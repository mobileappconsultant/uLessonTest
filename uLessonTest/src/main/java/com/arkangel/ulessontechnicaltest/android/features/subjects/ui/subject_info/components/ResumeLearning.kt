package com.arkangel.ulessontechnicaltest.android.features.subjects.ui.subject_info.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import com.arkangel.ulessontechnicaltest.android.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ResumeLearning(
    title: String,
    subtitle: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .semantics { testTagsAsResourceId = true }
            .testTag("resumeLearning")
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .background(Color(0xFFEA7052))
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 18.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.resume_icon),
            contentDescription = "Resume Icon",
            tint = Color.Unspecified
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                title, style = MaterialTheme.typography.h6,
                color = Color.White
            )
            Text(
                subtitle, style = MaterialTheme.typography.subtitle2,
                color = Color.White
            )
        }
    }
}
