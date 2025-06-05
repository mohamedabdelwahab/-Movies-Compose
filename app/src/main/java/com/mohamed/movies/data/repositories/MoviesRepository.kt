package com.mohamed.movies.data.repositories

import com.mohamed.movies.data.BaseRepository
import com.mohamed.movies.data.mapper.toMovieListItem
import com.mohamed.movies.data.mapper.toMoviesDomainModel
import com.mohamed.movies.data.remotedatasource.IMoviesRemoteDataSource
import com.mohamed.movies.domain.model.Resource
import com.mohamed.movies.domain.model.moviesResponse.MovieListItem
import com.mohamed.movies.domain.model.moviesResponse.MoviesListResponse
import com.mohamed.movies.domain.repository.IMoviesRepository
import com.mohamed.movies.utils.connection_utils.IConnectionUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    mIConnectionUtils: IConnectionUtils,
    private val moviesRemoteDataSource: IMoviesRemoteDataSource,
) : IMoviesRepository,
    BaseRepository(
        mIConnectionUtils,
    ) {

    override suspend fun getLatestMoviesList(
        language: String,
        page: Int
    ): Flow<Resource<MoviesListResponse>> {
        return safeApiCall {
            moviesRemoteDataSource.getLatestMoviesList(language = language, page = page)
                .toMoviesDomainModel()
        }
    }

    override suspend fun getSearchSuggestions(
        query: String, language: String
    ): Flow<Resource<ArrayList<MovieListItem>>> {
        return safeApiCall {
            moviesRemoteDataSource.getSearchSuggestions(
                language = language,
                query = query
            ).searchResultMovies
                ?.mapNotNull { it?.toMovieListItem() }
                ?.let { ArrayList(it) }
                ?: arrayListOf()
        }
    }

}