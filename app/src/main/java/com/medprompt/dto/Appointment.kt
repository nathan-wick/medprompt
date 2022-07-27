package com.medprompt.dto

data class Appointment (
    val datetime: String,
    val freqAmount: Number,
    val freqType: String,
    val appName: String
)