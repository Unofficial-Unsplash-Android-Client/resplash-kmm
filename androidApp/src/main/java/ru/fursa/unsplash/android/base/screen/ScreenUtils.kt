package ru.fursa.unsplash.android.base.screen

import android.content.res.Resources
import android.util.DisplayMetrics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Float.toPx(): Float {
    val displayMetrics = Resources.getSystem().displayMetrics
    return this * displayMetrics.density
}

fun Float.toDp(metrics: DisplayMetrics): Dp {
    return (this / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).dp
}
