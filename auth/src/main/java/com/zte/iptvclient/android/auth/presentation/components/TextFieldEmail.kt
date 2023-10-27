package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.sp

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
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = label,
            textAlign = TextAlign.Start,
            color = Color(0xFFF5F5F5),
            fontSize = 14.sp,
            fontWeight = FontWeight.W500
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFF202020),
                cursorColor = Color(0xFFF5F5F5),
                focusedTextColor = Color(0xFFF5F5F5),
                unfocusedTextColor = Color(0xFFF5F5F5),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(8.dp),
            placeholder = {
                Text(
                    text = "Email (mail@gmail.com)",
                    color = Color(0xFF919999),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400
                )
            },
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions
        )
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