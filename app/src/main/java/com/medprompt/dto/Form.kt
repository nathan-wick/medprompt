package com.medprompt.dto

import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList
/**
 * * A class Form is noun class Data Transfer Object. Inherites Notification
 * @property questions the questions for every Form: ArrayList of Strings
 * @property id the id for every Form : Int
 * @property name the name for every Form : String
 * @property completionDate the completionDate for every Form : Date
 * @property frequency the frequency for every Form : Int
 */
public class Form(questions: ArrayList<String> = ArrayList<String>(),
                  id: Int = 0,
                  name: String = "",
                  completionDate: LocalDate = LocalDate.of(2023, 1, 1),
                  frequency: Int = 0
) : Notification(id, name, completionDate, frequency) {

}