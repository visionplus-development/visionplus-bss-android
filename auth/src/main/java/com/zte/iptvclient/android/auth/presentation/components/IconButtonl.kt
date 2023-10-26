package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zte.iptvclient.android.auth.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    text: String,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.size(32.dp),
            shape = CircleShape,
            onClick = onButtonClick
        ) {
            Image(
                painter,
                contentDescription = text,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = text,
            modifier = Modifier.padding(top = 8.dp),
            color = Color(0xFF919999),
            fontSize = 10.sp,
            fontWeight = FontWeight.W600
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IconButtonPreview() {
    IconButton(
        painter = painterResource(id = R.drawable.ic_gmail),
        text = "GMAIL",
    ) {
    }
}