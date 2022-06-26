package com.medprompt.dto
import com.google.gson.annotations.SerializedName
// Use serialized name

import java.util.*
/**
 * * A class Appointment is noun class Data Transfer Object. Inherites Notification
 * @property id the id for every Appointment : Int
 * @property name the name for every Appointment : String
 * @property completionDate the completionDate for every Appointment : Date
 * @property frequency the frequency for every Appointment : Int
 */
class Appointment(id: Int,
                  name: String,
                  completionDate: Date,
                  frequency: Int
) : Notification(id, name, completionDate, frequency) {

}