package com.arkangel.ulessontechnicaltest.android.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.arkangel.ulessontechnicaltest.android.DEFAULT_MARGIN
import com.arkangel.ulessontechnicaltest.android.DEFAULT_PADDING
import com.arkangel.ulessontechnicaltest.android.R
import com.arkangel.ulessontechnicaltest.android.ui.screens.destinations.SubjectInfoScreenDestination
import com.arkangel.ulessontechnicaltest.android.ui.screens.home.components.Ads
import com.arkangel.ulessontechnicaltest.android.ui.screens.home.components.BottomBar
import com.arkangel.ulessontechnicaltest.android.ui.screens.home.components.SearchBox
import com.arkangel.ulessontechnicaltest.android.ui.screens.home.components.Subjects
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    val viewModel: HomeScreenViewModel = koinViewModel()
    var searchQuery by remember { viewModel.searchQuery }
    val subjects = remember { viewModel.subjects }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.loadSubjects()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = { BottomBar() },
        backgroundColor = MaterialTheme.colors.surface
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(DEFAULT_PADDING),
            verticalArrangement = Arrangement.spacedBy(DEFAULT_MARGIN)
        ) {
            Ads()

            SearchBox(
                value = searchQuery,
                placeholder = { Text(stringResource(R.string.what_would_you_like_to_learn)) },
            ) { searchQuery = it }

            Subjects(
                modifier = Modifier.weight(1f),
                subjects.toList()
            ) { subject ->
                if (subject.title != context.getString(R.string.biology)) {
                    Toast.makeText(context,
                        context.getString(R.string.only_biology_is_available_at_the_moment),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@Subjects
                }
                navigator.navigate(SubjectInfoScreenDestination(subject))
            }
        }
    }
}