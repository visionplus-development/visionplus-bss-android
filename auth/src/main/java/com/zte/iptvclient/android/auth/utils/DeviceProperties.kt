package com.zte.iptvclient.android.auth.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp

enum class DeviceProperties {
    LARGE(20f, 16f),
    MEDIUM(18f, 14f),
    SMALL(16f, 12f);

    private val tabletValue: Float
    private val mobileValue: Float

    constructor(tabletValue: Float, mobileValue: Float) {
        this.tabletValue = tabletValue
        this.mobileValue = mobileValue
    }

    fun getFontSize(isTablet: Boolean): Float {
        return if (isTablet) tabletValue else mobileValue
    }

    companion object {
        fun getPadding(padding: Dp, isTablet: Boolean): PaddingValues {
            val adjustedPadding = if (isTablet) {
                padding * 2
            } else {
                padding
            }

            return PaddingValues(
                start = adjustedPadding,
                end = adjustedPadding,
                top = adjustedPadding / 2,
                bottom = adjustedPadding / 2
            )
        }
    }
}