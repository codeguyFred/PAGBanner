package cn.codeguy.banner

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback

/**
 *@author V.shengcheng.kang
 *@date 2023/10/7 17:25
 *@desc
 **/
class Banner @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    private var viewPager: ViewPager2
    private var onPageChangeCallback: OnPageChangeCallback? = null

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_banner, this, true)
        viewPager = view.findViewById(R.id.viewPager)
    }

    fun <T : ViewHolder> bindAdapter(adapter: RecyclerView.Adapter<T>): Banner {
        viewPager.adapter = adapter
        return this
    }

    fun setIndicator(indicator: Indicator): Banner {
        if (viewPager.adapter == null) {
            throw IllegalArgumentException("viewPager.adapter==null,please set viewpager first")
        }
        indicator.init(viewPager.adapter!!.itemCount)
        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indicator.onPageSelected(position)
                onPageChangeCallback?.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                if (state== ViewPager2.SCROLL_STATE_DRAGGING){
                    if ( viewPager.adapter is BannerAdapter )
                        (viewPager.adapter as BannerAdapter).pause()
                }else if (state== ViewPager2.SCROLL_STATE_SETTLING){
                    if ( viewPager.adapter is BannerAdapter )
                        (viewPager.adapter as BannerAdapter).resume()
                }
            }
        })
        return this
    }

    fun setOnPageChangeCallback(callback: OnPageChangeCallback): Banner {
        onPageChangeCallback = callback
        return this
    }
}