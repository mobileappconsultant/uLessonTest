package com.arkangel.ulessontechnicaltest.features.daily_login.usecase

import com.arkangel.ulessontechnicaltest.di.sharedDI
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

private const val LAST_LOGIN_DATE = "last_login_date"
class DailyLoginUseCase {
    private val settings: Settings by sharedDI.koin.inject()

    fun lastLoginDate(): Long? {
        return settings[LAST_LOGIN_DATE]
    }
    fun storeLastLoginDate(dateMillis: Long) {
        settings[LAST_LOGIN_DATE] = dateMillis
    }
}