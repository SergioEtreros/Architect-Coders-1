package com.senkou.architectcoderssem.ui.common

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun PermissionRequestEffect(permission: String, onResult: (Boolean) -> Unit) {
  val permissionLauncher =
    rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
      onResult(it)
    }

  LaunchedEffect(key1 = Unit) {
    permissionLauncher.launch(permission)
  }
}