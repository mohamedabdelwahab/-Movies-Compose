package com.mohamed.movies.data.repositories

import com.mohamed.movies.utils.connection_utils.IConnectionUtils
import com.mohamed.movies.data.BaseRepository
import com.mohamed.movies.data.mapper.toMoviesDomainModel
import com.mohamed.movies.data.remotedatasource.IMoviesRemoteDataSource
import com.mohamed.movies.domain.model.MoviesSearchModel
import com.mohamed.movies.domain.model.Resource
import com.mohamed.movies.domain.model.moviesResponse.MoviesListResponse
import com.mohamed.movies.domain.repository.IMoviesRepository
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
        moviesSearchModel: MoviesSearchModel
    ): Flow<Resource<MoviesListResponse>> {
        return safeApiCall {
            val movieAnnouncementResponse =
                moviesRemoteDataSource.getLatestMoviesList(moviesSearchModel)

            movieAnnouncementResponse.toMoviesDomainModel()
        }
    }
}