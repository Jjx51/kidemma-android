package com.kidemma.common.extensions

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun RowScope.KidemmaSpacer(width: Dp, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.width(width))
}

@Composable
fun RowScope.KidemmaWeightSpacer(weight: Float = 1f, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.weight(weight))
}

@Composable
fun ColumnScope.KidemmaSpacer(height: Dp, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(height))
}

@Composable
fun ColumnScope.KidemmaWeightSpacer(weight: Float = 1f, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.weight(weight))
}

@Composable
fun LazyItemScope.KidemmaSpacer(padding: Dp, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.size(padding))
}

@Composable
fun LazyGridItemScope.KidemmaSpacer(padding: Dp, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.size(padding))
}

fun LazyListScope.KidemmaSpacer(height: Dp) {
    item(key = "spacer_${height.value}", contentType = "spacer") {
        Spacer(modifier = Modifier.height(height))
    }
}

fun LazyGridScope.KidemmaSpacer(height: Dp) {
    item(key = "spacer_${height.value}", contentType = "spacer") {
        Spacer(modifier = Modifier.height(height))
    }
}