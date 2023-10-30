package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextPrimary
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextSecondary
import com.zte.iptvclient.android.auth.presentation.theme.VisionplusbssandroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldPhoneNumber(
    modifier: Modifier,
    label: String,
    placeHolder: String,
    isEnabled: Boolean,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
) {

    val focusManager = LocalFocusManager.current
    val countries = listOf(
        Country("Indonesia", "62",R.drawable.ic_flag_indonesia),
        Country("Malaysia", "60", R.drawable.ic_flag_malaysia)
    )
    var showBottomSheet by remember { mutableStateOf(false) }

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
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFF202020),
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                value = phoneNumber,
                textStyle = TextStyle(
                    color = Color(0xFFFFFFFF),
                    fontSize = 12.sp
                ),
                singleLine = true,
                leadingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable {
                            showBottomSheet = true
                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_flag_indonesia),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.padding(10.dp)
                                .size(25.dp)
                        )
                        Text(
                            text = "+62",
                            color = Color(0xFF919999),
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
                    if (it.isNullOrEmpty()) {
                        onPasswordChange("")
                    }
                    onPhoneNumberChange(it)
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Phone),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
                )
            )
            if (showBottomSheet) {
                BottomSheetCountry(
                    countries = countries,
                    onShowBottomSheet = { showBottomSheet = false },
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
        placeHolder = "e.g. placeholder",
        isEnabled = true,
        phoneNumber = "",
        onPasswordChange = { },
        onPhoneNumberChange = {}
    )
}