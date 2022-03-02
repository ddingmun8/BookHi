package com.ddingmung.bookhi.functions

import com.ddingmung.bookhi.BuildConfig
import com.ddingmung.bookhi.dataclass.BookDataTest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET(BuildConfig.ENDPOINT_SEARCH_BOOK)
    fun getInfo(
        @Header("Authorization") Authorization: String?,
        @Query("query") query: String
    ): Call<BookDataTest>
}