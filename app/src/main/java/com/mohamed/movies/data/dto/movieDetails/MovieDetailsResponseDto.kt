package com.mohamed.movies.data.dto.movieDetails


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class MovieDetailsResponseDto(
    @SerializedName("adult")
    @Expose
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String? = null,
    @SerializedName("belongs_to_collection")
    @Expose
    val belongsToCollection: BelongsToCollection? = null,
    @SerializedName("budget")
    @Expose
    val budget: Int? = null,
    @SerializedName("genres")
    @Expose
    val genres: List<Genre?>? = null,
    @SerializedName("homepage")
    @Expose
    val homepage: String? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("imdb_id")
    @Expose
    val imdbId: String? = null,
    @SerializedName("origin_country")
    @Expose
    val originCountry: List<String?>? = null,
    @SerializedName("original_language")
    @Expose
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    @Expose
    val originalTitle: String? = null,
    @SerializedName("overview")
    @Expose
    val overview: String? = null,
    @SerializedName("popularity")
    @Expose
    val popularity: Double? = null,
    @SerializedName("poster_path")
    @Expose
    val posterPath: String? = null,
    @SerializedName("production_companies")
    @Expose
    val productionCompanies: List<ProductionCompany?>? = null,
    @SerializedName("production_countries")
    @Expose
    val productionCountries: List<ProductionCountry?>? = null,
    @SerializedName("release_date")
    @Expose
    val releaseDate: String? = null,
    @SerializedName("revenue")
    @Expose
    val revenue: Int? = null,
    @SerializedName("runtime")
    @Expose
    val runtime: Int? = null,
    @SerializedName("spoken_languages")
    @Expose
    val spokenLanguages: List<SpokenLanguage?>? = null,
    @SerializedName("status")
    @Expose
    val status: String? = null,
    @SerializedName("tagline")
    @Expose
    val tagline: String? = null,
    @SerializedName("title")
    @Expose
    val title: String? = null,
    @SerializedName("video")
    @Expose
    val video: Boolean? = null,
    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    @Expose
    val voteCount: Int? = null
)