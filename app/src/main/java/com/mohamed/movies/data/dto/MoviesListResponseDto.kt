package com.mohamed.movies.data.dto


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class MoviesListResponseDto(
    @SerializedName("dates")
    @Expose
    val dates: Dates? = null,
    @SerializedName("page")
    @Expose
    val page: Int? = null,
    @SerializedName("results")
    @Expose
    val movieItems: List<MovieItem?>? = null,
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int? = null,
    @SerializedName("total_results")
    @Expose
    val totalResults: Int? = null
)