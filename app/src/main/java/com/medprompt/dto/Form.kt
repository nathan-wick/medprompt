package com.medprompt.dto

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
public class Form(questions: ArrayList<String>, id: Int, name: String, completionDate: Date, frequency: Int) : Notification(id, name, completionDate, frequency)
{

}