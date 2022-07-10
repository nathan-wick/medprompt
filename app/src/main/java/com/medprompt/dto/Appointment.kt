package com.medprompt.dto
// Use serialized name

import java.time.LocalDate
import java.util.*
/**
 * * A class Appointment is noun class Data Transfer Object. Inherites Notification
 * @property id the id for every Appointment : Int
 * @property name the name for every Appointment : String
 * @property completionDate the completionDate for every Appointment : Date
 * @property frequency the frequency for every Appointment : Int
 */
class Appointment(id: Int = 0,
                  name: String = "",
                  completionDate: LocalDate = LocalDate.of(2023, 1, 1),
                  frequency: Int = 0
) : Notification(id, name, completionDate, frequency) {

}