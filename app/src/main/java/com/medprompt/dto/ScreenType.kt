package com.medprompt.dto

enum class ScreenType {
    MEDICATION,
    APPOINTMENT,
    FORM
}

inline fun <reified T : Enum<T>> getEnum(screenType: String) : ScreenType {
    return ScreenType.valueOf(screenType)
}