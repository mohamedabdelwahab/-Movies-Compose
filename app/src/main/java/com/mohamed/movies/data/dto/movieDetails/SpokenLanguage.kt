package com.mohamed.movies.data.dto.movieDetails


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class SpokenLanguage(
    @SerializedName("english_name")
    @Expose
    val englishName: String? = null,
    @SerializedName("iso_639_1")
    @Expose
    val iso6391: String? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null
)