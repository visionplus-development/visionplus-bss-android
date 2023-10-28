package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zte.iptvclient.android.auth.R
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextPrimary
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextSecondary
import com.zte.iptvclient.android.auth.presentation.theme.ColorBackgroundTextField
import com.zte.iptvclient.android.auth.presentation.theme.VisionplusbssandroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldPassword(
    modifier: Modifier,
    label: String,
    placeHolder: String,
    isEnabled: Boolean,
    password: String,
    onPasswordChange: (String) -> Unit,
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    VisionplusbssandroidTheme {
        Column(
            modifier = modifier,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = if (isEnabled) ColorTextPrimary else ColorTextSecondary,
                textAlign = TextAlign.Start
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = password,
                onValueChange = { onPasswordChange(it) },
                placeholder = {
                    Text(
                        text = placeHolder,
                        style = MaterialTheme.typography.labelSmall,
                        color = ColorTextSecondary
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
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) {
                        painterResource(id = R.drawable.ic_visibility_on)
                    } else {
                        painterResource(id = R.drawable.ic_visibility_off)
                    }

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = image,
                            "switch password visibility",
                            Modifier.size(24.dp),
                            tint = ColorTextSecondary
                        )
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldPasswordPreview() {
    TextFieldPassword(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 12.dp),
        label = "Enter Password",
        placeHolder = "e.g. placeholder",
        isEnabled = true,
        password = "",
        onPasswordChange = { }
    )
}