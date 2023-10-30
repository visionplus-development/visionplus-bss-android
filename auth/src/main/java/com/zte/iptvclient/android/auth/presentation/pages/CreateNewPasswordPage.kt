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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var password by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            ToolbarMain(
                modifier = Modifier.fillMaxWidth(),
                title = "Create New Password"
            ) {
            }
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
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
                        password = password,
                        onPasswordChange = {
                            password = it
                        }
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
                isEnabled = password.isNotEmpty()
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