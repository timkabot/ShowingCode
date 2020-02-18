package ru.mts.mtstv.getshop.di

import org.koin.dsl.module
import ru.mts.mtstv.getshop.data.GetShopRepository
import ru.mts.mtstv.getshop.data.ScreenDimensionProvider
import ru.mts.mtstv.getshop.domain.interactors.LoadBanner
import ru.mts.mtstv.getshop.domain.interactors.LoadPlaylist
import ru.mts.mtstv.getshop.domain.interactors.PlaylistScheduler
import ru.mts.mtstv.getshop.network.GetShopApi
import ru.mts.mtstv.getshop.presentation.banner.presenter.BannerViewModel
import ru.mts.mtstv.getshop.utils.Constants

fun createGetShopModule(baseUrl: String = Constants.BASEURL) = module {
    single { ScreenDimensionProvider() }
    single { GetShopRepository(get()) }
    single { GetShopApi.create(baseUrl) }
    single { PlaylistScheduler(get(), get()) }
    single { LoadPlaylist(get()) }
    single { LoadBanner(get(), get()) }
    single { BannerViewModel(get()) }

}
