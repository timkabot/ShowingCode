package ru.mts.mtstv.getshop.data

import io.reactivex.Single
import ru.mts.mtstv.getshop.domain.entities.Banner
import ru.mts.mtstv.getshop.domain.entities.Playlist
import ru.mts.mtstv.getshop.network.GetShopApi


class GetShopRepository(val getShopApi: GetShopApi) : IGetShopRepository {
    val key = "" // was deleted in case for privacy
    override fun getBanner(eventId: String, displayWidth: Int, displayHeight: Int): Single<Banner> {
        return getShopApi.getBannerInfo(key, eventId, displayWidth, displayHeight)
    }

    override fun getPlaylist(
        channelId: String?,
        clientId: String,
        san: String?,
        location: String?,
        clientVersion: String?,
        displayWidth: Int?,
        displayHeight: Int?,
        eventId: String?,
        from: String?
    ): Single<Playlist> {
        return getShopApi.getPlaylist(key = key, clientId = clientId)
    }


}