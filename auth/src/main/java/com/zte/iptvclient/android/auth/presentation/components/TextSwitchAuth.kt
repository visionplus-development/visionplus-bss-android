package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.android.gms.common.util.DeviceProperties.isTablet
import com.zte.iptvclient.android.auth.presentation.theme.ColorPrimary
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextSecondary
import com.zte.iptvclient.android.auth.presentation.theme.VisionplusbssandroidTheme
import com.zte.iptvclient.android.auth.utils.DeviceProperties

@Composable
fun TextSwitchAuth(
    modifier: Modifier,
    questionText: String,
    actionText: String,
    onTextClick: () -> Unit
) {

    val context = LocalContext.current
    val largeFontSize = DeviceProperties.LARGE.getFontSize(isTablet(context))
    val mediumFontSize = DeviceProperties.MEDIUM.getFontSize(isTablet(context))
    val smallFontSize = DeviceProperties.SMALL.getFontSize(isTablet(context))

    VisionplusbssandroidTheme {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = questionText,
                style = MaterialTheme.typography.labelSmall.copy(fontSize = smallFontSize.sp),
                color = ColorTextSecondary,
            )
            ClickableText(
                text = AnnotatedString(actionText),
                style = MaterialTheme.typography.labelSmall.copy(
                    color = ColorPrimary,
                    fontSize = smallFontSize.sp
                ),
                onClick = {
                    onTextClick()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextSwitchAuthPreview() {
    TextSwitchAuth(
        modifier = Modifier,
        questionText = "Have not created an account? ",
        actionText = "Register"
    ) {
    }
}