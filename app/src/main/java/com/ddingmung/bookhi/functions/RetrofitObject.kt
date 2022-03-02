package com.ddingmung.bookhi.functions

import com.ddingmung.bookhi.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.URL_KAKAO)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiService():ApiService{
        return getRetrofit().create(ApiService::class.java)
    }
}