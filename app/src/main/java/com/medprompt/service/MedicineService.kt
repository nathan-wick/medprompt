package com.medprompt.service

import com.medprompt.RetrofitClientInstance
import com.medprompt.dao.IMedicationDAO
import com.medprompt.dto.Medication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class MedicineService {
    suspend fun fetchMedicines(): List<Medication>?{
       return withContext(Dispatchers.IO){
            val service=RetrofitClientInstance.retrofitInstance?.create(IMedicationDAO::class.java)
            val medicine= async {service?.getAllMedicines()}
            return@withContext medicine.await()?.awaitResponse()?.body()
        }
    }
}