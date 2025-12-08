package org.lasalle.recipeapp.data.services

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import org.enfria.project.services.httpClient
import org.lasalle.recipeapp.models.AuthResponse
import org.lasalle.recipeapp.models.LoginBody
import org.lasalle.recipeapp.models.RegisterBody

class AuthService() {
    private val client = httpClient
    private val BASE_URL = "https://recipes.pjasoft.com/api/"

    suspend fun register(request: RegisterBody): AuthResponse {
        val response = client.post("$BASE_URL/auth/register") {
            setBody(request)
        }
        return response.body()
    }

    suspend fun login(request: LoginBody): AuthResponse {
        val response = client.post("$BASE_URL/auth/login") {
            setBody(request)
        }
        return response.body()
    }
}