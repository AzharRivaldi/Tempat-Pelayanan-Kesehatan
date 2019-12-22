package com.azhar.tpk.network

import com.azhar.tpk.network.response.ResultDetails
import com.azhar.tpk.network.response.ResultNearby
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

interface ApiInterface {

    @GET("nearbysearch/json")
    fun json(
            @Query("key") key: String?,
            @Query("keyword") keyword: String?,
            @Query("location") location: String?,
            @Query("rankby") rankby: String?
    ): Call<ResultNearby>

    @GET("details/json")
    fun json(
            @Query("key") key: String?,
            @Query("placeid") placeid: String?
    ): Call<ResultDetails>

}