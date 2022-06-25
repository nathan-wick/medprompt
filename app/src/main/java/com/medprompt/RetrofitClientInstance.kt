package com.medprompt

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



/**
 * RetrofitClientInstance object bootstrap
 */
object RetrofitClientInstance {

    private var retrofit: Retrofit?= null
    private val BASE_URL="https://pkgstore.datahub.io/" // the JSON data to read
    // this is temporary, decide soon

    val retrofitInstance :Retrofit?
        get(){
            if (retrofit==null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit

        }
}