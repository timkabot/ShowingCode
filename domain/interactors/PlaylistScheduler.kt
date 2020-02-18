package ru.mts.mtstv.getshop.domain.interactors

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import ru.mts.mtstv.getshop.domain.entities.BannerInfo
import ru.mts.mtstv.getshop.domain.entities.BannerJob
import ru.mts.mtstv.getshop.domain.entities.BannerJob.HideBannerJob
import ru.mts.mtstv.getshop.domain.entities.BannerJob.ShowBannerJob
import ru.mts.mtstv.getshop.domain.entities.Event
import ru.mts.mtstv.getshop.domain.entities.EventType
import ru.mts.mtstv.getshop.utils.calculateDelay
import timber.log.Timber
import java.util.concurrent.TimeUnit

class PlaylistScheduler(private val playlistLoader: LoadPlaylist, private val bannerInteractor: LoadBanner) {
    private var result: PublishSubject<BannerJob> = PublishSubject.create()
    private var bannerTimeIntervals: MutableList<BannerInfo> = mutableListOf()
    private var disposables: CompositeDisposable = CompositeDisposable()
    private var time: Long = System.currentTimeMillis()
    fun onPause() {
        clearJobs()
    }

    fun getResult() = result


    fun onContinue(time: Long) {
        updateTime(time)
        schedule()
    }

    fun onMove(time: Long) {
        updateTime(time)
        clearJobs()

    }

    private fun updateTime(time: Long) {
        this.time = time
    }

    fun onStart(time: Long = System.currentTimeMillis()) {
        updateTime(time)
        getPlaylist(time = time)
    }

    private fun updateTimeIntervals(events: List<Event>) {
        bannerTimeIntervals = events.map {
            BannerInfo(from = it.banner.datetime, to = it.banner.to, banner = it.banner)
        }.toMutableList()
        schedule()
    }

    private fun getPlaylist(delay: Long = 0, time: Long = System.currentTimeMillis()) {
        playlistLoader.loadPlayList(time = time).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .delay(delay, TimeUnit.MILLISECONDS)
            .map { it.events }
            .subscribe(
                { result ->
                    updateTimeIntervals(result)
                    Timber.d(result.toString())
                    updatePlaylist(events = result)
                },
                { error -> Timber.e(error) }
            ).addTo(disposables)
    }

    private fun updatePlaylist(events: List<Event>) {
        val updateEvent = events.find { it.type == EventType.UPDATE_PLAYLIST }
        updateEvent?.let {
            val difference = calculateDelay(toInSeconds = it.datetime)
            getPlaylist(delay = difference)
        }
    }

    private fun createCompletableWithDelay(delay: Long) = Completable.complete().delay(delay, TimeUnit.MILLISECONDS)
    private fun createJob(job: BannerJob, delay: Long) {
        createCompletableWithDelay(delay).subscribe {
            result.onNext(job)
        }.addTo(disposables)
    }

    private fun createShowBannerJobs() {
        bannerTimeIntervals.forEach { bannerInterval ->
            val tempDelay = calculateDelay(from = time, toInSeconds = bannerInterval.from)
            val tempJob = ShowBannerJob(banner = bannerInterval.banner)
            createJob(delay = tempDelay, job = tempJob)
        }
    }

    private fun createHideBannerJobs() {
        bannerTimeIntervals.forEach { bannerInterval ->
            val tempDelay = calculateDelay(from = time, toInSeconds = bannerInterval.to)
            val tempJob = HideBannerJob()
            createJob(delay = tempDelay, job = tempJob)
        }
    }

    private fun schedule() {
        clearJobs()
        createShowBannerJobs()
        createHideBannerJobs()
    }

    fun clearJobs() {
        disposables.clear()
    }


}