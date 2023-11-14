https://github.com/libpag/pag-android

```
val data = listOf("guide_1.pag", "guide_2.pag", "guide_3.pag")
val banner = inflate.findViewById<Banner>(R.id.pag_banner)

banner.bindAdapter(BannerAdapter(data))
.setIndicator(inflate.findViewById<RectIndicator>(R.id.rect_indicator))
```