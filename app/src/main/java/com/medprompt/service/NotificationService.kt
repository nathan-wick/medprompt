package com.medprompt.service
/**
 * CountryService service package which is business logic and uses coroutines to invoke Retrofit
 */
import com.medprompt.dto.Notification
import com.medprompt.RetrofitClientInstance
import com.medprompt.dao.INotificationDAO
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class NotificationService {

    suspend fun fetchNotification(): List<Notification>? {

        return withContext(Dispatchers.ID)
        {
            val Notifications = async { service?.fetchNotifaction() }
        }
        
    }




}