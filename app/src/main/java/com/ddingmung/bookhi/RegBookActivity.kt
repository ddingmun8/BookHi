package com.ddingmung.bookhi

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.ddingmung.bookhi.functions.BookDB
import com.ddingmung.bookhi.model.BookInfo
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_reg_book.*
import java.io.ByteArrayOutputStream

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
                //저장 누르면 바텀 다이얼로그
                val dialog: BottomSheetDialog = BottomSheetDialog(this)
                dialog.setContentView(R.layout.reg_bottom_sheet_layout)
                val btnStartDt = dialog.findViewById<TextView>(R.id.btnStartDt)
                btnStartDt?.setOnClickListener {
                    Toast.makeText(this, "내용을 클릭하였습니다", Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                }
                dialog.show()

                /*regBook()
                //if문으로 성공일 때 Toast 추가해야해
                Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show()
                Log.d("testDB", db.getDao().getAll().toString())
                Log.d("testDB", db.getDao().getTitle().toString())
                finish()*/
            }else{
                Toast.makeText(this, "제목은 필수 입력 사항입니다.", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun drawableToByteArray(drawable: Drawable?): ByteArray?{
        val bitmapDrawable = drawable as BitmapDrawable?
        val bitmap = bitmapDrawable?.bitmap
        val stream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()

        return byteArray
    }

    fun regBook(){
        val bookId = db.getDao().getTitle().size + 1
        db.getDao().insertBook(BookInfo(bookId, drawableToByteArray(imgThumbnail.drawable), etTitle.text.toString(), etAuthors.text.toString(), etPublisher.text.toString(), etIsbn.text.toString()))
    }
}
