package org.example.firstcmpproject.core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.firstcmpproject.core.utils.URL
import kotlin.time.Duration.Companion.seconds

object HttpClientProvider {

    val httpClient by lazy{
        HttpClient{
            //Json  Serialization
            install(ContentNegotiation){
                //Configuration
                json(
                    Json {
                        ignoreUnknownKeys = true
                        explicitNulls = false
                        prettyPrint = true
                    }
                )
            }

            //Default Request
            install(DefaultRequest){
                url(URL)

                header("Accept","application/json")
                header("Content-Type","application/json")
            }

            //Time out
            install(HttpTimeout){
                connectTimeoutMillis = 30.seconds.inWholeMilliseconds
                socketTimeoutMillis = 30.seconds.inWholeMilliseconds
                requestTimeoutMillis = 30.seconds.inWholeMilliseconds
            }
        }
    }
}