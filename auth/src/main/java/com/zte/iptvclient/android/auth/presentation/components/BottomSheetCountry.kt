package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zte.iptvclient.android.auth.R
import com.zte.iptvclient.android.auth.presentation.theme.ColorBackround
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextPrimary
import com.zte.iptvclient.android.auth.presentation.theme.ColorTextSecondary

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BottomSheetCountry(
    countries: List<Country>,
    onShowBottomSheet: () -> Unit,
    onCountrySelected: (Country) -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    var selectedCountry by remember { mutableStateOf<Country?>(null) }

    ModalBottomSheet(
        containerColor = ColorBackround,
        modifier = Modifier.fillMaxHeight(0.5F),
        onDismissRequest = {
            onShowBottomSheet
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Icon(
                        tint = ColorTextSecondary,
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = "Country Code",
                        color = ColorTextPrimary,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(8.dp)
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                    TextFieldSearch(
                        modifier = Modifier.fillMaxWidth(),
                        placeHolder = "Enter country name or country code",
                        isEnabled = true, search = "", onSearchChange = {})

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                    ) {
                        items(countries.size) { country ->
                            CountryListItem(
                                country = countries[country],
                                onCountrySelected = {
                                    selectedCountry = it
                                    onShowBottomSheet
                                }
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun CountryListItem(
    country: Country,
    onCountrySelected: (Country) -> Unit
) {
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
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = country.name,
            color = ColorTextPrimary,
            fontSize = 16.sp)

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "+${country.code}",
            color = ColorTextPrimary,
            fontSize = 16.sp)
    }
}

data class Country(val name: String, val code: String, val flag : Int)

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
        Country("United States", "1",R.drawable.ic_flag_malaysia),
        Country("Canada", "1", R.drawable.ic_flag_malaysia),
        Country("India", "91",R.drawable.ic_flag_malaysia),
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