package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.common.util.DeviceProperties.isTablet
import com.zte.iptvclient.android.auth.presentation.theme.ColorDivider
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextPrimary
import com.zte.iptvclient.android.auth.presentation.theme.VisionplusbssandroidTheme
import com.zte.iptvclient.android.auth.utils.DeviceProperties

@Composable
fun TextDivider(
    modifier: Modifier,
    text: String
) {

    val context = LocalContext.current
    val largeFontSize = DeviceProperties.LARGE.getFontSize(isTablet(context))
    val mediumFontSize = DeviceProperties.MEDIUM.getFontSize(isTablet(context))
    val smallFontSize = DeviceProperties.SMALL.getFontSize(isTablet(context))

    VisionplusbssandroidTheme {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                modifier = Modifier
                    .width(80.dp)
                    .height(1.dp),
                color = ColorDivider
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = text,
                style = MaterialTheme.typography.labelSmall.copy(fontSize = smallFontSize.sp),
                color = ColorTextPrimary,
                textAlign = TextAlign.Center
            )
            Divider(
                modifier = Modifier
                    .width(80.dp)
                    .height(1.dp),
                color = ColorDivider
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextDividerPreview() {
    TextDivider(
        modifier = Modifier.fillMaxWidth(),
        text = "or continue with"
    )
}