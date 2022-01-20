package com.zxy_hunan.compose.acy.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.zxy_hunan.compose.ui.theme.black3

@Composable
fun SampleAlertDialog(
    title: String, content: String, cancelText: String,
    confirm: String, onConfirmClick: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { MediumTitle(title = title) },
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        text = { TextContent(text = content) },
        confirmButton = {
            TextButton(onClick = {
                onConfirmClick.invoke()
                onDismiss.invoke()
            }) {
                TextContent(text = confirm, color = black3)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss.invoke() }) {
                TextContent(text = cancelText)
            }
        },
    )
}

@Composable
fun SelectAlertDialog(
    title: String, content: String, primaryButtonText: String,
    secondButtonText: String, onPrimaryButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit, onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card {
            Column(Modifier.padding(20.dp)) {
                MediumTitle(title = title,modifier = Modifier.padding(bottom = 20.dp))
                TextContent(text = content,modifier = Modifier.padding(bottom = 20.dp))
                Prima
            }
        }
    }
}

