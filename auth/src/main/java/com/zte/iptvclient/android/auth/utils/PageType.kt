package com.zte.iptvclient.android.auth.utils

internal enum class PageType(type: String) {
    LOGIN("login"),
    REGISTER("register"),
    RESET_PASSWORD("reset_password"),
    CHANGE_PASSWORD("change_password"),
    OTP("otp"),
    EMAIL_VERIFICATION("email_verification")
}