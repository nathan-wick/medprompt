package com.medprompt.dto


import java.time.LocalDate
import java.util.*
/**
 *  A class Medication is noun class Data Transfer Object.
 *  Inherites class Notification
 * @property doseSize the doseSize for every Medication : Int
 * @property doseMetricType the doseMetricType for every Medication : Int
 * @property stockSize the stockSize for every Medication : Int
 * @property stockMetricType the stockMetricType for every Medication : Int
 */
public class Medication(doseSize: Int = 0,
                        doseMetricType: Int = 0,
                        stockSize: Int = 0,
                        stockMetricType: Int = 0,
                        id: Int = 0,
                        name: String = "",
                        completionDateTime: LocalDate = LocalDate.of(2023, 1, 1),
                        frequency: Int = 0
) : Notification(id, name, completionDateTime, frequency) {

}