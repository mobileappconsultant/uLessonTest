package com.arkangel.ulessontechnicaltest.android.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arkangel.ulessontechnicaltest.android.R

@Composable
fun Ads() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        painter = painterResource(id = R.drawable.ad),
        contentDescription = "Ad"
    )
}