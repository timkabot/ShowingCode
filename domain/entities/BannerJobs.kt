package ru.mts.mtstv.getshop.domain.entities

import ru.mts.mtstv.getshop.presentation.banner.view.IBannerView

sealed class BannerJob {

    class HideBannerJob : BannerJob() {
        fun hideBanner(bannerView: IBannerView) {
            bannerView.hide()
        }
    }

    class ShowBannerJob(val banner: Banner) : BannerJob() {
        fun hideBanner(bannerView: IBannerView) {
            bannerView.show(banner)
        }
    }
}