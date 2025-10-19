package org.example.firstcmpproject.movies.persistence.type_converters

import androidx.room.TypeConverter
import org.example.firstcmpproject.core.utils.universalJsonParser
import org.example.firstcmpproject.movies.data.vos.GenreVO
import org.example.firstcmpproject.movies.data.vos.ProductionCompaniesVO
import org.example.firstcmpproject.movies.data.vos.ProductionCountriesVO
import org.example.firstcmpproject.movies.data.vos.SpokenLanguagesVO

class SpokenLanguageTypeConverter {

    @TypeConverter
    fun fromSpokenLanguage(spokenLanguages: List<SpokenLanguagesVO>?) : String?{
        return spokenLanguages?.let {
            universalJsonParser.encodeToString(it)
        }
    }

    @TypeConverter
    fun toSpokenLanguage(jsonString: String?) : List<SpokenLanguagesVO>?{
        return jsonString?.let {
            universalJsonParser.decodeFromString(it)
        }
    }
}