package com.ddingmung.bookhi.functions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ddingmung.bookhi.model.BookInfo

@Dao
interface BookDao {
    @Insert
    fun insertBook(bookInfo: BookInfo)

    @Query("SELECT * FROM BookInfo")
    fun getAll(): List<BookInfo>

    @Query("SELECT title FROM BookInfo")
    fun getTitle(): List<String>
}