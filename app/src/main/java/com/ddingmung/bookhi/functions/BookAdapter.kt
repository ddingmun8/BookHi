package com.ddingmung.bookhi.functions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ddingmung.bookhi.R
import com.ddingmung.bookhi.dataclass.BookData

class BookAdapter (private val context: Context) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    var datas = mutableListOf<BookData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.search_book_list,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtTitle: TextView = itemView.findViewById(R.id.tv_rv_book_title)
        private val txtPublisher: TextView = itemView.findViewById(R.id.tv_rv_book_publisher)
        private val imgCover: ImageView = itemView.findViewById(R.id.img_rv_cover)

        fun bind(item: BookData) {
            txtTitle.text = item.title
            txtPublisher.text = item.publisher.toString()
            Glide.with(itemView).load(item.cover).into(imgCover)

        }
    }


}