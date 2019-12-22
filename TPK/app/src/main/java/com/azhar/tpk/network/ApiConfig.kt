package com.azhar.tpk.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

class ApiConfig {

    companion object {
        const val BASE_URL = "https://maps.googleapis.com/maps/api/place/"
        const val API_KEY = "YOUR API KEY"
    }

    private fun retrofit(): Retrofit {

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }

    fun instance(): ApiInterface {
        return retrofit().create(ApiInterface::class.java)
    }

}
