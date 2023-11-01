package com.zte.iptvclient.android.auth.presentation.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zte.iptvclient.android.auth.R
import com.zte.iptvclient.android.auth.presentation.components.ConfigLayoutDevice
import com.zte.iptvclient.android.auth.presentation.components.ToolbarMain
import com.zte.iptvclient.android.auth.presentation.theme.ColorBackround
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextPrimary
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextSecondary
import com.zte.iptvclient.android.auth.presentation.theme.VisionplusbssandroidTheme

@Composable
internal fun EmailVerificationPage(
    isSuccess: Boolean = true
) {
    VisionplusbssandroidTheme {
        ConfigLayoutDevice(
            screenMobile = { EmailVerificationContent(isSuccess)},
            screenTablet = { EmailVerificationContent(isSuccess)})
    }
}

@Composable
internal fun EmailVerificationContent(
    isSuccess: Boolean = true
) {
    Scaffold(
        topBar = {
            ToolbarMain(
                modifier = Modifier.fillMaxWidth(),
                title = "Register"
            ) {
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(ColorBackround),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isSuccess) {
                    VerificationSuccess()
                } else {
                    VerificationFailed()
                }
            }
        }
    )
}

@Composable
private fun VerificationSuccess() {
    Image(
        modifier = Modifier
            .width(150.dp)
            .padding(bottom = 32.dp),
        painter = painterResource(id = R.drawable.ic_verify_success),
        contentDescription = "",
        contentScale = ContentScale.Crop,
    )
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        text = stringResource(id = R.string.verify_success),
        style = MaterialTheme.typography.titleLarge,
        color = ColorTextPrimary,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun VerificationFailed() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 32.dp,
                end = 32.dp,
                bottom = 16.dp
            ),
        text = stringResource(id = R.string.verify_link_expired),
        style = MaterialTheme.typography.titleLarge,
        color = ColorTextPrimary,
        textAlign = TextAlign.Center
    )
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 52.dp),
        text = stringResource(id = R.string.retry_link_verification),
        style = MaterialTheme.typography.bodySmall,
        color = ColorTextSecondary,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun EmailVerificationPreview() {
    EmailVerificationPage(
        isSuccess = false
    )
}