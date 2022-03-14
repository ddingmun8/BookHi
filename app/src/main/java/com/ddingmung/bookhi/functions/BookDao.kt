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

    @Query("SELECT startDt FROM BookInfo")
    fun getStartDt(): List<String>

    /*@Query("SELECT * FROM BookInfo order by bookId limit 5")
    fun getTopBookList(): List<String>*/
}