package com.ddingmung.bookhi.functions

import com.ddingmung.bookhi.dataclass.BookDataTest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    //GET 예제
    @GET("posts/1")
    fun getUser(): Call<BookDataTest>

    @GET("posts/{page}")
    fun getUserPage(@Path("page") page: String): Call<BookDataTest>

    @GET("/v3/search/book/{query}")
    fun getBookInfo(
        @Header("Authorization") Authorization: String?,
        @Query("query") query: String?
    ): Call<BookDataTest>

    /*@GET("/v3/search/book/{query}")
    fun getBookInfo(
        @Header("Authorization") id: String?,
        @Query("query") query: String?
    ): Call<String?>?*/

}