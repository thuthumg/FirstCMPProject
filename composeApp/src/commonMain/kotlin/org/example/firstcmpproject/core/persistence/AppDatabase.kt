package org.example.firstcmpproject.core.persistence

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import org.example.firstcmpproject.movies.data.vos.MovieVO
import org.example.firstcmpproject.movies.persistence.daos.MovieDao
import org.example.firstcmpproject.movies.persistence.type_converters.BelongsToCollectionTypeConverter
import org.example.firstcmpproject.movies.persistence.type_converters.GenreIdsTypeConverter
import org.example.firstcmpproject.movies.persistence.type_converters.GenreListTypeConverter
import org.example.firstcmpproject.movies.persistence.type_converters.OriginCountryTypeConverter
import org.example.firstcmpproject.movies.persistence.type_converters.ProductionCompanyTypeConverter
import org.example.firstcmpproject.movies.persistence.type_converters.ProductionCountryTypeConverter
import org.example.firstcmpproject.movies.persistence.type_converters.SpokenLanguageTypeConverter

@Database(
    entities = [MovieVO::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(
    BelongsToCollectionTypeConverter::class,
    GenreIdsTypeConverter::class,
    GenreListTypeConverter::class,
    OriginCountryTypeConverter::class,
    ProductionCompanyTypeConverter::class,
    ProductionCountryTypeConverter::class,
    SpokenLanguageTypeConverter::class
)

@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDao
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase>{
    override fun initialize(): AppDatabase
}