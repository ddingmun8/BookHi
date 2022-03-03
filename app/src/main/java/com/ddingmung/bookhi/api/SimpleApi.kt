package com.ddingmung.bookhi.api

import com.ddingmung.bookhi.model.BookSearchResponse
import com.ddingmung.bookhi.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SimpleApi {
    @GET("v3/search/book")
    suspend fun searchBook(
        @Header("Authorization") apiKey: String = Constants.AUTH_HEADER,
        @Query("query") query : String,
        @Query("sort") sort : String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ) : Response<BookSearchResponse>
}