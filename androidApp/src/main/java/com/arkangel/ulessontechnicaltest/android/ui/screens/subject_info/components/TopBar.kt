package com.arkangel.ulessontechnicaltest.android.ui.screens.subject_info.components

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun TopBar(navigator: DestinationsNavigator) {
    Icon(
        modifier = Modifier.clickable { navigator.popBackStack() },
        imageVector = Icons.Default.ArrowBack, contentDescription = "Back",
    )
}
