package com.arkangel.ulessontechnicaltest.android.features.subjects.usecase

import android.content.res.Resources
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetSubjectsUseCaseTest {

    private lateinit var getSubjectsUseCase: GetSubjectsUseCase
    private lateinit var mockResources: Resources

    @Before
    fun setup() {
        mockResources = mockk()
        getSubjectsUseCase = GetSubjectsUseCase(mockResources)
    }

    @Test
    fun `test getSubjects returns correct list of subjects`() {
        // Given
        val mockMathematics = "Mathematics"
        every { mockResources.getString(any()) } returns mockMathematics

        // When
        val subjects = getSubjectsUseCase.getSubjects()

        // Then
        assertTrue(subjects.isNotEmpty())
    }
}
