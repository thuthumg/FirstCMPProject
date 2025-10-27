package org.example.firstcmpproject.movies.persistence

import org.example.firstcmpproject.core.persistence.DatabaseProvider
import org.example.firstcmpproject.core.persistence.Movie
import org.example.firstcmpproject.movies.data.vos.MovieVO

object MovieDao {
    val dbQuery = DatabaseProvider.database.dbQuery

    fun insertMovies(movies: List<MovieVO>){
        val moviesForPersistence : List<Movie> = movies.map {
            it.convertToPersistenceModel()
        }

        dbQuery.transaction{
            moviesForPersistence.forEach {
                dbQuery.insertPartialMovie(
                    id = it.id,
                    adult = it.adult,
                    backdropPath = it.backdropPath,
                    genreIds = it.genreIds,
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    releaseDate = it.releaseDate,
                    title = it.title,
                    video = it.video,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount
                )
            }
        }
    }

    fun insertSingleMovie(movieVO: MovieVO){
        val movieForPersistence = movieVO.convertToPersistenceModel()
        with(movieForPersistence){
            dbQuery.insertFullMovie(
                id = id,
                adult = adult,
                backdropPath = backdropPath,
                belongsToCollection = belongsToCollection,
                budget = budget,
                genres = genres,
                homePage = homePage,
                imdbId = imdbId,
                originCountry = originCountry,
                genreIds = genreIds,
                originalLanguage = originalLanguage,
                originalTitle = originalTitle,
                overview = overview,
                popularity = popularity,
                posterPath = posterPath,
                productionCompanies = productionCompanies,
                productionCountries = productionCountries,
                releaseDate = releaseDate,
                revenue = revenue,
                runtime = runtime,
                spokenLanguages = spokenLanguages,
                status = status,
                tagline = tagline,
                title = title,
                video = video,
                voteAverage = voteAverage,
                voteCount = voteCount
            )
        }

    }

    fun getMovieById(movieId: Int): MovieVO?{
        val movie = dbQuery.getMovieById(movieId.toLong()).executeAsOneOrNull()

        return movie?.convertToMovieVO()
    }
}