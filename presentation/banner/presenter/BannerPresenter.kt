package ru.mts.mtstv.getshop.presentation.banner.presenter

import ru.mts.mtstv.getshop.domain.entities.Banner


interface BannerPresenter {

    fun showBanner(banner: Banner)

    fun hideBanner()

    fun openMicrosite()

}