package com.azhar.tpk.network.response

import com.azhar.tpk.network.model.Results
import com.google.gson.annotations.SerializedName

data class ResultNearby(

        @SerializedName("results")
        var results: ArrayList<Results>?

)