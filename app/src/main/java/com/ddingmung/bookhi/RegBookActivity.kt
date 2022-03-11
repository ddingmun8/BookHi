package com.ddingmung.bookhi

import android.app.AlertDialog
import android.app.DatePickerDialog
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
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.room.Room
import com.ddingmung.bookhi.functions.BookDB
import com.ddingmung.bookhi.model.BookInfo
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_reg_book.*
import kotlinx.android.synthetic.main.reg_bottom_sheet_layout.*
import java.io.ByteArrayOutputStream
import java.util.*

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
                val btnEndDt = dialog.findViewById<TextView>(R.id.btnEndDt)
                val btnSaveBook = dialog.findViewById<TextView>(R.id.btnSaveBook)

                btnStartDt?.setOnClickListener {
                    //달력 보이게
                    //Toast.makeText(this, "내용을 클릭하였습니다", Toast.LENGTH_LONG).show()

                    //  날짜 dialog
                    val dialog = AlertDialog.Builder(this).create()
                    val edialog : LayoutInflater = LayoutInflater.from(this)
                    val mView : View = edialog.inflate(R.layout.dialog_datepicker,null)

                    val year : NumberPicker = mView.findViewById(R.id.yearpicker_datepicker)
                    val month : NumberPicker = mView.findViewById(R.id.monthpicker_datepicker)
                    val day : NumberPicker = mView.findViewById(R.id.daypicker_datepicker)
                    val cancel : Button = mView.findViewById(R.id.cancel_button_datepicker)
                    val save : Button = mView.findViewById(R.id.save_button_datepicker)


                    //  순환 안되게 막기
                    year.wrapSelectorWheel = false
                    month.wrapSelectorWheel = false
                    day.wrapSelectorWheel = false

                    //  editText 설정 해제
                    year.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
                    month.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
                    day.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

                    //  최소값 설정
                    year.minValue = 2022
                    month.minValue = 1
                    day.minValue = 1

                    //  최대값 설정
                    year.maxValue = 2025
                    month.maxValue = 12
                    day.maxValue = 31


                    //  취소 버튼 클릭 시
                    cancel.setOnClickListener {
                        dialog.dismiss()
                        dialog.cancel()
                    }

                    //  완료 버튼 클릭 시
                    save.setOnClickListener {
                        val tvStartDt = dialog.findViewById<TextView>(R.id.tvStartDt)
                        //val rView : View = edialog.inflate(R.layout.reg_bottom_sheet_layout,null)
                        //val tvStartDt : TextView = rView.findViewById(R.id.tvStartDt)

                        val text = (year.value).toString() + "." + (month.value).toString() + "." + (day.value).toString()
                        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
                        //tvStartDt.text = text

                        dialog.dismiss()
                        dialog.cancel()
                    }
                    dialog.setView(mView)
                    dialog.create()
                    dialog.show()

                    //dialog.dismiss()
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
