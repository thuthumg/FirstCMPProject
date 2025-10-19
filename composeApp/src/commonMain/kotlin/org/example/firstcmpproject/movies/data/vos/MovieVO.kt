package org.example.firstcmpproject.movies.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.firstcmpproject.core.utils.FEATURED_MOVIE_IMAGE_BASE_URL
import org.example.firstcmpproject.core.utils.MOVIE_ITEM_IMAGE_BASE_URL


@Entity("movies")
@Serializable
data class MovieVO(

    @ColumnInfo(name = "adult")
    @SerialName("adult")
    val adult: Boolean,


    @ColumnInfo(name = "backdrop_path")
    @SerialName("backdrop_path")
    val backdropPath: String,


    @ColumnInfo(name = "belongs_to_collection")
    @SerialName("belongs_to_collection")
    val belongsToCollection: BelongsToCollectionVO?,

    @ColumnInfo(name = "budget")
    @SerialName("budget")
    val budget: Long?,

    @ColumnInfo(name = "genres")
    @SerialName("genres")
    val genres: List<GenreVO>?,

    @ColumnInfo(name = "genre_ids")
    @SerialName("genre_ids")
    val genreIds: List<Int>?,


    @SerialName("homepage")
    val homepage: String?,


    @PrimaryKey(autoGenerate = false)
    @SerialName("id")
    val id: Long,

    @ColumnInfo(name = "imdb_id")
    @SerialName("imdb_id")
    val imdbId: String?,

    @ColumnInfo(name = "origin_country")
    @SerialName("origin_country")
    val originCountry: List<String>?,

    @ColumnInfo(name = "original_language")
    @SerialName("original_language")
    val originalLanguage: String,

    @ColumnInfo(name = "original_title")
    @SerialName("original_title")
    val originalTitle: String,


    @ColumnInfo(name = "overview")
    @SerialName("overview")
    val overview: String,


    @ColumnInfo(name = "popularity")
    @SerialName("popularity")
    val popularity: Double,

    @ColumnInfo(name = "poster_path")
    @SerialName("poster_path")
    val posterPath: String,


    @ColumnInfo(name = "production_companies")
    //production_country
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompaniesVO>?,


    @ColumnInfo(name = "production_countries")
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountriesVO>?,


    @ColumnInfo(name = "release_date")
    @SerialName("release_date")
    val releaseDate: String,


    @ColumnInfo(name = "revenue")
    @SerialName("revenue")
    val revenue: Long?,


    @ColumnInfo(name = "runtime")
    @SerialName("runtime")
    val runtime: Int?,


    @ColumnInfo(name = "spoken_languages")
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguagesVO>?,

    @ColumnInfo(name = "status")
    @SerialName("status")
    val status: String?,


    @ColumnInfo(name = "tagline")
    @SerialName("tagline")
    val tagline: String?,


    @ColumnInfo(name = "title")
    @SerialName("title")
    val title: String,


    @ColumnInfo(name = "video")
    @SerialName("video")
    val video: Boolean,

    @ColumnInfo(name = "vote_average")
    @SerialName("vote_average")
    val voteAverage: Double,


    @ColumnInfo(name = "vote_count")
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
