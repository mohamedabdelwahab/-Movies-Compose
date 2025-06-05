package com.mohamed.movies.data.mapper

import com.mohamed.movies.data.dto.movieDetails.BelongsToCollection
import com.mohamed.movies.data.dto.movieDetails.Genre
import com.mohamed.movies.data.dto.movieDetails.MovieDetailsResponseDto
import com.mohamed.movies.data.dto.movieDetails.ProductionCompany
import com.mohamed.movies.data.dto.movieDetails.ProductionCountry
import com.mohamed.movies.data.dto.movieDetails.SpokenLanguage
import com.mohamed.movies.domain.model.movieDetails.BelongsToCollectionModel
import com.mohamed.movies.domain.model.movieDetails.GenreModel
import com.mohamed.movies.domain.model.movieDetails.MovieDetailsReposeModel
import com.mohamed.movies.domain.model.movieDetails.ProductionCompanyModel
import com.mohamed.movies.domain.model.movieDetails.ProductionCountryModel
import com.mohamed.movies.domain.model.movieDetails.SpokenLanguageModel

fun MovieDetailsResponseDto.toMovieDetailsReposeModel(): MovieDetailsReposeModel {
    return MovieDetailsReposeModel(
        adult = adult,
        backdrop_path = backdropPath,
        belongs_to_collection = belongsToCollection?.toModel(),
        budget = budget,
        genreModels = genres?.map { it?.toModel() },
        homepage = homepage,
        id = id,
        imdb_id = imdbId,
        origin_country = originCountry,
        original_language = originalLanguage,
        original_title = originalTitle,
        overview = overview,
        popularity = popularity,
        poster_path = posterPath,
        production_companies = productionCompanies?.map { it?.toModel() },
        production_countries = productionCountries?.map { it?.toModel() },
        release_date = releaseDate,
        revenue = revenue,
        runtime = runtime,
        spoken_languages = spokenLanguages?.map { it?.toModel() },
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        vote_average = voteAverage,
        vote_count = voteCount
    )
}

private fun BelongsToCollection?.toModel(): BelongsToCollectionModel? {
    return this?.let {
        BelongsToCollectionModel(
            backdrop_path = backdropPath,
            id = id,
            name = name,
            poster_path = posterPath
        )
    }
}

private fun Genre?.toModel(): GenreModel? {
    return this?.let {
        GenreModel(
            id = id,
            name = name
        )
    }
}

private fun ProductionCompany?.toModel(): ProductionCompanyModel? {
    return this?.let {
        ProductionCompanyModel(
            id = id,
            logo_path = logoPath,
            name = name,
            origin_country = originCountry
        )
    }
}

private fun ProductionCountry?.toModel(): ProductionCountryModel? {
    return this?.let {
        ProductionCountryModel(
            iso_3166_1 = iso31661,
            name = name
        )
    }
}

private fun SpokenLanguage?.toModel(): SpokenLanguageModel? {
    return this?.let {
        SpokenLanguageModel(
            english_name = englishName,
            iso_639_1 = iso6391,
            name = name
        )
    }
}
