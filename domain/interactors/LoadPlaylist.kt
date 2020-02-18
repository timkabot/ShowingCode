package ru.mts.mtstv.getshop.domain.interactors

import ru.mts.mtstv.getshop.data.GetShopRepository
import ru.mts.mtstv.getshop.domain.entities.Playlist
import ru.mts.mtstv.getshop.utils.fromEpochToISO8601
import io.reactivex.Single

class LoadPlaylist(private val getShopRepository: GetShopRepository) {

    fun loadPlayList(time: Long): Single<Playlist> {
        val clientId = "" // was deleted in case for privacy
        return getShopRepository.getPlaylist(clientId = clientId, from = fromEpochToISO8601(time))
    }
}