package com.mohamed.movies.data.dto.movieDetails


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ProductionCompany(
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("logo_path")
    @Expose
    val logoPath: String? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("origin_country")
    @Expose
    val originCountry: String? = null
)