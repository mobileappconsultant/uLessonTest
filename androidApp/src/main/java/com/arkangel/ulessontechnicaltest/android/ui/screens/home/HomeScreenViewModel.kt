package com.arkangel.ulessontechnicaltest.android.ui.screens.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arkangel.ulessontechnicaltest.android.features.subjects.models.Subject
import com.arkangel.ulessontechnicaltest.android.features.subjects.usecase.GetSubjectsUseCase
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val getSubjectsUseCase: GetSubjectsUseCase
) : ViewModel() {
    val searchQuery = mutableStateOf("")
    val subjects = mutableStateListOf<Subject>()

    fun loadSubjects() {
        viewModelScope.launch {
            subjects.clear()
            subjects.addAll(getSubjectsUseCase.getSubjects())
        }
    }
}
