package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.common.util.DeviceProperties.isTablet
import com.zte.iptvclient.android.auth.R
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextSecondary
import com.zte.iptvclient.android.auth.utils.DeviceProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    text: String,
    onButtonClick: () -> Unit
) {

    val context = LocalContext.current
    val largeFontSize = DeviceProperties.LARGE.getFontSize(isTablet(context))
    val mediumFontSize = DeviceProperties.MEDIUM.getFontSize(isTablet(context))
    val smallFontSize = DeviceProperties.SMALL.getFontSize(isTablet(context))

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
            color = ColorTextSecondary,
            style = MaterialTheme.typography.titleSmall.copy(fontSize = smallFontSize.sp)
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