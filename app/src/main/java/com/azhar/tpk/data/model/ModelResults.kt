package com.azhar.tpk.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Azhar Rivaldi on 06-06-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

class ModelResults : Serializable {

    @SerializedName("geometry")
    val modelGeometry: ModelGeometry? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("vicinity")
    val vicinity: String? = null

    @SerializedName("place_id")
    val placeId: String? = null

}