package org.example.firstcmpproject.core.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetflixError(

    @SerialName("statusCode")
    val statusCode: Int,

    @SerialName("statusMessage")
    val statusMessage: String,

    @SerialName("success")
    val success: Boolean
)
