package com.mohamed.movies.domain.useCase.searchSuggestion

import com.mohamed.movies.domain.model.Resource
import com.mohamed.movies.domain.model.moviesResponse.MovieListItem
import com.mohamed.movies.domain.repository.IMoviesRepository
import com.mohamed.movies.utils.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchSuggestionMoviesUseCase @Inject constructor(private val iMoviesRepository: IMoviesRepository) {
    suspend operator fun invoke(
        query: String, language: String = Constants.Defaults.DEFAULT_LANGUAGE
    ): Flow<Resource<ArrayList<MovieListItem>>> {
        return iMoviesRepository.getSearchSuggestions(query = query, language = language)
    }
}