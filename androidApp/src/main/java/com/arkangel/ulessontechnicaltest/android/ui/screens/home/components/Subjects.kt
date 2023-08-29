package com.arkangel.ulessontechnicaltest.android.ui.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arkangel.ulessontechnicaltest.android.features.subjects.models.Subject

@Composable
fun Subjects(
    modifier: Modifier = Modifier,
    subjects: List<Subject>,
    onSubjectClick: (subject: Subject) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(subjects) { subject ->
            SubjectItem(subject) { onSubjectClick(subject) }
        }
    }
}

@Composable
fun SubjectItem(subject: Subject, onClick: () -> Unit) {
    Column(
        modifier = Modifier.clickable { onClick() },
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(subject.icon),
            contentDescription = "Subject Icon",
            tint = Color.Unspecified
        )
        Text(subject.title, style = MaterialTheme.typography.subtitle2)
    }
}
