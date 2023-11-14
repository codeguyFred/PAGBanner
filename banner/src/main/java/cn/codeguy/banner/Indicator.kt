package cn.codeguy.banner

/**
 *@author V.shengcheng.kang
 *@date 2023/10/7 17:26
 *@desc
 **/
interface Indicator {
    fun init(indicatorSize: Int)
    fun onPageSelected(position: Int)
}