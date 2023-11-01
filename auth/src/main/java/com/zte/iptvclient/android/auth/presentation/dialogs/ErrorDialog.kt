package com.zte.iptvclient.android.auth.presentation.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.zte.iptvclient.android.auth.data.model.DialogWrapper
import com.zte.iptvclient.android.auth.presentation.components.ButtonMain
import com.zte.iptvclient.android.auth.presentation.theme.ColorBackgroundDialog
import com.zte.iptvclient.android.auth.presentation.theme.ColorPrimary
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextPrimary
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextSecondary
import com.zte.iptvclient.android.auth.presentation.theme.VisionplusbssandroidTheme

@Composable
fun ErrorDialog(
    dialogWrapper: DialogWrapper,
    setShowDialog: (Boolean) -> Unit,
    onPositiveClick: (() -> Unit)? = null,
    onNegativeClick: (() -> Unit)? = null
) {
    VisionplusbssandroidTheme {
        Dialog(onDismissRequest = { setShowDialog(false) }) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = ColorBackgroundDialog
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = dialogWrapper.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = ColorTextPrimary
                    )
                    Text(
                        modifier = Modifier.padding(bottom = 32.dp),
                        text = dialogWrapper.message,
                        style = MaterialTheme.typography.labelSmall,
                        color = ColorTextSecondary
                    )

                    // CTA negative shown for server 500.
                    if (dialogWrapper.textPositive.isNotEmpty()) {
                        ButtonMain(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            text = dialogWrapper.textPositive,
                            isEnabled = true
                        ) {
                            setShowDialog(false)
                            onPositiveClick?.invoke()
                        }
                    }

                    if (dialogWrapper.textNegative.isNotEmpty()) {
                        ClickableText(
                            text = AnnotatedString(dialogWrapper.textNegative),
                            style = MaterialTheme.typography.titleMedium.copy(ColorPrimary),
                            onClick = {
                                setShowDialog(false)
                                onNegativeClick?.invoke()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorDialogPreview() {
    ErrorDialog(
        dialogWrapper = DialogWrapper(
            title = "Too many login attempts",
            message = "Please try again after 10 minutes.",
            textPositive = "OK",
            textNegative = "Forgot Password"
        ),
        setShowDialog = {},
        onPositiveClick = {},
        onNegativeClick = {}
    )
}