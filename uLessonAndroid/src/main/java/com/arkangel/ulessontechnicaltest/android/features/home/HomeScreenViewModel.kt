package com.arkangel.ulessontechnicaltest.android.features.home

import android.text.format.DateUtils
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arkangel.ulessonsharedlibrary.features.daily_login.usecase.DailyLoginUseCase
import com.arkangel.ulessontechnicaltest.android.features.subjects.models.Subject
import com.arkangel.ulessontechnicaltest.android.features.subjects.usecase.GetSubjectsUseCase
import kotlinx.coroutines.launch
import java.util.Calendar

class HomeScreenViewModel(
    private val getSubjectsUseCase: GetSubjectsUseCase,
    dailyLoginUseCase: DailyLoginUseCase
) : ViewModel() {
    val searchQuery = mutableStateOf("")
    val subjects = mutableStateListOf<Subject>()
    val showReward = mutableStateOf(false)

    init {
        val lastLoginDate = dailyLoginUseCase.lastLoginDate()
        if (lastLoginDate == null || !DateUtils.isToday(lastLoginDate)) {
            showReward.value = true
            dailyLoginUseCase.storeLastLoginDate(Calendar.getInstance().timeInMillis)
        }
    }

    fun loadSubjects() {
        viewModelScope.launch {
            subjects.clear()
            subjects.addAll(getSubjectsUseCase.getSubjects())
        }
    }
}
