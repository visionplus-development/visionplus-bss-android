package com.zte.iptvclient.android.auth.presentation.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zte.iptvclient.android.auth.presentation.components.ButtonMain
import com.zte.iptvclient.android.auth.presentation.components.TextFieldPassword
import com.zte.iptvclient.android.auth.presentation.components.ToolbarMain
import com.zte.iptvclient.android.auth.presentation.theme.ColorBackgroundForm

@Composable
fun CreateNewPasswordPage() {
    Scaffold(
        topBar = {
            ToolbarMain(
                modifier = Modifier.fillMaxWidth(),
                title = "Create New Password"
            ) {
            }
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(Color.Black)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(ColorBackgroundForm),
                ) {
                    TextFieldPassword(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp, horizontal = 12.dp),
                        label = "Enter Email",
                        placeHolder = "e.g. placeholder",
                        isEnabled = true,
                        password = "",
                        onPasswordChange = {}
                    )
                }
            }
        },
        bottomBar = {
            ButtonMain(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                text = "Next",
                isEnabled = false
            ) {
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CreateNewPasswordPreview() {
    CreateNewPasswordPage()
}