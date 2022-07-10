package com.medprompt.dao
import com.medprompt.dto.Appointment
import com.medprompt.dto.Form
import com.medprompt.dto.Medication
import com.medprompt.dto.MetricTypes
import com.medprompt.dto.Notification
import retrofit2.Call
import retrofit2.http.GET
/**
 * Interface for RetrofitClientInstance
 */

interface IMedicationDAO {    // Implement the @GET here to get json data
    @GET("/drug/label.json?count=openfda.brand_name.exact&limit=1000")
    fun getAllMedicines(): Call<ArrayList<Medication>>
}