package com.ddingmung.bookhi.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.ddingmung.bookhi.R
import com.ddingmung.bookhi.dataclass.BookData
import com.ddingmung.bookhi.dataclass.BookDataTest
import com.ddingmung.bookhi.functions.ApiInterface
import com.ddingmung.bookhi.functions.BookAdapter
import com.ddingmung.bookhi.functions.RetrofitObject
import com.ddingmung.bookhi.functions.RetrofitService
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.fragment_reg_book.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RegBookFragment : Fragment() {
    //private var _binding: RegBookFragment? = null
    //private val binding get() = _binding!!

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

        /* 레트로핏*/
        /*val retrofit = Retrofit.Builder().baseUrl("https://dapi.kakao.com")
            .addConverterFactory(GsonConverterFactory.create()).build();
        val service = retrofit.create(RetrofitService::class.java);

        service.getBookInfo("KakaoAK aa6e4714d6d7c7d6bdd9833afc755946", "미움")?.enqueue(object : Callback<BookDataTest> {
            override fun onResponse(call: Call<BookDataTest>, response: Response<BookDataTest>) {
                if (response.isSuccessful) {
                    // 정상적으로 통신이 성고된 경우
                    var result: BookDataTest? = response.body()
                    Log.d("YMC", "onResponse 성공: " + result?.toString());
                } else {
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("YMC", "onResponse 실패")
                }
            }

            override fun onFailure(call: Call<BookDataTest>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("YMC", "onFailure 에러: " + t.message.toString());
            }
        })*/

        RetrofitObject.getApiService().getInfo("KakaoAK aa6e4714d6d7c7d6bdd9833afc755946","AB").enqueue(object : Callback<BookDataTest>{
            override fun onResponse(call: Call<BookDataTest>, response: Response<BookDataTest>) {
                setResponseText(response.code(), response.body())
                Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<BookDataTest>, t: Throwable) {
                Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show()
            }

        })

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

    private fun setResponseText(resCode:Int, body:BookDataTest?) {
        // 상태별 text 지정
        when (resCode) {
            200 -> {
                if (body == null) {
                    Log.d("YMC", "api body가 비어있습니다.")
                    //"api body가 비어있습니다."
                } else {
                    Log.d("YMC", body.toString())
                    /*if (body.authors.toString() == "[]") {
                        Log.d("YMC", "api호출은 성공했으나 해당 저자데이터가 없습니다.")
                        //"api호출은 성공했으나 해당 저자데이터가 없습니다."
                    } else {
                        Log.d("YMC", body.toString())
                        //body.toString()
                    }*/
                }
            }
            400 -> {
                "API 키가 만료됬거나 쿼리 파라미터가 잘못 됬습니다."
            }
            401 -> {
                "인증 정보가 정확하지 않습니다."
            }
            500 -> {
                "API 서버에 문제가 발생하였습니다."
            }
            else -> {
                "기타 문제발생..."
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

