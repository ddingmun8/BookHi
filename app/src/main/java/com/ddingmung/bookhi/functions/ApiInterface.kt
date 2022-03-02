package com.ddingmung.bookhi.functions

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {
    @GET("/v3/search/book/{query}")
    fun getSearchResult(
        @Header("Authorization") id: String?,
        @Query("query") query: String?
    ): Call<String?>?
}