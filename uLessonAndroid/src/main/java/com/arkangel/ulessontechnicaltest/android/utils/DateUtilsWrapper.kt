package com.arkangel.ulessontechnicaltest.android.utils

import android.text.format.DateUtils

class DateUtilsWrapper {
    fun isToday(date: Long): Boolean = DateUtils.isToday(date)
}
