package ru.mts.mtstv.getshop.network

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.mts.mtstv.getshop.domain.entities.Banner
import ru.mts.mtstv.getshop.domain.entities.Playlist
import ru.mts.mtstv.getshop.utils.Constants

interface GetShopApi {

    @GET("playlist")
    fun getPlaylist(
        @Query("key") key: String,
        @Query("channel_id") channelId: String? = null,
        @Query("client_id") clientId: String,
        @Query("san") san: String? = null,
        @Query("location") location: String? = null,
        @Query("client_version") clientVersion: String? = null,
        @Query("display_width") displayWidth: Int? = null,
        @Query("display_height") displayHeight: Int? = null,
        @Query("event_id") eventId: String? = null,
        @Query("from") from: String? = null //TODO replace for not nullable
    ): Single<Playlist>

    // Banner info
    @GET("banner")
    fun getBannerInfo(
        @Query("key") key: String? = null,
        @Query("event_id") eventId: String,
        @Query("display_width") displayWidth: Int,
        @Query("display_height") displayHeight: Int
    ): Single<Banner>

    /**
     * Companion object to create the GetShopApi
     */
    companion object Factory {
        fun create(baseUrl: String = Constants.BASEURL): GetShopApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()
            return retrofit.create(GetShopApi::class.java)
        }
    }
}