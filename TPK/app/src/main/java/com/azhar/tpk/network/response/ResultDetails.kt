package com.azhar.tpk.network.response

import com.azhar.tpk.network.model.Result
import com.google.gson.annotations.SerializedName

data class ResultDetails(

        @SerializedName("result")
        var result: Result

)