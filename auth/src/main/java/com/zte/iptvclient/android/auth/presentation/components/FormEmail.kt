package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormEmail(

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 12.dp),
    ) {
        TextFieldEmail(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            label = "Enter Email"
        )

        TextFieldPassword(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 42.dp),
            label = "Enter Password",
            placeHolder = "e.g. placeholder",
            isEnabled = false,
            password = "",
            onPasswordChange = {}
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Forgot Password?",
            textAlign = TextAlign.End,
            color = Color(0xFF919999),
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FormEmailPreview() {
    FormEmail()
}