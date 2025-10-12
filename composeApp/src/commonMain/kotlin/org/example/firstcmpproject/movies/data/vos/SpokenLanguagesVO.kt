package org.example.firstcmpproject.movies.data.vos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SpokenLanguagesVO(

    @SerialName("english_name")
    val englishName: String,

    @SerialName("iso_639_1")
    val iso6391: String,

    @SerialName("name")
    val name: String
)
