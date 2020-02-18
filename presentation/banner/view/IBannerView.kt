package ru.mts.mtstv.getshop.presentation.banner.view

import android.content.Context
import android.view.View
import ru.mts.mtstv.getshop.domain.entities.Banner

interface IBannerView {
    fun getBannerView(): View

    fun getFragmentContext(): Context?

    fun show(banner: Banner)

    fun hide()
}