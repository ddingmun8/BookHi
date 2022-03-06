package com.ddingmung.bookhi.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.ddingmung.bookhi.R
import com.ddingmung.bookhi.functions.BookDB
import kotlinx.android.synthetic.main.book_list.view.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlin.math.abs

lateinit var navController: NavController

class MainFragment : Fragment(){
    lateinit var db: BookDB

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //DB 생성
        db = Room.databaseBuilder(requireActivity(), BookDB::class.java, "BookDB").allowMainThreadQueries().build()

        var arrTitle = db.getDao().getTitle().toString().split(",")
        Log.d("test66" , arrTitle.toString())
        Log.d("test66" , arrTitle.size.toString())
        /*var lTitle= arrayListOf(
            "ttt",
            "sss",
            for(i:Int in arrTitle.size){
                arrTitle.get(i).toString()
            }
        )*/

        // Adapter를 생성하면서 넘길 색상이 담긴 ArrayList<Int> 생성
        var bgColors = arrayListOf<Int>(
            R.color.gray,
            R.color.nick,
            R.color.purple_200,
            R.color.light_nick,
            R.color.purple_700
        )

        // RecyclerView.Adapter<PagerViewHolder>()
        viewPager.adapter = PagerRecyclerAdapter(bgColors, arrTitle)
        // 관리하는 페이지 수. default = 1
        viewPager.offscreenPageLimit = 4
        // item_view 간의 양 옆 여백을 상쇄할 값
        val offsetBetweenPages = resources.getDimensionPixelOffset(R.dimen.offsetBetweenPages).toFloat()
        viewPager.setPageTransformer { page, position ->
            val myOffset = position * -(2 * offsetBetweenPages)
            if (position < -1) {
                page.translationX = -myOffset
            } else if (position <= 1) {
                // Paging 시 Y축 Animation 배경색을 약간 연하게 처리
                val scaleFactor = 0.8f.coerceAtLeast(1 - abs(position))
                page.translationX = myOffset
                page.scaleY = scaleFactor
                page.alpha = scaleFactor
            } else {
                page.alpha = 0f
                page.translationX = myOffset
            }
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position+1}")
            }
        })
    }
}

class PagerRecyclerAdapter(private val bgColors: ArrayList<Int>, private val lTitle: List<String>) : RecyclerView.Adapter<PagerRecyclerAdapter.PagerViewHolder>() {
    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val pageName: TextView = itemView.findViewById(R.id.pageName)
        private val title: TextView = itemView.findViewById(R.id.title)

        fun bind(@ColorRes bgColor: Int, position: Int) {
            pageName.text = "Page ${position+1}"
            pageName.setBackgroundColor(ContextCompat.getColor(pageName.context, bgColor))
        }

        fun bindTitle(s: String, position: Int) {
            title.text = lTitle[position]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_view,
            parent,
            false
        )
        return PagerViewHolder(view)
    }
    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        //holder.bind(bgColors[position], position)
        holder.bindTitle(lTitle[position], position)
    }

    override fun getItemCount(): Int = lTitle.size

}
