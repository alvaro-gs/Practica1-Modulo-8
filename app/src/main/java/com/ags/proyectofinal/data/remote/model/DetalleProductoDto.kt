package com.ags.proyectofinal.data.remote.model

import com.google.gson.annotations.SerializedName

data class DetalleProductoDto(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("image")
    var imageURL: String? = null,
    @SerializedName("long_desc")
    var description: String? = null,
    @SerializedName("type_id")
    var categoryId: Long ? = 0,
    @SerializedName("presentations")
    var presentations : List<Presentations>? = null,
    @SerializedName("video_url")
    var videoURL: String ?= null

)

data class Presentations(
    @SerializedName("desc")
    var desc: String? = null,
    @SerializedName("price")
    var price: Double? = 0.0
)
