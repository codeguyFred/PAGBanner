package cn.codeguy.pagbanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import cn.codeguy.banner.Banner
import cn.codeguy.banner.BannerAdapter
import cn.codeguy.banner.RectIndicator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun popupwin(view: View) {
        val inflate = LayoutInflater.from(this).inflate(R.layout.layout_popup, null)
        val popupWindow = PopupWindow(this)
        popupWindow.contentView = inflate
        popupWindow.width = /*WindowManager.LayoutParams.MATCH_PARENT*/800
        popupWindow.height = /*WindowManager.LayoutParams.WRAP_CONTENT*/800
        popupWindow.isOutsideTouchable = true
        //popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        val data = listOf("0.pag", "1.pag", "2.pag")
        val banner = inflate.findViewById<Banner>(R.id.pag_banner)

        banner.bindAdapter(BannerAdapter(data))
            .setIndicator(inflate.findViewById<RectIndicator>(R.id.rect_indicator))
        popupWindow.showAsDropDown(view)
    }
}