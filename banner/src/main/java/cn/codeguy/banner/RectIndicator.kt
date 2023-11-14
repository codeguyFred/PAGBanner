package cn.codeguy.banner

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View

/**
 *@author V.shengcheng.kang
 *@date 2023/10/7 17:26
 *@desc
 **/
class RectIndicator : View, Indicator {

    companion object {
        private val TAG: String = "RectIndicator"
    }

    private var indicatorSize: Int = 0
    private var selPosition: Int = 0
    private var selPaint = Paint()
    private var normalPaint = Paint()
    private var itemPaddingStart: Int = 20
    private var indicatorItemWidth: Int = 40
    private var indicatorItemHeight: Int = 20

    private var selColor: Int = 0XFFFFFF
    private var normalColor: Int = 0X4DFFFF


    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int = 0) {

        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.Indicator, defStyle, 0)
        val n = a.indexCount
        for (i in 0 until n) {
            when (val attr = a.getIndex(i)) {
                R.styleable.Indicator_selColor -> {
                    selColor = a.getColor(attr, Color.WHITE)
                }

                R.styleable.Indicator_normalColor -> {
                    normalColor = a.getColor(attr, Color.BLACK)
                }

                R.styleable.Indicator_itemWidth -> {
                    indicatorItemWidth = a.getDimensionPixelSize(
                        attr, TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 20F, resources.displayMetrics
                        ).toInt()
                    )
                }

                R.styleable.Indicator_itemHeight -> {
                    indicatorItemHeight = a.getDimensionPixelSize(
                        attr, TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 6F, resources.displayMetrics
                        ).toInt()
                    )
                }

                R.styleable.Indicator_itemPaddingStart -> {
                    itemPaddingStart = a.getDimensionPixelSize(
                        attr, TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 12F, resources.displayMetrics
                        ).toInt()
                    )
                }
            }
        }
        a.recycle()

        selPaint.color = selColor
        selPaint.isAntiAlias = true

        normalPaint.color = normalColor
        normalPaint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (indicatorSize == 0) {
            return
        }
        for (i in 0 until indicatorSize) {
            val left = i * (itemPaddingStart + indicatorItemWidth)
            Log.d(TAG, "left:$left")
            val rectF = Rect(left, 0, left + indicatorItemWidth, indicatorItemHeight)
            if (selPosition == i) {
                canvas.drawRect(rectF, selPaint)
            } else {
                canvas.drawRect(rectF, normalPaint)
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val width = if (widthMode == MeasureSpec.EXACTLY) {
            widthSize
        } else {
            //第一个item加上有边距的其他几个
            (indicatorItemWidth + (indicatorSize - 1) * (itemPaddingStart + indicatorItemWidth)).toInt()
        }

        val height = if (heightMode == MeasureSpec.EXACTLY) {
            heightSize
        } else (indicatorItemHeight.toInt())
        setMeasuredDimension(width, height)
    }

    override fun init(indicatorSize: Int) {
        this.indicatorSize = indicatorSize
        invalidate()
    }

    override fun onPageSelected(position: Int) {
        this.selPosition = position
        invalidate()
    }
}