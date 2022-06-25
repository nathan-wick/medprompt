package com.medprompt.dto

import java.util.*

open class Notification(var id: Int,
                        var name: String,
                        var completionDate: Date,
                        var frequency: Int
) {

}