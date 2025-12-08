package org.enfria.project.services

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val httpClient = HttpClient {
    expectSuccess = false
    // PERMITE QUE LA API TARDE MÁS EN SU RESPUESTA { POR DEFECTO SOLO DA 10 S}
    install(HttpTimeout){
        requestTimeoutMillis = 40000
        connectTimeoutMillis = 40000
        socketTimeoutMillis = 40000
    }
    // XML -> SOAP
    install(ContentNegotiation){
        json(
            Json {
                isLenient = true // PERMITE JSONS IMPERFECTOS O INCOMPLETOS
                ignoreUnknownKeys = true // IGNORA PROPIEDADES NO MAPEADAS
            }
        )
    }
    defaultRequest {
        contentType(ContentType.Application.Json)
        accept(ContentType.Application.Json)
    }

}