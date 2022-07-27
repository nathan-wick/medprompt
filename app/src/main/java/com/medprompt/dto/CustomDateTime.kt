package com.medprompt.dto

import com.google.type.DateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

data class CustomDateTime(
    var year: String,
    var month: String,
    var day: String,
    var hour: String,
    var pmOrAm: String
) {
    override fun toString(): String {
        return "$month $day, $hour $pmOrAm"
    }
}
