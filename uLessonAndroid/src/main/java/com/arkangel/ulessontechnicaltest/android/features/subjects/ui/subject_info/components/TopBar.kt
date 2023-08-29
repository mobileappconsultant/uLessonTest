package com.arkangel.ulessontechnicaltest.android.features.subjects.ui.subject_info.components

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navigator: DestinationsNavigator) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        title = {},
        navigationIcon = {
            Icon(
                modifier = Modifier.clickable { navigator.popBackStack() },
                imageVector = Icons.Default.ArrowBack, contentDescription = "Back",
            )
        },
    )
}
