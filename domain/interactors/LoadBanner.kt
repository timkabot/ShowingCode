package ru.mts.mtstv.getshop.domain.interactors

import ru.mts.mtstv.getshop.data.GetShopRepository
import ru.mts.mtstv.getshop.data.ScreenDimensionProvider
import ru.mts.mtstv.getshop.domain.entities.Banner
import io.reactivex.Single


class LoadBanner(
    private val getShopRepository: GetShopRepository,
    private val screenDimensionProvider: ScreenDimensionProvider
) {
    //private val defaultEventId = "" was deleted in case for privacy

    fun loadBanner(
        eventId: String = defaultEventId
    ): Single<Banner> {
        return getShopRepository.getBanner(eventId, screenDimensionProvider.width, screenDimensionProvider.height)
    }
}