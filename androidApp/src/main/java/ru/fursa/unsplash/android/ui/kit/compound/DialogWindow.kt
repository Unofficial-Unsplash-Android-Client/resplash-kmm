package ru.fursa.unsplash.android.ui.kit.compound

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun DialogWindow(
    content: @Composable () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { onDismiss() }
    ) {
        content.invoke()
    }
}
