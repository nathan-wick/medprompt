package com.medprompt.dto

import java.util.*
import kotlin.collections.ArrayList


class Form(questions: ArrayList<String>,
           id: Int,
           name: String,
           completionDate: Date,
           frequency: Int
) : Notification(id, name, completionDate, frequency) {

}