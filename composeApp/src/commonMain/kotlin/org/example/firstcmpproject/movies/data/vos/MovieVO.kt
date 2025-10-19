package org.example.firstcmpproject.movies.data.vos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.firstcmpproject.core.utils.FEATURED_MOVIE_IMAGE_BASE_URL
import org.example.firstcmpproject.core.utils.MOVIE_ITEM_IMAGE_BASE_URL

@Serializable
data class MovieVO(

    @SerialName("adult")
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String,


    @SerialName("belongs_to_collection")
    val belongsToCollection: BelongsToCollectionVO?,


    @SerialName("budget")
    val budget: Long?,


    @SerialName("genres")
    val genres: List<GenreVO>?,


    @SerialName("genre_ids")
    val genreIds: List<Int>?,


    @SerialName("homepage")
    val homepage: String?,


    @SerialName("id")
    val id: Long,


    @SerialName("imdb_id")
    val imdbId: String?,


    @SerialName("origin_country")
    val originCountry: List<String>?,


    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_title")
    val originalTitle: String,

    @SerialName("overview")
    val overview: String,

    @SerialName("popularity")
    val popularity: Double,

    @SerialName("poster_path")
    val posterPath: String,


    //production_country
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompaniesVO>?,

    @SerialName("production_countries")
    val productionCountries: List<ProductionCountriesVO>?,


    @SerialName("release_date")
    val releaseDate: String,

    @SerialName("revenue")
    val revenue: Long?,

    @SerialName("runtime")
    val runtime: Int?,

    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguagesVO>?,


    @SerialName("status")
    val status: String?,

    @SerialName("tagline")
    val tagline: String?,


    @SerialName("title")
    val title: String,

    @SerialName("video")
    val video: Boolean,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Int
){
    fun getFullMoviePosterPath() : String{
        return "$FEATURED_MOVIE_IMAGE_BASE_URL$posterPath"
    }

    fun getFullMovieBackdropPath() : String{
        return "$MOVIE_ITEM_IMAGE_BASE_URL$backdropPath"
    }





    // Extract only year (e.g., "2021")
    val year: String
        get() = releaseDate.take(4)

    // Convert runtime minutes → "Xh Ym" format
    val formattedRuntime: String
        get() = runtime?.let {
            val hours = it / 60
            val minutes = it % 60
            "${hours}h ${minutes}m"
        } ?: "-"

}
