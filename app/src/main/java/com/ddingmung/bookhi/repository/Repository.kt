package com.ddingmung.bookhi.repository

import com.ddingmung.bookhi.model.BookSearchResponse
import com.ddingmung.bookhi.api.RetrofitInstance
import retrofit2.Response

class Repository {

    suspend fun searchImage(query : String, sort : String) : Response<BookSearchResponse> {
        return RetrofitInstance.api.searchBook(query = query, sort = sort, page = 1, size =50)
    }
}