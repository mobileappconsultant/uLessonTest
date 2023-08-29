package com.arkangel.ulessontechnicaltest.android.features.subjects.ui.subject_info

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arkangel.ulessontechnicaltest.UIState
import com.arkangel.ulessontechnicaltest.android.R
import com.arkangel.ulessontechnicaltest.android.features.destinations.SubjectInfoScreenDestination
import com.arkangel.ulessontechnicaltest.android.features.subjects.models.Subject
import com.arkangel.ulessontechnicaltest.features.chapter.model.Chapter
import com.arkangel.ulessontechnicaltest.features.chapter.usecase.GetChaptersUseCase
import com.arkangel.ulessontechnicaltest.utils.LearningProgress
import com.arkangel.ulessontechnicaltest.utils.ResumeLearningUseCase
import kotlinx.coroutines.launch

class SubjectInfoScreenViewModel(
    state: SavedStateHandle,
    private val getChaptersUseCase: GetChaptersUseCase,
    private val resumeLearningUseCase: ResumeLearningUseCase,
    private val application: Application
) : ViewModel() {
    private val navArgs = SubjectInfoScreenDestination.argsFrom(state)
    val resumeLearningProgress = mutableStateOf<LearningProgress?>(null)

    val subject: Subject
        get() = navArgs.subject

    val uiState = mutableStateOf(UIState.IDLE)
    val chapters = mutableStateListOf<Chapter>()
    val searchQuery = mutableStateOf("")

    fun loadChapters() {
        uiState.value = UIState.LOADING
        viewModelScope.launch {
            val response = getChaptersUseCase.getChapters(subject.title)
            if (response.hasError) {
                Toast.makeText(application, response.error?.message ?: application.getString(R.string.unknown_error), Toast.LENGTH_SHORT).show()
            } else {
                chapters.clear()
                chapters.addAll(response.value ?: listOf())
            }

            resumeLearningProgress.value = resumeLearningUseCase.getSavedProgress()

            uiState.value = UIState.IDLE
        }
    }
}
