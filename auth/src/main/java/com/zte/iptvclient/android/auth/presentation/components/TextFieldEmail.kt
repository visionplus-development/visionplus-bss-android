package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextPrimary
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextSecondary
import com.zte.iptvclient.android.auth.presentation.theme.ColorBackgroundTextField
import com.zte.iptvclient.android.auth.presentation.theme.VisionplusbssandroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldEmail(
    modifier: Modifier,
    label: String,
    value: String,
    onValueChange: (value: String) -> Unit,
    keyboardOptions: KeyboardOptions = remember { KeyboardOptions.Default },
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    VisionplusbssandroidTheme {
        Column(
            modifier = modifier
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = ColorTextPrimary,
                textAlign = TextAlign.Start,
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = {
                    onValueChange(it)
                },
                placeholder = {
                    Text(
                        text = "Email (mail@gmail.com)",
                        style = MaterialTheme.typography.labelSmall,
                        color = ColorTextSecondary,
                    )
                },
                textStyle = MaterialTheme.typography.bodyMedium,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = ColorBackgroundTextField,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = ColorTextPrimary,
                    unfocusedTextColor = ColorTextPrimary
                ),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldEmailPreview() {
    TextFieldEmail(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        label = "Email",
        value = "",
        onValueChange = {},
        keyboardOptions = remember {
            KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        },
        keyboardActions = KeyboardActions()
    )
}