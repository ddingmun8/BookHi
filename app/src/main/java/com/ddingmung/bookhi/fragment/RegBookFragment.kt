package com.ddingmung.bookhi.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.ddingmung.bookhi.R
import com.ddingmung.bookhi.model.KakaoBook
import com.ddingmung.bookhi.functions.BookAdapter
import com.ddingmung.bookhi.viewmodel.MainViewModel
import com.ddingmung.bookhi.viewmodel.MainViewModelFactory
import com.ddingmung.bookhi.repository.Repository
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_reg_book.view.*


class RegBookFragment : Fragment() {
    private lateinit var viewModel : MainViewModel

    lateinit var bookAdapter: BookAdapter
    lateinit var rv_search_book: RecyclerView
    val datas = mutableListOf<KakaoBook>()

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

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        regView.btnBookSearch.setOnClickListener {
            viewModel.searchBook()
        }

        bookAdapter = BookAdapter(requireContext())
        rv_search_book.adapter = bookAdapter

        viewModel.myCustomPosts.observe(viewLifecycleOwner, Observer { result ->
            if(result.isSuccessful){
                Log.d("test5", "$result")
                var j : Int = 0
                var inputCntents = ""
                datas.apply {
                    for (i in result.body()!!.documents!!) {
                        Log.d("test5", "$i")
                        Log.d("test5", "길이 : " + result.body()!!.documents?.get(j)!!.contents.length)

                        if(result.body()!!.documents?.get(j)!!.contents.length > 95){
                            inputCntents = result.body()!!.documents?.get(j)!!.contents.substring(0,95) + "···"
                        }else{
                            inputCntents = result.body()!!.documents?.get(j)!!.contents
                        }
                        add(
                            KakaoBook(
                                thumbnail = result.body()!!.documents?.get(j)!!.thumbnail,
                                authors = result.body()!!.documents?.get(j)!!.authors,
                                title = result.body()!!.documents?.get(j)!!.title,
                                contents = inputCntents
                            )
                        )
                        j++
                        Log.d("test568", "" + j)
                    }

                    bookAdapter.datas = datas
                    bookAdapter.notifyDataSetChanged()
                }
                //regView.textView.text = result.body()!!.documents?.get(0)!!.url
            }
            else{
                Log.d("test5", "fail")
            }
        })

        //initRecycler()

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
            //println("데어터를 가져 오는 중...")

            /*val key = "Your-key"
            val url = "http://openapi.seoul.go.kr:8088/"+key+"/json/SeoulLibraryBookRentNumInfo/1/15/"*/

            /*add(BookData(cover = R.drawable.bookhi_nick_logo_final, title = "mary", publisher = "mary"))
            add(BookData(cover = R.drawable.bookhi_nick_logo_final, title = "jenny", publisher = "mary"))
            add(BookData(cover = R.drawable.bookhi_nick_logo_final, title = "jhon", publisher = "mary"))
            add(BookData(cover = R.drawable.login_ka, title = "ruby", publisher = "mary"))
            add(BookData(cover = R.drawable.login_ap, title = "yuna", publisher = "mary"))
            add(BookData(cover = R.drawable.login_ap, title = "yuna", publisher = "mary"))
            add(BookData(cover = R.drawable.login_ap, title = "yuna", publisher = "mary"))
            add(BookData(cover = R.drawable.login_ap, title = "yuna", publisher = "mary"))
            add(BookData(cover = R.drawable.login_ap, title = "yuna", publisher = "mary"))*/

            bookAdapter.datas = datas
            bookAdapter.notifyDataSetChanged()

        }
    }
}

