package com.medprompt.dto

import java.time.LocalDate

/**
 * * A class Notification is noun class Data Transfer Object.
 * @property id the id for every Notification : Int
 * @property name the name for every Notification : String
 * @property completionDate the completionDate for every Notification : Date
 * @property frequency the frequency for every Notification : Int
 *
 */
open class Notification(
    var id: Int,
    var name: String,
    var completionDate: LocalDate,
    var frequency: Int
)