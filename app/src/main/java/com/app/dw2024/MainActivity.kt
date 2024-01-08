package com.app.dw2024

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.app.dw2024.repository.interfaces.TasksRepository
import com.app.dw2024.repository.interfaces.UserRepository
import com.app.dw2024.ui.theme.DarkBlack
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DzienWydzialu2024Theme {
                if (userRepository.getUserId() == 0) {
                    LaunchedEffect(key1 = true) {
                        userRepository.createNewUser()
                    }
                }
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DarkBlack
                ) {
                    MainScreen(
                        navController = navController,
                        onQrCodeScannerClick = {
                            requestCameraPermission(this)
                        }
                    )
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
            Toast.makeText(this, "No QR code found", Toast.LENGTH_LONG).show()
        } else {
            lifecycleScope.launch {
                val completedTask = tasksRepository.getTaskByQrCode(result.contents)
                if (completedTask != null) {
                    val res = tasksRepository.completeTask(completedTask)
                    if (res) {
                        Toast.makeText(this@MainActivity, "Task completed", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@MainActivity, "Task already completed", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "No task found", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun requestCameraPermission(context: Context) {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED) {
            showCamera()
        } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
            Toast.makeText(this, "Please restart app, camera is required", Toast.LENGTH_LONG).show()
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
}