package ru.mts.mtstv.getshop.domain.entities

import com.google.gson.annotations.SerializedName


enum class EventType {
    @SerializedName("banner")
    BANNER,
    @SerializedName("playlist_update")
    UPDATE_PLAYLIST
}