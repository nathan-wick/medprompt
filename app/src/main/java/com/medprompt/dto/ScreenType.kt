package com.medprompt.dto

/**
 * I use this enum in home feed. WHen a user clicks on a row,
 * I check if the document id is related to med, app, or form
 */
enum class ScreenType {
    MEDICATION,
    APPOINTMENT,
    FORM
}

inline fun <reified T : Enum<T>> getEnum(screenType: String) : ScreenType {
    return ScreenType.valueOf(screenType)
}