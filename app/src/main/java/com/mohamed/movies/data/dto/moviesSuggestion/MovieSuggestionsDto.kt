package com.mohamed.movies.data.dto.moviesSuggestion


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieSuggestionsDto(
    @SerializedName("page")
    @Expose
    val page: Int? = null,
    @SerializedName("results")
    @Expose
    val searchResultMovies: ArrayList<SearchResultMovie?>? = null,
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int? = null,
    @SerializedName("total_results")
    @Expose
    val totalResults: Int? = null
)