package com.arkangel.ulessontechnicaltest.android.features.lesson_player.components

import android.text.format.DateUtils
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arkangel.ulessonsharedlibrary.features.bookmark.model.Bookmark
import com.arkangel.ulessontechnicaltest.android.R

@Composable
fun BookmarkList(
    modifier: Modifier = Modifier,
    bookmarks: List<Bookmark>,
    onSelectBookmark: (bookmark: Bookmark) -> Unit,
    onDeleteBookmark: (bookmark: Bookmark) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.bookmarks), style = MaterialTheme.typography.h6,
            )
        }
        item {
            Divider()
        }
        items(bookmarks) { bookmark ->
            BookmarkItem(bookmark = bookmark, onClick = { onSelectBookmark(bookmark) }) {
                onDeleteBookmark(bookmark)
            }
        }
    }
}

@Composable
fun BookmarkItem(bookmark: Bookmark, onClick: () -> Unit, onDelete: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Text(DateUtils.formatElapsedTime(bookmark.timestamp / 1000))
        Card(
            modifier = Modifier
                .clickable { onClick() }
                .weight(1f),
            backgroundColor = Color.White,
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp),
                text = bookmark.note
            )
        }
        Icon(
            modifier = Modifier.clickable {
                onDelete()
            },
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete Icon",
        )
    }
}
