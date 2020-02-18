package ru.mts.mtstv.getshop.domain.entities

import java.time.Duration

data class BannerInfo(
    val from: Long,
    val to: Long,
    var banner: Banner ,
    val duration: Duration? = null,
    var closed: Boolean? = false
)