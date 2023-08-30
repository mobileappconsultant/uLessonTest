package com.arkangel.ulessontechnicaltest.android.features.home

import com.arkangel.ulessonsharedlibrary.features.daily_login.usecase.DailyLoginUseCase
import com.arkangel.ulessontechnicaltest.android.features.subjects.models.Subject
import com.arkangel.ulessontechnicaltest.android.features.subjects.usecase.GetSubjectsUseCase
import com.arkangel.ulessontechnicaltest.android.utils.DateUtilsWrapper
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

@ExperimentalCoroutinesApi
class HomeScreenViewModelTest {

    private lateinit var viewModel: HomeScreenViewModel
    private lateinit var getSubjectsUseCase: GetSubjectsUseCase
    private lateinit var dailyLoginUseCase: DailyLoginUseCase
    private lateinit var dateUtilsWrapper: DateUtilsWrapper

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        getSubjectsUseCase = mockk()
        dailyLoginUseCase = mockk()
        dateUtilsWrapper = mockk()

        every { dailyLoginUseCase.lastLoginDate() } returns null
        every { dailyLoginUseCase.storeLastLoginDate(any()) } returns Unit
        every { dateUtilsWrapper.isToday(any()) } returns true
        viewModel = HomeScreenViewModel(getSubjectsUseCase, dailyLoginUseCase, dateUtilsWrapper)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test loadSubjects updates subjects`() {
        val mockSubjects = listOf(
            Subject("Mathematics", Random.nextInt()),
            Subject("English", Random.nextInt())
        )

        coEvery { getSubjectsUseCase.getSubjects() } returns mockSubjects

        viewModel.loadSubjects()

        println("ViewModel sub = ${viewModel.subjects}")

        viewModel.subjects.forEachIndexed { index, subject ->
            assert(subject == mockSubjects[index])
        }
    }

    @Test
    fun `test showReward is set if last login is null`() {
        every { dailyLoginUseCase.lastLoginDate() } returns null

        viewModel = HomeScreenViewModel(getSubjectsUseCase, dailyLoginUseCase, dateUtilsWrapper)

        assert(viewModel.showReward.value)
    }

    @Test
    fun `test showReward is not set if last login is today`() {
        every { dailyLoginUseCase.lastLoginDate() } returns System.currentTimeMillis()

        viewModel = HomeScreenViewModel(getSubjectsUseCase, dailyLoginUseCase, dateUtilsWrapper)

        assert(viewModel.showReward.value.not())
    }
}
