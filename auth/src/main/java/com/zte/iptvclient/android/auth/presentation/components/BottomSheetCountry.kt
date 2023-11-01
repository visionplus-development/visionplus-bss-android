package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.common.util.DeviceProperties.isTablet
import com.zte.iptvclient.android.auth.R
import com.zte.iptvclient.android.auth.presentation.theme.ColorBackround
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextPrimary
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextSecondary
import com.zte.iptvclient.android.auth.presentation.theme.VisionplusbssandroidTheme
import com.zte.iptvclient.android.auth.utils.DeviceProperties

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BottomSheetCountry(
    countries: List<Country>,
    onShowBottomSheet: () -> Unit,
    onCountrySelected: (Country) -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    val context = LocalContext.current
    val largeFontSize = DeviceProperties.LARGE.getFontSize(isTablet(context))
    val mediumFontSize = DeviceProperties.MEDIUM.getFontSize(isTablet(context))
    val smallFontSize = DeviceProperties.SMALL.getFontSize(isTablet(context))

    VisionplusbssandroidTheme {
        ModalBottomSheet(
            containerColor = ColorBackround,
            modifier = Modifier
                .fillMaxWidth(),
            onDismissRequest = {
                onShowBottomSheet()
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(DeviceProperties.getPadding(16.dp, isTablet(context)))
                ) {
                    Icon(
                        tint = ColorTextSecondary,
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                onShowBottomSheet()
                            }
                            .align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Country Code",
                        color = ColorTextPrimary,
                        style = MaterialTheme.typography.titleMedium.copy(fontSize = mediumFontSize.sp),
                        modifier = Modifier.padding(DeviceProperties.getPadding(8.dp, isTablet(context)))
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    TextFieldSearch(
                        modifier = Modifier.fillMaxWidth(),
                        placeHolder = "Enter country name or country code",
                        isEnabled = true,
                        search = searchText,
                        onSearchChange = { value ->
                            searchText = value
                        })

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        items(countries.size) { country ->
                            CountryListItem(
                                country = countries[country],
                                onCountrySelected = { value ->
                                    onCountrySelected(value)
                                    onShowBottomSheet()
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun CountryListItem(
    country: Country,
    onCountrySelected: (Country) -> Unit
) {

    val context = LocalContext.current
    val largeFontSize = DeviceProperties.LARGE.getFontSize(isTablet(context))
    val mediumFontSize = DeviceProperties.MEDIUM.getFontSize(isTablet(context))
    val smallFontSize = DeviceProperties.SMALL.getFontSize(isTablet(context))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onCountrySelected(country)
            }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = country.flag),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(width = 25.dp, height = 20.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = country.name,
            color = ColorTextPrimary,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = smallFontSize.sp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "+${country.code}",
            color = ColorTextPrimary,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = largeFontSize.sp)
        )
    }
}

data class Country(val name: String, val code: String, val flag: Int)

@Composable
fun DemoScreen() {
    val countries = listOf(
        Country("United States", "1", R.drawable.ic_flag_indonesia),
        Country("Canada", "1", R.drawable.ic_flag_malaysia),
        Country("India", "91", R.drawable.ic_flag_malaysia),
        // Add more countries as needed
    )

    var showBottomSheet by remember { mutableStateOf(false) }

    Button(
        onClick = {
            showBottomSheet = true
        }) {
        Text(text = "Show Country Code Bottom Sheet")
    }
    if (showBottomSheet) {
        BottomSheetCountry(
            countries = countries,
            onShowBottomSheet = {
                showBottomSheet = false
            },
            onCountrySelected = {
                // Handle country selection here
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCountryCodeBottomSheet() {
    val countries = listOf(
        Country("United States", "1", R.drawable.ic_flag_malaysia),
        Country("Canada", "1", R.drawable.ic_flag_malaysia),
        Country("India", "91", R.drawable.ic_flag_malaysia),
        // Add more countries as needed
    )
    BottomSheetCountry(
        countries = countries,
        onShowBottomSheet = {
        },
        onCountrySelected = {
            // Handle country selection here
        }
    )
}