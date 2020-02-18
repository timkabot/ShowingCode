package ru.mts.mtstv.getshop.domain.entities

data class Playlist(
    val events: List<Event>,
    val deviceTimestamp: Double
)