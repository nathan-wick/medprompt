package com.medprompt.dao;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Appointment2ApproachDAO {

    @GET("when you decided on your base url"
    )
    Call<Appointment2ApproachDAO> getALLData();
}
