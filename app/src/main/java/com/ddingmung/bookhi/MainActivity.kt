package com.ddingmung.bookhi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.book_list.view.*


private val MIN_SCALE = 0.85f // 뷰가 몇퍼센트로 줄어들 것인지
private val MIN_ALPHA = 0.5f // 어두워지는 정도

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* 여백, 너비에 대한 정의 */
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageWidth)
        val screenWidth = resources.displayMetrics.widthPixels // 스마트폰의 너비 길이를 가져옴
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        // 뷰 페이저에 들어갈 아이템
        fun getIdolList(): ArrayList<Int> {
            return arrayListOf<Int>(R.drawable.bookhi_nick_logo_final, R.drawable.bookhi_logo_version2, R.drawable.bookhi_nick_logo_final)
        }

        val viewPager_book : ViewPager2 = findViewById(R.id.viewPager_book)
        viewPager_book.adapter = ViewPagerAdapter(getIdolList()) // 어댑터 생성
        viewPager_book.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로
        viewPager_book.setPageTransformer(ZoomOutPageTransformer()) // 애니메이션 적용
        viewPager_book.offscreenPageLimit = 1 // 몇 개의 페이지를 미리 로드 해둘것인지

        viewPager_book.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }

    }
}

class ZoomOutPageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            val pageHeight = height
            when {
                position < -1 -> { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    alpha = 0f
                }
                position <= 1 -> { // [-1,1]
                    // Modify the default slide transition to shrink the page as well
                    val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                    val vertMargin = pageHeight * (1 - scaleFactor) / 2
                    val horzMargin = pageWidth * (1 - scaleFactor) / 2
                    translationX = if (position < 0) {
                        horzMargin - vertMargin / 2
                    } else {
                        horzMargin + vertMargin / 2
                    }

                    // Scale the page down (between MIN_SCALE and 1)
                    scaleX = scaleFactor
                    scaleY = scaleFactor

                    // Fade the page relative to its size.
                    alpha = (MIN_ALPHA +
                            (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                }
                else -> { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    alpha = 0f
                }
            }
        }
    }
}

class ViewPagerAdapter(bookList: ArrayList<Int>) : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
    var item = bookList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.book.setImageResource(item[position])
    }

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.book_list, parent, false)){

        val book = itemView.imageView_book!!

    }
}