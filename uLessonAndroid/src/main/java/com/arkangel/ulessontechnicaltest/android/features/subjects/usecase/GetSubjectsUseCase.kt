package com.arkangel.ulessontechnicaltest.android.features.subjects.usecase

import android.content.res.Resources
import com.arkangel.ulessontechnicaltest.android.R
import com.arkangel.ulessontechnicaltest.android.features.subjects.models.Subject

class GetSubjectsUseCase(
    private val resources: Resources
) {
    fun getSubjects(): List<Subject> = listOf(
        Subject(
            title = resources.getString(R.string.mathematics),
            icon = R.drawable.maths_icon
        ),
        Subject(
            title = resources.getString(R.string.english),
            icon = R.drawable.english_icon
        ),
        Subject(
            title = resources.getString(R.string.biology),
            icon = R.drawable.biology_icon
        ),
        Subject(
            title = resources.getString(R.string.chemistry),
            icon = R.drawable.chemistry_icon
        ),
        Subject(
            title = resources.getString(R.string.physics),
            icon = R.drawable.physics_icon
        ),
        Subject(
            title = resources.getString(R.string.government),
            icon = R.drawable.government_icon
        ),
        Subject(
            title = resources.getString(R.string.accounting),
            icon = R.drawable.accounting_icon
        ),
        Subject(
            title = resources.getString(R.string.economics),
            icon = R.drawable.economics_icon
        ),
        Subject(
            title = resources.getString(R.string.literature),
            icon = R.drawable.literature_icon
        )
    )
}
