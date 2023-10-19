package com.zte.iptvclient.android.auth.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.zte.iptvclient.android.auth.R
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    clickGoogle: () -> Unit,
    callbackManager: CallbackManager,
    onLoginResult: (loginResult: LoginResult?) -> Unit,
    clickBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CustomToolbar(
                title = "Login",
                onBackClick = clickBack
            )
        },
        bottomBar = {
            ButtonLogin()
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 60.dp)
                    .background(Color.Black),
            ) {
                TextSwitchRegister()
                TabLayout()
                TextSocialLogin()
                GridSocialLoginScreen(clickGoogle, callbackManager, onLoginResult)
                TextTNC()
            }
        }
    )
}

@Composable
fun ButtonLogin() {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 56.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF07E3D0)
        ),
        shape = RoundedCornerShape(8.dp),
        onClick = {
        }) {
        Text(
            text = "LOGIN",
            fontSize = 14.sp,
            color = Color(0xFF1F1F1F)
        )
    }
}

@Composable
fun TextSwitchRegister() {
    Row(modifier = Modifier.padding(all = 16.dp)) {
        Text(
            text = "Have not created an account? ",
            color = Color(0xFF7D7D7D),
            fontSize = 13.sp
        )
        ClickableText(
            text = AnnotatedString("Register"),
            style = TextStyle(color = Color(0xFF07E3D0), fontSize = 13.sp),
            onClick = {
            }
        )
    }
}

@Composable
fun TextSocialLogin() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            color = Color(0xFF7D7D7D),
            modifier = Modifier
                .width(80.dp)
                .height(1.dp)
                .padding(end = 10.dp)
        )
        Text(
            text = "or continue with",
            textAlign = TextAlign.Center,
            fontSize = 13.sp,
            color = Color(0xFFFCFCFC),
        )
        Divider(
            color = Color(0xFF7D7D7D),
            modifier = Modifier
                .width(80.dp)
                .height(1.dp)
                .padding(start = 10.dp)
        )
    }
}

@Composable
fun TextTNC() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "By logging in, I agree with",
            color = Color(0xFF7D7D7D),
            fontSize = 13.sp
        )
        Row {
            ClickableText(
                text = AnnotatedString("Terms & Conditions "),
                style = TextStyle(color = Color(0xFF07E3D0), fontSize = 13.sp),
                onClick = {
                }
            )
            Text(
                text = "and ",
                color = Color(0xFF7D7D7D),
                fontSize = 13.sp
            )
            ClickableText(
                text = AnnotatedString("Privacy Policy "),
                style = TextStyle(color = Color(0xFF07E3D0), fontSize = 13.sp),
                onClick = {
                }
            )
            Text(
                text = "from Vision+ ",
                fontSize = 13.sp,
                color = Color(0xFF7D7D7D),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbar(
    title: String,
    onBackClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Black
            ),
            title = {
                Text(
                    text = title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            navigationIcon = {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            },
            actions = {
                // Add other actions if needed
            },
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayout() {
    val isActive = remember { mutableIntStateOf(0) }

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    val tabRowItems = listOf(
        ImageTabItem(
            text = "Phone Number",
            screen = { PhoneLogin() }
        ),
        ImageTabItem(
            text = "Email",
            screen = { EmailLogin() }
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF141414)),
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
                            title.text,
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
            pageCount = tabRowItems.size,
            state = pagerState,
            userScrollEnabled = false
        ) {
            tabRowItems[pagerState.currentPage].screen()
        }
    }
}

data class ImageTabItem(
    val text: String,//Tab Title
    val screen: @Composable () -> Unit//Tab Screen(can also take params)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneLogin() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 12.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            text = "Enter Phone Number",
            textAlign = TextAlign.Start,
            color = Color(0xFFF5F5F5),
            fontSize = 14.sp
        )

        TextField(
            modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFF202020),
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            value = "",
            placeholder = {
                Text(
                    text = "Phone number (ex: 085812345678)",
                    color = Color(0xFF919999)
                )
            },
            onValueChange = {},
        )

        Text(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            text = "Enter Password",
            textAlign = TextAlign.Start,
            color = Color(0xFFF5F5F5),
            fontSize = 14.sp
        )

        TextField(
            modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFF202020),
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            value = "",
            placeholder = {
                Text(
                    text = "Password",
                    color = Color(0xFF919999)
                )
            },
            onValueChange = {},
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Forgot Password?",
            textAlign = TextAlign.End,
            color = Color(0xFF919999),
            fontSize = 14.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailLogin() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 12.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            text = "Enter Email",
            textAlign = TextAlign.Start,
            color = Color(0xFFF5F5F5),
            fontSize = 14.sp
        )

        TextField(
            modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFF202020),
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            value = "",
            placeholder = {
                Text(
                    text = "Email (mail@gmail.com)",
                    color = Color(0xFF919999)
                )
            },
            onValueChange = {},
        )

        Text(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            text = "Enter Password",
            textAlign = TextAlign.Start,
            color = Color(0xFFF5F5F5),
            fontSize = 14.sp
        )

        TextField(
            modifier = Modifier.fillMaxWidth().padding(bottom = 42.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFF202020),
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            value = "",
            placeholder = {
                Text(
                    text = "Password",
                    color = Color(0xFF919999)
                )
            },
            onValueChange = {},
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Forgot Password?",
            textAlign = TextAlign.End,
            color = Color(0xFF919999),
            fontSize = 14.sp
        )
    }
}


@Composable
fun GridSocialLoginScreen(
    clickGoogle: () -> Unit,
    callbackManager: CallbackManager,
    onLoginResult: (loginResult: LoginResult?) -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GridItem(
                text = "Google",
                painter = painterResource(id = R.drawable.gmail),
                modifier = Modifier.weight(1f),
                onClick = {
                    clickGoogle()
                }
            )
            GridItem(
                text = "Facebook",
                painter = painterResource(id = R.drawable.facebook),
                modifier = Modifier.weight(1f),
                onClick = {
                    val loginButton = LoginButton(context)
                    loginButton.setPermissions(listOf("email", "public_profile"))
                    loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                        override fun onSuccess(loginResult: LoginResult) {
                            onLoginResult(loginResult)
                        }

                        override fun onCancel() {
                        }

                        override fun onError(error: FacebookException) {
                            onLoginResult(null)
                        }
                    })

                    loginButton.performClick()
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridItem(text: String, painter: Painter, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.size(32.dp),
            shape = CircleShape,
            onClick = onClick
        ) {
            Image(
                painter,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = text,
            modifier = Modifier.padding(top = 4.dp),
            color = Color(0xFF919999),
            fontSize = 12.sp,
        )
    }
}