package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.common.util.DeviceProperties.isTablet
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextSecondary
import com.zte.iptvclient.android.auth.presentation.theme.VisionplusbssandroidTheme

@Composable
fun ConfigLayoutDevice(
    screenMobile: @Composable () -> Unit,
    screenTablet: @Composable () -> Unit
) {
    VisionplusbssandroidTheme {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            if (isTablet(LocalContext.current)) {
                TabletLayout(screenTablet)
            } else {
                MobileLayout(screenMobile)
            }
        }
    }
}

@Composable
fun TabletLayout(screenTablet: @Composable () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier.weight(0.25f)
        ) {
        }
        Box(
            modifier = Modifier.weight(0.5f)
        ) {
            screenTablet()
        }
        Box(
            modifier = Modifier.weight(0.25f)
        ) {
        }
    }
}

@Composable
fun MobileLayout(screenMobile: @Composable () -> Unit) {
    screenMobile()
}


@Preview(
    showBackground = true,
    device = "id:pixel_c", widthDp = 960, heightDp = 600
)
@Composable
fun ConfigLayoutDevicePreview() {
    ConfigLayoutDevice(
        screenMobile = {
            Text("mobile", color = ColorTextSecondary)
        },
        screenTablet = {
            Text("tablet", color = ColorTextSecondary)
        }
    )
}