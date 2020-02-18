package ru.mts.mtstv.getshop.domain.entities

data class Banner(
    val datetime: Long,
    val duration: Long,
    val top: Int,
    val left: Int,
    val caption: String,
    val width: Int,
    val height: Int,
    val image: String,
    val pixels: Pixels,
    val microsite: String,
    val to: Long = datetime + duration
)