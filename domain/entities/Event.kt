package ru.mts.mtstv.getshop.domain.entities

data class Event(
    val id: String,
    val datetime: Double,
    val banner: Banner,
    val type: EventType
)