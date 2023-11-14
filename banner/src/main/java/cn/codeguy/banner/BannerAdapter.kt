package cn.codeguy.banner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.libpag.PAGImageView

class BannerAdapter(private val data: List<String>) : RecyclerView.Adapter<BannerViewHolder>() {
    private var pause: Boolean = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onViewAttachedToWindow(holder: BannerViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.pagView.play()
    }

    override fun onViewDetachedFromWindow(holder: BannerViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.pagView.pause()
    }

    fun pause() {
        pause = true
    }

    fun resume() {
        pause = false
    }

}

class BannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var pagView: PAGImageView
    fun bind(item: String) {
        pagView.path = "assets://${item}"
        pagView.setRepeatCount(-1)
        pagView.setCacheAllFramesInMemory(true)
    }

    init {
        pagView = view.findViewById<View>(R.id.pagView) as PAGImageView
    }
}
