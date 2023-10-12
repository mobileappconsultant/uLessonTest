package com.arkangel.ulessontechnicaltest.android.features.home.components

import android.widget.Toast
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.sp
import com.arkangel.ulessontechnicaltest.android.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BottomBar() {
    val context = LocalContext.current

    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(painterResource(id = R.drawable.home), "Home Icon") },
            label = { Text(stringResource(R.string.home), fontSize = 10.sp) },
            modifier = Modifier
                .semantics { testTagsAsResourceId = true }
                .testTag("HomeIcon")
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                Toast.makeText(context, R.string.not_implemented, Toast.LENGTH_SHORT).show()
            },
            icon = { Icon(painterResource(id = R.drawable.classes), "Classes Icon") },
            label = { Text(stringResource(R.string.classes), fontSize = 10.sp) },
            modifier = Modifier
                .semantics { testTagsAsResourceId = true }
                .testTag("ClassesIcon")
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                Toast.makeText(context, R.string.not_implemented, Toast.LENGTH_SHORT).show()
            },
            icon = { Icon(painterResource(id = R.drawable.subscribe), "Subscribe Icon") },
            label = { Text(stringResource(R.string.subscribe), fontSize = 10.sp) },
            modifier = Modifier
                .semantics { testTagsAsResourceId = true }
                .testTag("SubscribeIcon")
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                Toast.makeText(context, R.string.not_implemented, Toast.LENGTH_SHORT).show()
            },
            icon = { Icon(painterResource(id = R.drawable.downloads), "Downloads Icon") },
            label = { Text(stringResource(R.string.downloads), fontSize = 10.sp) },
            modifier = Modifier
                .semantics { testTagsAsResourceId = true }
                .testTag("DownloadsIcon")
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                Toast.makeText(context, R.string.not_implemented, Toast.LENGTH_SHORT).show()
            },
            icon = { Icon(painterResource(id = R.drawable.more), "More Icon") },
            label = { Text(stringResource(R.string.more), fontSize = 10.sp) },
            modifier = Modifier
                .semantics { testTagsAsResourceId = true }
                .testTag("MoreIcon")
        )
    }
}
