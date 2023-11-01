package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.common.util.DeviceProperties.isTablet
import com.zte.iptvclient.android.auth.presentation.theme.ColorButtonDisable
import com.zte.iptvclient.android.auth.presentation.theme.ColorPrimary
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextButton
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextButtonDisable
import com.zte.iptvclient.android.auth.utils.DeviceProperties

@Composable
fun ButtonMain(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean,
    onButtonClick: () -> Unit
) {

    val context = LocalContext.current
    val largeFontSize = DeviceProperties.LARGE.getFontSize(isTablet(context))
    val mediumFontSize = DeviceProperties.MEDIUM.getFontSize(isTablet(context))
    val smallFontSize = DeviceProperties.SMALL.getFontSize(isTablet(context))

    Button(
        modifier = modifier,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = ColorPrimary,
            disabledContainerColor = ColorButtonDisable
        ),
        shape = RoundedCornerShape(5.dp),
        onClick = {
            onButtonClick()
        }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium.copy(fontSize = mediumFontSize.sp),
            color = if (isEnabled) ColorTextButton else ColorTextButtonDisable,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonMainPreview() {
    ButtonMain(
        modifier = Modifier.fillMaxWidth(),
        text = "Login",
        false
    ) {
    }
}