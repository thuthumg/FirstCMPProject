package org.example.firstcmpproject.movies.data.vos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class ProductionCompaniesVO(
    @SerialName("id")
    val id: Long,

    @SerialName("logo_path")
    val logoPath: String?,

    @SerialName("name")
    val name: String?,

    @SerialName("origin_country")
    val originCountry: String?
)