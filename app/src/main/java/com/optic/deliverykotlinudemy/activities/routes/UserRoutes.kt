package com.optic.deliverykotlinudemy.activities.routes

import com.optic.deliverykotlinudemy.activities.models.ResponseHttp
import com.optic.deliverykotlinudemy.activities.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRoutes {
    @POST("users/create")
    fun register(@Body user: User):Call<ResponseHttp>

}