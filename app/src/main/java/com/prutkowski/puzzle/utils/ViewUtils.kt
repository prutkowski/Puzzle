package com.prutkowski.puzzle.utils

import android.util.DisplayMetrics
import android.util.TypedValue

class ViewUtils {
    companion object {
        fun dpToPx(dp: Float, displayMetrics: DisplayMetrics) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics).toInt()
    }
}