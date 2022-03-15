package com.ddingmung.bookhi.functions

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ddingmung.bookhi.R
import com.ddingmung.bookhi.fragment.RegBookFragment
import com.ddingmung.bookhi.model.KakaoBook

class BookAdapter (private val context: Context) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    var datas = mutableListOf<KakaoBook>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.search_book_list,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imgCover: ImageView = itemView.findViewById(R.id.img_rv_cover)
        private val txtTitle: TextView = itemView.findViewById(R.id.tv_rv_book_title)
        private val txtAuthors: TextView = itemView.findViewById(R.id.tv_rv_book_authors)
        private val txtContents: TextView = itemView.findViewById(R.id.tv_rv_book_contents)

        fun bind(item: KakaoBook) {
            Glide.with(itemView).load(item.thumbnail).into(imgCover)
            txtTitle.text = item.title
            txtAuthors.text = item.authors.toString()
            txtContents.text = item.contents

        }

    }

}