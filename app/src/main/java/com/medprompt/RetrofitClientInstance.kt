package com.medprompt

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



/**
 * RetrofitClientInstance object bootstrap boilerplate code for future JSON implementation
 */
object RetrofitClientInstance {

    private var retrofit: Retrofit?= null
    private val BASE_URL="https://api.fda.gov/"

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