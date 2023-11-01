package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.common.util.DeviceProperties.isTablet
import com.zte.iptvclient.android.auth.presentation.theme.ColorBackgroundTextField
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextPrimary
import com.zte.iptvclient.android.auth.utils.DeviceProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldSearch(
    modifier: Modifier,
    placeHolder: String,
    isEnabled: Boolean,
    search: String,
    onSearchChange: (String) -> Unit,
) {

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val largeFontSize = DeviceProperties.LARGE.getFontSize(isTablet(context))
    val mediumFontSize = DeviceProperties.MEDIUM.getFontSize(isTablet(context))
    val smallFontSize = DeviceProperties.SMALL.getFontSize(isTablet(context))

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp)
            .height(50.dp),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = ColorBackgroundTextField,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = ColorTextPrimary,
            unfocusedTextColor = ColorTextPrimary
        ),
        value = search,
        textStyle = MaterialTheme.typography.labelSmall.copy(fontSize = smallFontSize.sp),
        singleLine = true,
        leadingIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    tint = Color.White,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 4.dp, top = 4.dp, end = 8.dp, bottom = 4.dp)
                )
            }
        },
        placeholder = {
            Text(
                text = placeHolder,
                color = Color(0xFF919999),
                style = MaterialTheme.typography.labelSmall.copy(fontSize = smallFontSize.sp)
            )
        },
        onValueChange = {
            onSearchChange(it)
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Next) }
        )
    )
}

@Preview(showBackground = true)
@Composable
fun TextFieldSearchPreview() {
    TextFieldSearch(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 12.dp),
        placeHolder = "Enter country name or country code",
        isEnabled = true,
        search = "",
        onSearchChange = {}
    )
}