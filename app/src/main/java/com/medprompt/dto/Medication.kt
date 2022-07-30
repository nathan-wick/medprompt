package com.medprompt.dto


import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.util.*

/**
 *  A class Medication is noun class Data Transfer Object.
 *  Inherites class Notification
 * @property doseSize the doseSize for every Medication : Int
 * @property doseMetricType the doseMetricType for every Medication : Int
 * @property stockSize the stockSize for every Medication : Int
 * @property stockMetricType the stockMetricType for every Medication : Int
 */
data class Medication(
    val datetime: String,
    val freqAmount: Number,
    val freqType: String,
    val doseSize: Number,
    val dozeSizeUnit: String,
    val stockSize: Number,
    val stockSizeUnit: String,
    val medName: String
)