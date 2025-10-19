package org.example.firstcmpproject.movies.persistence.type_converters

import androidx.room.TypeConverter
import org.example.firstcmpproject.core.utils.universalJsonParser
import org.example.firstcmpproject.movies.data.vos.GenreVO

class GenreIdsTypeConverter {

    @TypeConverter
    fun fromGenreIds(genreIdsList: List<Int>?) : String?{
        return genreIdsList?.let {
            universalJsonParser.encodeToString(it)
        }
    }

    @TypeConverter
    fun toGenreIds(jsonString: String?) : List<Int>?{
        return jsonString?.let {
            universalJsonParser.decodeFromString(it)
        }
    }
}