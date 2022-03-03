package com.ddingmung.bookhi.model

import com.google.gson.annotations.SerializedName

data class BookSearchResponse(
    @SerializedName("meta")
    val metaData: MetaData?,

    @SerializedName("documents")
    var documents: MutableList<KakaoBook>?
)
