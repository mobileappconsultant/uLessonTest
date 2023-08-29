package com.arkangel.ulessontechnicaltest.utils

data class ApiResponse<T>(
    val value: T?,
    val error: Throwable? = null
) {
    val hasError: Boolean
        get() = value == null
}
