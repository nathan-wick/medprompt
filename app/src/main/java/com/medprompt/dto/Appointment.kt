package com.medprompt.dto
import com.google.gson.annotations.SerializedName
// Use serialized name

import java.util.*

class Appointment(id: Int,
                  name: String,
                  completionDate: Date,
                  frequency: Int
) : Notification(id, name, completionDate, frequency) {

}