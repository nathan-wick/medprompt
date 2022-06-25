package com.medprompt.dto

import java.util.*

class Appointment(
    id: Int,
    name: String,
    completionDate: Date,
    frequency: Int
) : Notification(id, name, completionDate, frequency) {

}