package com.ddingmung.bookhi.functions

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ddingmung.bookhi.model.BookInfo

@Database(entities = [BookInfo::class], version = 1)
abstract class BookDB: RoomDatabase() {
    abstract fun getDao() : BookDao
}