package com.medprompt.dto

import com.google.type.DateTime

data class CustomDateTime(
    val year: String,
    val month: String,
    val day: String,
    val hour: String,
    val pmOrAm: String
) {

    override fun toString(): String {
        return "$month $day, $hour $pmOrAm"
    }
}
