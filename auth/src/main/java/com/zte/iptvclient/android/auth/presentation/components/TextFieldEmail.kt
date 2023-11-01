package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.border
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zte.iptvclient.android.auth.data.model.InputWrapper
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextPrimary
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextSecondary
import com.zte.iptvclient.android.auth.presentation.theme.ColorBackgroundTextField
import com.zte.iptvclient.android.auth.presentation.theme.ColorError
import com.zte.iptvclient.android.auth.presentation.theme.ColorErrorBorder
import com.zte.iptvclient.android.auth.presentation.theme.VisionplusbssandroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldEmail(
    modifier: Modifier,
    inputWrapper: InputWrapper,
    label: String,
    onValueChange: (value: String) -> Unit,
    keyboardOptions: KeyboardOptions = remember { KeyboardOptions.Default },
    keyboardActions: KeyboardActions = KeyboardActions()
) {

    val fieldValue = remember { mutableStateOf(inputWrapper.value) }
    val fieldError = remember { mutableStateOf(inputWrapper.errorMessage) }

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

            val borderModifier = if (fieldValue.value == "error") {
                Modifier.border(1.dp, ColorErrorBorder, RoundedCornerShape(8.dp))
            } else {
                Modifier
            }
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(borderModifier),
                value = fieldValue.value,
                onValueChange = {
                    fieldValue.value = it
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

            // todo: check error from BE response
            if (fieldValue.value == "error") {
                fieldError.value = "error message"
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                    text = fieldError.value.orEmpty(),
                    style = MaterialTheme.typography.bodySmall,
                    color = ColorError,
                    textAlign = TextAlign.Start,
                )
            }
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
        inputWrapper = InputWrapper("error",null),
        label = "Email",
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