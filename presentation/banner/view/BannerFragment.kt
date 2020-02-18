package ru.mts.mtstv.getshop.presentation.banner.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.shopify.livedataktx.observe
import org.koin.android.viewmodel.ext.android.viewModel
import ru.mts.mtstv.get_shop.R
import ru.mts.mtstv.getshop.domain.entities.Banner
import ru.mts.mtstv.getshop.domain.entities.BannerJob.HideBannerJob
import ru.mts.mtstv.getshop.domain.entities.BannerJob.ShowBannerJob
import ru.mts.mtstv.getshop.presentation.banner.presenter.BannerViewModel
import ru.mts.mtstv.getshop.utils.showErrorLog

class BannerFragment : Fragment(), IBannerView {
    private lateinit var imageBannerView: ImageView
    private val bannerVm: BannerViewModel by viewModel()

    override fun getBannerView(): View = imageBannerView

    override fun getFragmentContext(): Context? = context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.banner, container, false)
        imageBannerView = fragmentView.findViewById(R.id.bannerImageView)
        observeEvents()
        return fragmentView
    }

    private fun observeEvents() {
        bannerVm.currentEvents.observe(this) {
            when (it) {
                is HideBannerJob -> hide()
                is ShowBannerJob -> show(it.banner)
            }
        }
    }

    private fun initParams(banner: Banner) {
        imageBannerView.layoutParams.height = banner.height
        imageBannerView.layoutParams.width = banner.width
        val params = imageBannerView.layoutParams as MarginLayoutParams
        params.leftMargin = banner.left
        params.topMargin = banner.top
        Glide.with(this).load(banner.image).into(imageBannerView)
    }

    override fun show(banner: Banner) {
        initParams(banner)
        val showFragmentResult = fragmentManager?.beginTransaction()?.show(this)?.commit()
        if (showFragmentResult == null) showErrorLog("null pointer was caught during show() method of fragment")
    }

    override fun hide() {
        val hideFragmentResult = fragmentManager?.beginTransaction()?.hide(this)?.commit()
        if (hideFragmentResult == null) showErrorLog("null pointer was caught during hide() method of fragment")
    }

}