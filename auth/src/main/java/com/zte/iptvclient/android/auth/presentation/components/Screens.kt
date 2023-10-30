package com.zte.iptvclient.android.auth.presentation.components
sealed class Screens(val route : String) {
    object Login: Screens("login_route")
    object Register: Screens("register_route")
    object Forgot : Screens("forgot_password_route")
    object CreateNewPassword_ : Screens("create_new_password_route")
    object Verify : Screens("verify_route")
}