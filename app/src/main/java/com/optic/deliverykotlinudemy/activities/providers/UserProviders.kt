package com.optic.deliverykotlinudemy.activities.providers

import com.optic.deliverykotlinudemy.activities.api.ApiRoutes
import com.optic.deliverykotlinudemy.activities.models.ResponseHttp
import com.optic.deliverykotlinudemy.activities.models.User
import com.optic.deliverykotlinudemy.activities.routes.UserRoutes
import retrofit2.Call


class UsersProviders {
    private var usersRoutes: UserRoutes? =null

    init {
        val api = ApiRoutes()
        usersRoutes = api.getUsersRoutes()
    }
    fun register(user: User):Call<ResponseHttp>?{
        return usersRoutes?.register(user)
    }
}