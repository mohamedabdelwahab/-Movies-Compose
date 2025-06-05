package com.mohamed.movies.data.dto.latestMovies


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Dates(
    @SerializedName("maximum")
    @Expose
    val maximum: String? = null,
    @SerializedName("minimum")
    @Expose
    val minimum: String? = null
)