package com.ddingmung.bookhi.util

import com.ddingmung.bookhi.BuildConfig

class Constants {
    companion object{
        const val BASE_URL = "https://dapi.kakao.com"

        // 개인 API 사용
        const val AUTH_HEADER = "KakaoAK aa6e4714d6d7c7d6bdd9833afc755946"
        //const val AUTH_HEADER = "KakaoAK " + BuildConfig.API_KEY
    }
}