package net.joyfulworld.base.data.remote.api

import io.reactivex.Single
import net.joyfulworld.base.data.remote.response.PixabaySearchResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface PixabaySearchAPI {
    @GET("/api")
    fun search(@QueryMap params: MutableMap<String, String>): Single<PixabaySearchResponse>

}