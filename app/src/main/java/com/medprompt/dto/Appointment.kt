package com.medprompt.dto
// Use serialized name

import java.time.LocalDate
import java.util.*

data class Appointment (
    val datetime: String,
    val freqAmount: Number,
    val freqType: String,
    val appName: String
)