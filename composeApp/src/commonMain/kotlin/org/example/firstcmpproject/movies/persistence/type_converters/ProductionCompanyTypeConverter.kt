package org.example.firstcmpproject.movies.persistence.type_converters

import androidx.room.TypeConverter
import org.example.firstcmpproject.core.utils.universalJsonParser
import org.example.firstcmpproject.movies.data.vos.GenreVO
import org.example.firstcmpproject.movies.data.vos.ProductionCompaniesVO

class ProductionCompanyTypeConverter {

    @TypeConverter
    fun fromProductionCompanies(productionCompanies: List<ProductionCompaniesVO>?) : String?{
        return productionCompanies?.let {
            universalJsonParser.encodeToString(it)
        }
    }

    @TypeConverter
    fun toProductionCompanies(jsonString: String?) : List<ProductionCompaniesVO>?{
        return jsonString?.let {
            universalJsonParser.decodeFromString(it)
        }
    }
}