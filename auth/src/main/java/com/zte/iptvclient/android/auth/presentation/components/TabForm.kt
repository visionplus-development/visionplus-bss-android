package com.zte.iptvclient.android.auth.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabForm(
    modifier: Modifier,
    tabPhone: @Composable () -> Unit,
    tabEmail: @Composable () -> Unit
) {
    val isActive = remember { mutableIntStateOf(0) }

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState { 2 }

    val tabRowItems = listOf(
        FormTabItem(
            title = "Phone Number",
            screen = tabPhone
        ),
        FormTabItem(
            title = "Email",
            screen = tabEmail
        ),
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TabRow(
            containerColor = Color(0xFF141414),
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    color = Color(0xFF07E3D0),
                    height = 2.dp,
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                )
            }
        ) {
            tabRowItems.forEachIndexed { index, title ->
                Tab(
                    selectedContentColor = Color.Cyan,
                    unselectedContentColor = Color.Cyan,
                    text = {
                        Text(
                            title.title,
                            color = if (isActive.intValue == index) Color(0xFF07E3D0) else Color(0xFF919999)
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        isActive.intValue = index
                        coroutineScope.launch { pagerState.animateScrollToPage(index) }
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false
        ) {
            tabRowItems[pagerState.currentPage].screen()
        }

    }
}

data class FormTabItem(
    val title: String,
    val screen: @Composable () -> Unit
)

@Preview(showBackground = true)
@Composable
fun TabFormPreview() {
    TabForm(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF141414)),
        tabPhone = { FormEmail() },
        tabEmail = { FormEmail() }
    )
}