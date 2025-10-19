package org.example.firstcmpproject.movies.persistence.type_converters

import androidx.room.TypeConverter
import org.example.firstcmpproject.core.utils.universalJsonParser
import org.example.firstcmpproject.movies.data.vos.GenreVO
import org.example.firstcmpproject.movies.data.vos.ProductionCompaniesVO
import org.example.firstcmpproject.movies.data.vos.ProductionCountriesVO

class ProductionCountryTypeConverter {

    @TypeConverter
    fun fromProductionCountries(productionCountries: List<ProductionCountriesVO>?) : String?{
        return productionCountries?.let {
            universalJsonParser.encodeToString(it)
        }
    }

    @TypeConverter
    fun toProductionCountries(jsonString: String?) : List<ProductionCountriesVO>?{
        return jsonString?.let {
            universalJsonParser.decodeFromString(it)
        }
    }
}