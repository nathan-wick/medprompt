package com.medprompt.dto

import java.util.*

class Medication(doseSize: Int,
                 doseMetricType: Int,
                 stockSize: Int,
                 stockMetricType: Int,
                 id: Int,
                 name: String,
                 completionDateTime: Date,
                 frequency: Int
) : Notification(id, name, completionDateTime, frequency) {

}