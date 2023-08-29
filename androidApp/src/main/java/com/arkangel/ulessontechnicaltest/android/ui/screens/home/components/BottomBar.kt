package com.arkangel.ulessontechnicaltest.android.ui.screens.home.components

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.arkangel.ulessontechnicaltest.android.R

@Composable
fun BottomBar() {
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(painterResource(id = R.drawable.home), "Home Icon") },
            label = { Text(stringResource(R.string.home), fontSize = 12.sp) }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(painterResource(id = R.drawable.classes), "Classes Icon") },
            label = { Text(stringResource(R.string.classes), fontSize = 12.sp) }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(painterResource(id = R.drawable.subscribe), "Subscribe Icon") },
            label = { Text(stringResource(R.string.subscribe), fontSize = 12.sp) }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(painterResource(id = R.drawable.downloads), "Downloads Icon") },
            label = { Text(stringResource(R.string.downloads), fontSize = 12.sp) }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(painterResource(id = R.drawable.more), "More Icon") },
            label = { Text(stringResource(R.string.more), fontSize = 12.sp) }
        )
    }
}
