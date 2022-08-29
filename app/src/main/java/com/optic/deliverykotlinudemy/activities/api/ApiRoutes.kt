package com.optic.deliverykotlinudemy.activities.api

import com.optic.deliverykotlinudemy.activities.routes.UserRoutes


class ApiRoutes {
    val API_URL ="http://192.168.1.120:3000/api/"
    val retrofit = RetroFitClient()
    fun getUsersRoutes():UserRoutes{
        return  retrofit.getClient(API_URL).create(UserRoutes::class.java)
    }
}