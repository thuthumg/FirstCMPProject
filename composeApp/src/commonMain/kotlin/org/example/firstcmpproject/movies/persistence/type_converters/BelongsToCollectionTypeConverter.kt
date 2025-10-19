package org.example.firstcmpproject.movies.persistence.type_converters

import androidx.room.TypeConverter
import org.example.firstcmpproject.core.utils.universalJsonParser
import org.example.firstcmpproject.movies.data.vos.BelongsToCollectionVO

class BelongsToCollectionTypeConverter {

    @TypeConverter
    fun fromBelongsToCollection(belongsToCollectionVO: BelongsToCollectionVO?) : String?{
        return belongsToCollectionVO?.let {
            universalJsonParser.encodeToString(it)
        }
    }

    @TypeConverter
    fun toBelongsToCollection(jsonString: String?): BelongsToCollectionVO?{
        return jsonString?.let {
            universalJsonParser.decodeFromString(it)
        }
    }
}