package com.optic.deliverykotlinudemy.activities.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitClient {
    fun getClient(url:String):Retrofit {
        return Retrofit.Builder()
                //* setencias basicas para crear nuestro cliente  como en el postman
                //esta estructura se saca de la importancion  retrofit mas la configuracio de dependencias
            //de la carpeta buil.gradle(app)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }}