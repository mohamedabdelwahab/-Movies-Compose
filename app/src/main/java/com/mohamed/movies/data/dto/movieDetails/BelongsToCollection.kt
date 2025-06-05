package com.mohamed.movies.data.dto.movieDetails


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class BelongsToCollection(
    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("poster_path")
    @Expose
    val posterPath: String? = null
)