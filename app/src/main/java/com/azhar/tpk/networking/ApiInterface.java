package com.azhar.tpk.networking;

import com.azhar.tpk.data.response.ModelResultDetail;
import com.azhar.tpk.data.response.ModelResultNearby;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Azhar Rivaldi on 18-05-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

public interface ApiInterface {

    @GET("nearbysearch/json")
    Call<ModelResultNearby> getDataResult(@Query("key") String key,
                                          @Query("keyword") String keyword,
                                          @Query("location") String location,
                                          @Query("rankby") String rankby);

    @GET("details/json")
    Call<ModelResultDetail> getDetailResult(@Query("key") String key,
                                            @Query("placeid") String placeid);

}
