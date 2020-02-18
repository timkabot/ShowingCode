package ru.mts.mtstv.getshop.data

import ru.mts.mtstv.getshop.domain.entities.Banner
import ru.mts.mtstv.getshop.domain.entities.Playlist
import io.reactivex.Single

interface IGetShopRepository {
    fun getPlaylist(
        channelId: String? = null,
        clientId: String,
        san: String? = null,
        location: String? = null,
        clientVersion: String? = null,
        displayWidth: Int? = null,
        displayHeight: Int? = null,
        eventId: String? = null,
        from: String? = null
    ): Single<Playlist>

    fun getBanner(
        eventId: String,
        displayWidth: Int,
        displayHeight: Int
    ): Single<Banner>
}