package com.ddingmung.bookhi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookInfo(
    @PrimaryKey val bookId: Int?,
    @ColumnInfo var thumbnail: String,
    @ColumnInfo var title: String,
    @ColumnInfo var authors: String,
    @ColumnInfo var publisher: String,
    @ColumnInfo var isbn: String,
)
