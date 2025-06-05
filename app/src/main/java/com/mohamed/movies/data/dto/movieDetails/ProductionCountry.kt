package com.mohamed.movies.data.dto.movieDetails


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    @Expose
    val iso31661: String? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null
)