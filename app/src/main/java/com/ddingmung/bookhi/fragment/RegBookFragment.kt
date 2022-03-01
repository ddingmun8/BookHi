package com.ddingmung.bookhi.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.ddingmung.bookhi.R
import com.ddingmung.bookhi.dataclass.BookData
import com.ddingmung.bookhi.functions.BookAdapter
import kotlinx.android.synthetic.main.fragment_reg_book.*


class RegBookFragment : Fragment() {
    lateinit var bookAdapter: BookAdapter
    lateinit var rv_search_book: RecyclerView
    val datas = mutableListOf<BookData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("test","등록화면2")

        val regView: View = inflater.inflate(R.layout.fragment_reg_book, container, false)
        rv_search_book = regView.findViewById(R.id.rv_search_book)

        /* 리사이클러뷰 구분선 추가*/
        val regRecyclerView = regView!!.findViewById(R.id.rv_search_book) as RecyclerView
        regRecyclerView.addItemDecoration(DividerItemDecoration(regView!!.context, 1))

        initRecycler()
        return regView
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegBookFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private fun initRecycler() {
        bookAdapter = BookAdapter(requireContext())

        rv_search_book.adapter = bookAdapter

        Log.d("test","등록화면3")

        datas.apply {
            add(BookData(cover = R.drawable.bookhi_nick_logo_final, title = "mary", publisher = 24))
            add(BookData(cover = R.drawable.bookhi_nick_logo_final, title = "jenny", publisher = 26))
            add(BookData(cover = R.drawable.bookhi_nick_logo_final, title = "jhon", publisher = 27))
            add(BookData(cover = R.drawable.login_ka, title = "ruby", publisher = 21))
            add(BookData(cover = R.drawable.login_ap, title = "yuna", publisher = 23))
            add(BookData(cover = R.drawable.login_ap, title = "yuna", publisher = 23))
            add(BookData(cover = R.drawable.login_ap, title = "yuna", publisher = 23))
            add(BookData(cover = R.drawable.login_ap, title = "yuna", publisher = 23))
            add(BookData(cover = R.drawable.login_ap, title = "yuna", publisher = 23))

            bookAdapter.datas = datas
            bookAdapter.notifyDataSetChanged()

        }
    }
}

