package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonMain(
    modifier: Modifier,
    text: String,
    isEnabled: Boolean,
    onButtonClick: () -> Unit
) {
    Button(
        modifier = modifier,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF07E3D0),
            disabledContainerColor = Color(0xFF073634),

            ),
        shape = RoundedCornerShape(5.dp),
        onClick = {
            onButtonClick()
        }
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.W600,
            textAlign = TextAlign.Center,
            color = if (isEnabled) Color(0xFF1F1F1F) else Color(0xFF00625E)
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