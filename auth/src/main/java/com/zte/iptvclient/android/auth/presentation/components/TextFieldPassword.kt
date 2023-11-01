package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.border
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.common.util.DeviceProperties.isTablet
import com.zte.iptvclient.android.auth.R
import com.zte.iptvclient.android.auth.data.model.InputWrapper
import com.zte.iptvclient.android.auth.presentation.theme.ColorBackgroundTextField
import com.zte.iptvclient.android.auth.presentation.theme.ColorError
import com.zte.iptvclient.android.auth.presentation.theme.ColorErrorBorder
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextPrimary
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextSecondary
import com.zte.iptvclient.android.auth.presentation.theme.VisionplusbssandroidTheme
import com.zte.iptvclient.android.auth.utils.DeviceProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldPassword(
    modifier: Modifier,
    label: String,
    inputWrapper: InputWrapper,
    placeHolder: String,
    isEnabled: Boolean = true,
    onPasswordChange: (String) -> Unit,
) {
    val passwordVisible = rememberSaveable { mutableStateOf(false) }
    val fieldValue = remember { mutableStateOf(inputWrapper.value) }
    val fieldError = remember { mutableStateOf(inputWrapper.errorMessage) }
    val context = LocalContext.current
    val largeFontSize = DeviceProperties.LARGE.getFontSize(isTablet(context))
    val mediumFontSize = DeviceProperties.MEDIUM.getFontSize(isTablet(context))
    val smallFontSize = DeviceProperties.SMALL.getFontSize(isTablet(context))

    VisionplusbssandroidTheme {
        Column(
            modifier = modifier,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                text = label,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = mediumFontSize.sp),
                color = if (isEnabled) ColorTextPrimary else ColorTextSecondary,
                textAlign = TextAlign.Start
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
                    onPasswordChange(it)
                },
                enabled = isEnabled,
                placeholder = {
                    Text(
                        text = placeHolder,
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = smallFontSize.sp),
                        color = ColorTextSecondary
                    )
                },
                textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = mediumFontSize.sp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = ColorBackgroundTextField,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = ColorTextPrimary,
                    unfocusedTextColor = ColorTextPrimary
                ),
                shape = RoundedCornerShape(8.dp),
                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible.value) {
                        painterResource(id = R.drawable.ic_visibility_on)
                    } else {
                        painterResource(id = R.drawable.ic_visibility_off)
                    }

                    IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                        Icon(
                            painter = if (isEnabled) image else painterResource(id = R.drawable.ic_visibility_off),
                            "switch password visibility",
                            Modifier.size(24.dp),
                            tint = ColorTextSecondary
                        )
                    }
                }
            )

            // todo: check error from BE response
            if (fieldValue.value == "error") {
                fieldError.value = "error message"
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                    text = fieldError.value.orEmpty(),
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = smallFontSize.sp),
                    color = ColorError,
                    textAlign = TextAlign.Start,
                )
            }
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
        inputWrapper = InputWrapper("", null),
        placeHolder = "e.g. placeholder",
        isEnabled = true,
        onPasswordChange = { }
    )
}