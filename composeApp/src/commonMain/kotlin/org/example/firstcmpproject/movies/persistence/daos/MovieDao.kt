package org.example.firstcmpproject.movies.persistence.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.example.firstcmpproject.movies.data.vos.MovieVO

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movie : List<MovieVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSingleMovie(movieVO: MovieVO)

    @Query("SELECT * FROM movies WHERE id = :movieId LIMIT 1")
    suspend fun getMovieById(movieId : Long) : MovieVO?

    //reactive
    @Query("SELECT * FROM movies WHERE id = :movieId LIMIT 1")
     fun getMovieByIdFlow(movieId : Long) : Flow<MovieVO?>

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<MovieVO>

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()

}