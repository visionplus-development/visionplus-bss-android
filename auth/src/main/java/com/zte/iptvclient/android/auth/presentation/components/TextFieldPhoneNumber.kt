package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zte.iptvclient.android.auth.R
import com.zte.iptvclient.android.auth.data.model.InputWrapper
import com.zte.iptvclient.android.auth.presentation.theme.ColorBackgroundTextField
import com.zte.iptvclient.android.auth.presentation.theme.ColorError
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextPrimary
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextSecondary
import com.zte.iptvclient.android.auth.presentation.theme.VisionplusbssandroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldPhoneNumber(
    modifier: Modifier,
    label: String,
    inputWrapper: InputWrapper,
    isEnabled: Boolean,
    onPhoneNumberChange: (String) -> Unit,
) {

    val focusManager = LocalFocusManager.current
    val fieldValue = remember { mutableStateOf(inputWrapper.value) }
    val fieldError = remember { mutableStateOf(inputWrapper.errorMessage) }
    val countries = listOf(
        Country("Indonesia", "62", R.drawable.ic_flag_indonesia),
        Country("Malaysia", "60", R.drawable.ic_flag_malaysia)
    )
    val showBottomSheet = remember { mutableStateOf(false) }

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

            val borderModifier = if (fieldValue.value == "error") {
                Modifier.border(1.dp, ColorError, RoundedCornerShape(8.dp))
            } else {
                Modifier
            }
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(borderModifier),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = ColorBackgroundTextField,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = ColorTextPrimary,
                    unfocusedTextColor = ColorTextPrimary
                ),
                maxLines = 1,
                value = inputWrapper.value,
                textStyle = TextStyle(
                    color = ColorTextPrimary,
                    fontSize = 12.sp
                ),
                singleLine = true,
                leadingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable {
                            showBottomSheet.value = true
                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_flag_indonesia),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(10.dp)
                                .size(width = 25.dp, height = 20.dp)
                        )
                        Text(
                            text = "+62",
                            color = ColorTextSecondary,
                            fontSize = 12.sp
                        )
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            tint = Color.White,
                            contentDescription = null
                        )
                    }
                },
                placeholder = {
                    Text(
                        text = "Phone number (ex: 085812345678)",
                        color = Color(0xFF919999),
                        fontSize = 12.sp
                    )
                },
                onValueChange = {
                    fieldValue.value = it
                    onPhoneNumberChange(it)
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Phone),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
                )
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
            if (showBottomSheet.value) {
                BottomSheetCountry(
                    countries = countries,
                    onShowBottomSheet = { showBottomSheet.value = false },
                    onCountrySelected = {}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldPhoneNumberPreview() {
    TextFieldPhoneNumber(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 12.dp),
        label = "Enter Phone Number",
        inputWrapper = InputWrapper("", null),
        isEnabled = true,
        onPhoneNumberChange = {}
    )
}