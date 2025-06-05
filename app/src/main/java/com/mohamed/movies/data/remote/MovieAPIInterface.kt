package com.mohamed.movies.data.remote

import com.mohamed.movies.data.dto.MovieItem
import com.mohamed.movies.data.dto.MoviesListResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPIInterface {

    @GET("3/movie/now_playing")
    suspend fun getLatestMoviesList(
        @Query("page") page: Int,
        @Query("language") pageNumber: String
    ): MoviesListResponseDto

    @GET("3/movie/{movieId}")
    suspend fun getMovieDetailsById(
        @Path("movie_id") movieId: Int
    ): Response<MovieItem>

}