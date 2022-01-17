package com.example.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmationDialog(
    title: String,
    dismissText: String = stringResource(id = android.R.string.cancel),
    onDismiss: () -> Unit = {},
    confirmText: String = stringResource(id = android.R.string.ok),
    onConfirm: () -> Unit
) {
    AlertDialog(
        title = {
            Text(text = title)
        },
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            Button(onClick = {
                onConfirm()
                onDismiss()
            }, modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .padding(bottom = 8.dp)) {
                Text(text = confirmText)
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismiss()
            }, modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .padding(bottom = 8.dp)) {
                Text(text = dismissText)
            }
        },
    )
}