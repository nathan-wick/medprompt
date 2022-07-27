package com.medprompt.dto

data class CustomDateTime(
    var year: String,
    var month: String,
    var day: String,
    var hour: String,
    var pmOrAm: String
) {
    // using this in Home Feed items
    override fun toString(): String {
        return "$month $day, $hour $pmOrAm"
    }
}
