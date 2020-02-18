package ru.mts.mtstv.getshop.presentation.banner.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shopify.livedataktx.nonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import ru.mts.mtstv.getshop.domain.entities.Banner
import ru.mts.mtstv.getshop.domain.entities.BannerJob
import ru.mts.mtstv.getshop.domain.entities.BannerJob.HideBannerJob
import ru.mts.mtstv.getshop.domain.entities.BannerJob.ShowBannerJob
import ru.mts.mtstv.getshop.domain.interactors.PlaylistScheduler

class BannerViewModel(private val scheduler: PlaylistScheduler) : ViewModel() {
    init {
        handleScheduler()
    }

    private val liveEvents = MutableLiveData<BannerJob>()

    val currentEvents
        get() = liveEvents.immutable().nonNull()

    private val disposables = CompositeDisposable()

    private fun handleScheduler() {
        scheduler.getResult().subscribe {
            when (it) {
                is HideBannerJob ->
                    postHideBannerJob()
                is ShowBannerJob ->
                    postShowBannerJob(it.banner)
            }
        }.addTo(disposables)
    }

    private fun postShowBannerJob(banner: Banner) {
        liveEvents.postValue(ShowBannerJob(banner = banner))
    }

    private fun postHideBannerJob() {
        liveEvents.postValue(HideBannerJob())
    }

    override fun onCleared() {
        disposables.dispose()
    }

    fun openMicrosite() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun <T> MutableLiveData<T>.immutable(): LiveData<T> = this

}