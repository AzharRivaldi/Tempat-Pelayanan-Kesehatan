package com.azhar.tpk.networking

import com.azhar.tpk.data.response.ModelResultDetail
import com.azhar.tpk.data.response.ModelResultNearby
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Azhar Rivaldi on 18-05-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

interface ApiInterface {
    @GET("nearbysearch/json")
    fun getDataResult(@Query("key") key: String,
                      @Query("keyword") keyword: String,
                      @Query("location") location: String,
                      @Query("rankby") rankby: String?): Call<ModelResultNearby>

    @GET("details/json")
    fun getDetailResult(@Query("key") key: String,
                        @Query("placeid") placeid: String): Call<ModelResultDetail>
}