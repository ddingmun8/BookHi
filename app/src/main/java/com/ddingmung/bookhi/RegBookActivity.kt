package com.ddingmung.bookhi

import android.R.string
import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
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
    var inStartDt = ""
    var inEndDt = ""
    var inRating = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_book)

        //DB 생성
        db = Room.databaseBuilder(this, BookDB::class.java, "BookDB").allowMainThreadQueries().build()

        val btnSave : Button = findViewById<Button>(R.id.btnSave)
        val regBottomDialog: BottomSheetDialog = BottomSheetDialog(this)
        btnSave.setOnClickListener {
            if (etTitle.text.isNotEmpty()){
                //저장 누르면 바텀 다이얼로그
                regBottomDialog.setContentView(R.layout.reg_bottom_sheet_layout)
                regBottomDialog.show()

                val btnStartDt = regBottomDialog.findViewById<Button>(R.id.btnStartDt)
                val btnEndDt = regBottomDialog.findViewById<Button>(R.id.btnEndDt)
                val btnSaveBook = regBottomDialog.findViewById<Button>(R.id.btnSaveBook)
                val ratingBook = regBottomDialog.findViewById<RatingBar>(R.id.ratingBook)

                btnStartDt?.setOnClickListener {
                    //  날짜 dialog
                    val dateDialog = AlertDialog.Builder(this).create()
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
                        dateDialog.dismiss()
                        dateDialog.cancel()
                    }

                    //  저장 버튼 클릭 시
                    save.setOnClickListener {
                        val text = (year.value).toString() + "." + (month.value).toString() + "." + (day.value).toString()
                        //Toast.makeText(this, text, Toast.LENGTH_LONG).show()
                        
                        val tvStartDt = regBottomDialog.findViewById<TextView>(R.id.tvStartDt)
                        if (tvStartDt != null) {
                            tvStartDt.text = text
                            inStartDt = text
                        }

                        dateDialog.dismiss()
                        dateDialog.cancel()
                    }
                    dateDialog.setView(mView)
                    dateDialog.create()
                    dateDialog.show()

                    //dialog.dismiss()
                }

                btnEndDt?.setOnClickListener {
                    //  날짜 dialog
                    val dateDialog = AlertDialog.Builder(this).create()
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
                        dateDialog.dismiss()
                        dateDialog.cancel()
                    }

                    //  저장 버튼 클릭 시
                    save.setOnClickListener {
                        val text = (year.value).toString() + "." + (month.value).toString() + "." + (day.value).toString()
                        //Toast.makeText(this, text, Toast.LENGTH_LONG).show()

                        val tvEndDt = regBottomDialog.findViewById<TextView>(R.id.tvEndDt)
                        if (tvEndDt != null) {
                            tvEndDt.text = text
                            inEndDt = text
                        }
                        dateDialog.dismiss()
                        dateDialog.cancel()
                    }
                    dateDialog.setView(mView)
                    dateDialog.create()
                    dateDialog.show()

                    //dialog.dismiss()
                }

                if (ratingBook != null) {
                    ratingBook.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                        //Toast.makeText(this, "${rating}점", Toast.LENGTH_LONG).show()
                        inRating = "${rating}"
                    }
                }

                if (btnSaveBook != null) {
                    btnSaveBook.setOnClickListener{
                        if(inStartDt == ""){
                            Toast.makeText(this, "독서 시작날짜를 선택해주세요.", Toast.LENGTH_SHORT).show()
                        }else if(inEndDt == ""){
                            Toast.makeText(this, "독서 끝날짜를 선택해주세요.", Toast.LENGTH_SHORT).show()
                        }else if(inRating == ""){
                            Toast.makeText(this, "별점을 선택해주세요.", Toast.LENGTH_SHORT).show()
                        }else{
                            //Toast.makeText(this, inStartDt + "//" + inEndDt + "//" + inRating, Toast.LENGTH_LONG).show()
                            regBook()
                            finish()
                            //regBottomDialog.dismiss()
                            //regBottomDialog.cancel()
                        }
                    }
                }

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
        db.getDao().insertBook(BookInfo(bookId, drawableToByteArray(imgThumbnail.drawable), etTitle.text.toString(), etAuthors.text.toString(), etPublisher.text.toString(), etIsbn.text.toString(), inStartDt, inEndDt, inRating))
        Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show()
        Log.d("testDB", db.getDao().getAll().toString())
        Log.d("testDB", db.getDao().getTitle().toString())
        Log.d("testDB", db.getDao().getreadDt().toString())
    }
}
