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
import com.zte.iptvclient.android.auth.presentation.components.ToolbarMain
import com.zte.iptvclient.android.auth.presentation.theme.ColorBackround
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextPrimary
import com.zte.iptvclient.android.auth.presentation.theme.VisionplusbssandroidTheme

@Composable
internal fun EmailVerificationScreen() {
    VisionplusbssandroidTheme {
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
                    Image(
                        modifier = Modifier
                            .width(150.dp)
                            .padding(bottom = 32.dp),
                        painter = painterResource(id = R.drawable.ic_verify_success),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        text = stringResource(id = R.string.verify_success),
                        style = MaterialTheme.typography.titleLarge,
                        color = ColorTextPrimary,
                        textAlign = TextAlign.Center
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmailVerificationPreview() {
    EmailVerificationScreen()
}