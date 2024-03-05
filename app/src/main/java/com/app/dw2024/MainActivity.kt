package com.app.dw2024

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.app.dw2024.components.CustomAlertDialog
import com.app.dw2024.repository.interfaces.UserRepository
import com.app.dw2024.ui.theme.DarkGrey
import com.app.dw2024.ui.theme.DzienWydzialu2024Theme
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var userRepository: UserRepository

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            var isCameraPermissionDialogVisible by remember { mutableStateOf(false) }
            DzienWydzialu2024Theme {
                if (userRepository.getUserId() == 0) {
                    LaunchedEffect(key1 = true) {
                        userRepository.createNewUser()
                    }
                }
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DarkGrey
                ) {
                    MainScreen(
                        navController = navController,
                        onQrCodeScannerClick = {
                            requestCameraPermission(this, onPermanentlyDenied = {
                                isCameraPermissionDialogVisible = true
                            })
                        },
                        mainViewModel = mainViewModel
                    )
                    if (isCameraPermissionDialogVisible) {
                        CustomAlertDialog(
                            onDismiss = {
                                isCameraPermissionDialogVisible = false
                            },
                            onConfirm = {
                                isCameraPermissionDialogVisible = false
                                openAppSettings()
                            },
                            title = stringResource(id = R.string.required_camera_access),
                            description = stringResource(id = R.string.camera_access_is_required_to_scan_qr_code),
                            confirmText = "OK"
                        )
                    }
                }
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        isGranted -> if (isGranted) {
            showCamera()
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show()
        }
    }

    private val qcCodeScannerLauncher = registerForActivityResult(ScanContract()) { result ->
        if (result.contents == null) {
            mainViewModel.showNoQrCodeDetected()
        } else {
            lifecycleScope.launch {
                val completedTask = mainViewModel.state.tasks.firstOrNull { task ->
                    task.qrCode == result.contents
                }
                if (completedTask != null) {
                    // completeTask does not update Firestore, just SharedPreferences
                    // Firestore should be updated only if it returned true, so in showSuccessfulTaskCompletion
                    val res = mainViewModel.completeTask(completedTask)
                    if (res) {
                        // successfully completed task
                        mainViewModel.onSuccessfulTaskCompletion(completedTask)
                    } else {
                        // task was already done
                        mainViewModel.showTaskAlreadyFinished()
                    }
                } else {
                    // this is invalid QR code
                    mainViewModel.showNoSuchTaskExists()
                }
            }
        }
    }

    private fun requestCameraPermission(context: Context, onPermanentlyDenied: () -> Unit) {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED) {
            showCamera()
        } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
            onPermanentlyDenied()
        } else {
            requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    private fun showCamera() {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setCameraId(0)
        options.setOrientationLocked(true)
        options.setBeepEnabled(false)
        options.setTorchEnabled(false)
        options.setPrompt("")
        qcCodeScannerLauncher.launch(options)
    }

    private fun Activity.openAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivity(intent)
    }
}