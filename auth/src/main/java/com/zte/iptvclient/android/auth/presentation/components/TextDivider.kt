package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextDivider(
    modifier: Modifier,
    text: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            color = Color(0xFF3A3D3D),
            modifier = Modifier
                .width(80.dp)
                .height(1.dp)
        )
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 16.dp),
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            fontWeight = FontWeight.W400,
            color = Color(0xFFFCFCFC),
        )
        Divider(
            color = Color(0xFF3A3D3D),
            modifier = Modifier
                .width(80.dp)
                .height(1.dp)
        )
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