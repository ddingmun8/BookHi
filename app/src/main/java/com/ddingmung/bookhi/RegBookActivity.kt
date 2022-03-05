package com.ddingmung.bookhi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.room.Room
import com.ddingmung.bookhi.functions.BookDB
import com.ddingmung.bookhi.model.BookInfo
import kotlinx.android.synthetic.main.activity_reg_book.*

class RegBookActivity : AppCompatActivity() {
    lateinit var db: BookDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_book)

        //DB 생성
        db = Room.databaseBuilder(this, BookDB::class.java, "BookDB").allowMainThreadQueries().build()

        val btnSave : Button = findViewById<Button>(R.id.btnSave)
        btnSave.setOnClickListener {
            if (etTitle.text.isNotEmpty()){
                regBook()
                Log.d("testDB", db.getDao().getAll().toString())

            }else{
                Toast.makeText(this, "제목은 필수 입력 사항입니다.", Toast.LENGTH_SHORT).show()
            }

        }

    }

    fun regBook(){
        val bookId = db.getDao().getTitle().size + 1
        db.getDao().insertBook(BookInfo(bookId, etThumbnail.text.toString(), etTitle.text.toString(), etAuthors.text.toString(), etPublisher.text.toString(), etIsbn.text.toString()))
    }
}
