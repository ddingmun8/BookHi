package com.ddingmung.bookhi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookInfo(
    /*책 정보*/
    @PrimaryKey val bookId: Int?,
    @ColumnInfo var thumbnail: ByteArray?,
    @ColumnInfo var title: String,
    @ColumnInfo var authors: String,
    @ColumnInfo var publisher: String,
    @ColumnInfo var isbn: String,

    /*내가 기록한 정보*/
    /*@ColumnInfo var startDt: String,
    @ColumnInfo var endDt: String,
    @ColumnInfo var memo: String,
    @ColumnInfo var rate: Double*/
)
