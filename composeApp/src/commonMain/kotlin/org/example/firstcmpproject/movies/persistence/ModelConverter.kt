package org.example.firstcmpproject.movies.persistence

import org.example.firstcmpproject.core.persistence.Movie
import org.example.firstcmpproject.core.utils.universalJsonParser
import org.example.firstcmpproject.movies.data.vos.BelongsToCollectionVO
import org.example.firstcmpproject.movies.data.vos.GenreVO
import org.example.firstcmpproject.movies.data.vos.MovieVO
import org.example.firstcmpproject.movies.data.vos.ProductionCompaniesVO
import org.example.firstcmpproject.movies.data.vos.ProductionCountriesVO
import org.example.firstcmpproject.movies.data.vos.SpokenLanguagesVO

fun MovieVO.convertToPersistenceModel() : Movie {
    val belongsToCollectionJsonString = universalJsonParser.encodeToString(this.belongsToCollection)
    val genresJsonString = universalJsonParser.encodeToString(this.genres)
    val genreIdsJsonString = universalJsonParser.encodeToString(this.genreIds)
    val productionCompaniesJsonString = universalJsonParser.encodeToString(this.productionCompanies)
    val  productionCountriesJsonString = universalJsonParser.encodeToString(this.productionCountries)
    val spokenLanguageJsonSting = universalJsonParser.encodeToString(this.spokenLanguages)
    val originCountryJsonString = universalJsonParser.encodeToString(this.originCountry)

    return Movie(
        id = this.id,
        adult = this.adult,
        backdropPath = this.backdropPath,
        belongsToCollection = belongsToCollectionJsonString,
        budget = this.budget,
        genres = genresJsonString,
        homePage = this.homepage,
        imdbId = this.imdbId,
        originCountry = originCountryJsonString,
        genreIds = genreIdsJsonString,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        productionCompanies = productionCompaniesJsonString,
        productionCountries = productionCountriesJsonString,
        releaseDate = this.releaseDate,
        revenue = this.revenue,
        runtime = this.runtime?.toLong(),
        spokenLanguages = spokenLanguageJsonSting,
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount.toLong()
    )
}


fun Movie.convertToMovieVO(): MovieVO{
    val belongsToCollectionVO: BelongsToCollectionVO? =
        if(this.belongsToCollection != null) universalJsonParser.decodeFromString(this.belongsToCollection) else null

    val genres: List<GenreVO>? =
        if(this.genres != null) universalJsonParser.decodeFromString(this.genres) else null

    val genreIds: List<Int>? =
        if(this.genreIds != null) universalJsonParser.decodeFromString(this.genreIds) else null

    val productionCompanies: List<ProductionCompaniesVO>? =
        if(this.productionCompanies != null) universalJsonParser.decodeFromString(this.productionCompanies) else null

    val productionCountries: List<ProductionCountriesVO>? =
        if(this.productionCountries != null) universalJsonParser.decodeFromString(this.productionCountries) else null
    val spokenLanguages: List<SpokenLanguagesVO>? =
        if(this.spokenLanguages != null) universalJsonParser.decodeFromString(this.spokenLanguages) else null

    val originCountry: List<String>? =
        if(this.originCountry != null) universalJsonParser.decodeFromString(this.originCountry) else null

    
    return MovieVO(
        adult = this.adult ?: false,
        backdropPath = this.backdropPath.toString(),
        belongsToCollection = belongsToCollectionVO,
        budget = this.budget,
        genres = genres,
        genreIds = genreIds,
        homepage = this.homePage,
        id = this.id,
        imdbId = this.imdbId,
        originCountry = originCountry,
        originalLanguage = originalLanguage ?: "",
        originalTitle = this.originalTitle ?: "",
        overview =this.overview ?: "",
        popularity = this.popularity ?: 0.0,
        posterPath = this.posterPath ?: "",
        productionCompanies = productionCompanies,
        productionCountries = productionCountries,
        releaseDate = this.releaseDate ?: "",
        revenue = this.revenue,
        runtime = this.runtime?.toInt(),
        spokenLanguages = spokenLanguages,
        status = this.status,
        tagline = this.tagline,
        title = this.title ?: "",
        video = this.video ?: false,
        voteAverage = this.voteAverage ?: 0.0,
        voteCount = this.voteCount?.toInt() ?: 0
    )
}