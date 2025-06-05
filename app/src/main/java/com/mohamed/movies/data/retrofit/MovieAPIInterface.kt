package com.mohamed.movies.data.retrofit

import com.mohamed.movies.data.dto.latestMovies.MoviesListResponseDto
import com.mohamed.movies.data.dto.movieDetails.MovieDetailsResponseDto
import com.mohamed.movies.data.dto.moviesSuggestion.MovieSuggestionsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPIInterface {

    @GET("3/movie/now_playing")
    suspend fun getLatestMoviesList(
        @Query("page") page: Int,
        @Query("language") language: String
    ): MoviesListResponseDto

    @GET("3/search/movie")
    suspend fun getSearchSuggestions(
        @Query("query") query: String,
        @Query("language") language: String
    ): MovieSuggestionsDto

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetailsById(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): MovieDetailsResponseDto

}