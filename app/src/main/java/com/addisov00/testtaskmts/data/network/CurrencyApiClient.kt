package com.addisov00.testtaskmts.data.network

import com.addisov00.testtaskmts.common.Constants
import com.addisov00.testtaskmts.data.network.models.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface CurrencyApiClient {

    @GET("latest")
    suspend fun getCurrency(
        @Query("access_key")
        apiKey: String = Constants.API_KEY
    ): CurrencyResponse
}