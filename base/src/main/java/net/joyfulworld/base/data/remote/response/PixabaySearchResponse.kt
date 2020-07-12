package net.joyfulworld.base.data.remote.response

import com.google.gson.annotations.SerializedName
import net.joyfulworld.base.data.remote.domain.HitImage

data class PixabaySearchResponse(
    @SerializedName("total") val total : Int,
    @SerializedName("totalHits") val totalHits : Int,
    @SerializedName("hits") val hitImages : ArrayList<HitImage>
)