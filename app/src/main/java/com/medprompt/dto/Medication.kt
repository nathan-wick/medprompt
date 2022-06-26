package com.medprompt.dto

import java.util.*
/**
 *  A class Medication is noun class Data Transfer Object.
 *  Inherites class Notification
 * @property doseSize the doseSize for every Medication : Int
 * @property doseMetricType the doseMetricType for every Medication : Int
 * @property stockSize the stockSize for every Medication : Int
 * @property stockMetricType the stockMetricType for every Medication : Int
 */
public class Medication(doseSize: Int,
                 doseMetricType: Int,
                 stockSize: Int,
                 stockMetricType: Int,
                 id: Int,
                 name: String,
                 completionDateTime: Date,
                 frequency: Int
) : Notification(id, name, completionDateTime, frequency) {

}