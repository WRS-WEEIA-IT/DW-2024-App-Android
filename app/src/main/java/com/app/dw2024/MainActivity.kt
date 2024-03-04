package com.app.dw2024

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.app.dw2024.components.CustomAlertDialog
import com.app.dw2024.repository.interfaces.TasksRepository
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

    @Inject
    lateinit var tasksRepository: TasksRepository

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDialogVisible by remember { mutableStateOf(false) }
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
                                isDialogVisible = true
                            })
                        },
                        viewModel = viewModel
                    )
                    if (isDialogVisible) {
                        CustomAlertDialog(
                            onDismiss = {
                                isDialogVisible = false
                            },
                            onConfirm = {
                                isDialogVisible = false
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
            viewModel.showNoQrCodeDetected()
        } else {
            lifecycleScope.launch {
                val completedTask = tasksRepository.getTaskByQrCode(result.contents)
                if (completedTask != null) {
                    val res = tasksRepository.completeTask(completedTask)
                    if (res) {
                        viewModel.showSuccessfulTaskCompletion(completedTask)
                    } else {
                        viewModel.showTaskAlreadyFinished()
                    }
                } else {
                    viewModel.showNoSuchTaskExists()
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