package org.example.firstcmpproject.movies.persistence.type_converters

import androidx.room.TypeConverter
import org.example.firstcmpproject.core.utils.universalJsonParser
import org.example.firstcmpproject.movies.data.vos.GenreVO

class OriginCountryTypeConverter {

    @TypeConverter
    fun fromOriginalCountry(originalCountryList: List<String>?) : String?{
        return originalCountryList?.let {
            universalJsonParser.encodeToString(it)
        }
    }

    @TypeConverter
    fun toOriginalCountry(jsonString: String?) : List<String>?{
        return jsonString?.let {
            universalJsonParser.decodeFromString(it)
        }
    }
}