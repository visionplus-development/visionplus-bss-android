package com.zte.iptvclient.android.auth.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
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
internal fun LoginScreen(
    clickGoogle: () -> Unit,
    callbackManager: CallbackManager,
    onLoginResult: (loginResult: LoginResult?) -> Unit,
    clickBack: () -> Unit,
) {
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            CustomToolbar(
                title = stringResource(id = R.string.login),
                onBackClick = clickBack
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 60.dp)
                    .background(Color.Black),
            ) {
                TextSwitchRegister()
                TabLayout(
                    phoneNumber = phoneNumber,
                    password = password,
                    onPhoneNumberChange = {
                        phoneNumber = it
                    },
                    onPasswordChange = {
                        password = it
                    })
                TextSocialLogin()
                GridSocialLoginScreen(clickGoogle, callbackManager, onLoginResult)
                TextTNC()
            }
        },
        bottomBar = {
            ButtonLogin(phoneNumber, password)
        },
    )
}

@Composable
internal fun ButtonLogin(phoneNumber: String, password: String) {
    val isEnable = phoneNumber.isNotEmpty() && password.isNotEmpty()
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 56.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            ),
        enabled = isEnable,
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = Color(0x3307E3D0),
            containerColor = Color(0xFF07E3D0)
        ),
        shape = RoundedCornerShape(8.dp),
        onClick = {
        }) {
        Text(
            text = stringResource(id = R.string.login),
            fontSize = 14.sp,
            color = Color(0xFF1F1F1F)
        )
    }
}

@Composable
internal fun TextSwitchRegister() {
    Row(modifier = Modifier.padding(all = 16.dp)) {
        Text(
            text = stringResource(id = R.string.label_created_account) + " ",
            color = Color(0xFF7D7D7D),
            fontSize = 12.sp
        )
        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.register)),
            style = TextStyle(color = Color(0xFF07E3D0), fontSize = 13.sp),
            onClick = {
            }
        )
    }
}

@Composable
internal fun TextSocialLogin() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
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
            fontSize = 12.sp,
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
internal fun TextTNC() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "By logging in, I agree with",
            color = Color(0xFF7D7D7D),
            fontSize = 12.sp
        )
        Row {
            ClickableText(
                text = AnnotatedString("Terms & Conditions "),
                style = TextStyle(
                    color = Color(0xFF07E3D0),
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.Underline
                ),
                onClick = {
                }
            )
            Text(
                text = "and ",
                color = Color(0xFF7D7D7D),
                fontSize = 12.sp
            )
            ClickableText(
                text = AnnotatedString("Privacy Policy "),
                style = TextStyle(
                    color = Color(0xFF07E3D0),
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.Underline
                ),
                onClick = {
                }
            )
            Text(
                text = "from Vision+ ",
                fontSize = 12.sp,
                color = Color(0xFF7D7D7D),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CustomToolbar(
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
                    fontSize = 14.sp
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
internal fun TabLayout(
    phoneNumber: String,
    password: String,
    onPhoneNumberChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    val isActive = remember { mutableIntStateOf(0) }

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    val tabRowItems = listOf(
        ImageTabItem(
            text = stringResource(id = R.string.label_phone_number),
            screen = { PhoneLogin(phoneNumber, password, onPhoneNumberChange, onPasswordChange) }
        ),
        ImageTabItem(
            text = stringResource(id = R.string.label_email),
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

internal data class ImageTabItem(
    val text: String,//Tab Title
    val screen: @Composable () -> Unit//Tab Screen(can also take params)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PhoneLogin(
    phoneNumber: String,
    password: String,
    onPhoneNumberChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 12.dp),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = "Enter Phone Number",
            textAlign = TextAlign.Start,
            color = Color(0xFFF5F5F5),
            fontSize = 14.sp
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
                .height(50.dp),
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
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.indonesia_flag),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "+62",
                        color = Color(0xFF919999),
                        fontSize = 12.sp
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        tint = Color.White,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 4.dp, top = 4.dp, end = 8.dp, bottom = 4.dp)
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
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = "Enter Password",
            textAlign = TextAlign.Start,
            color = if (phoneNumber.isNullOrEmpty()) Color(0xFF919999) else Color(0xFFF5F5F5),
            fontSize = 14.sp
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFF202020),
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            textStyle = TextStyle(
                color = Color(0xFFFFFFFF),
                fontSize = 12.sp
            ),
            value = password,
            enabled = phoneNumber.isNotEmpty(),
            singleLine = true,
            placeholder = {
                Text(
                    text = "Password",
                    fontSize = 12.sp,
                    color = Color(0xFF919999)
                )
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            onValueChange = {
                onPasswordChange(it)
            },

            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            trailingIcon = {
                val image = if (passwordVisible)
                    painterResource(id = R.drawable.visibility_on)
                else painterResource(id = R.drawable.visibility_off)

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = image,
                        "icon",
                        Modifier.size(25.dp),
                        tint = Color(0xFF919999)
                    )
                }
            }
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.label_forgot_password),
            textAlign = TextAlign.End,
            color = Color(0xFF919999),
            fontSize = 12.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EmailLogin() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 12.dp),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = "Enter Email",
            textAlign = TextAlign.Start,
            color = Color(0xFFF5F5F5),
            fontSize = 14.sp
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = "Enter Password",
            textAlign = TextAlign.Start,
            color = Color(0xFFF5F5F5),
            fontSize = 14.sp
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 42.dp),
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
internal fun GridSocialLoginScreen(
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
internal fun GridItem(text: String, painter: Painter, modifier: Modifier = Modifier, onClick: () -> Unit) {
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