package org.lasalle.recipeapp.data.services

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Query
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.enfria.project.services.httpClient
import org.lasalle.recipeapp.models.Prompt
import org.lasalle.recipeapp.models.Recipe
import org.lasalle.recipeapp.models.RecipeReview

class RecipeService() {
    private val client= httpClient
    private val BASE_URL = "https://recipes.pjasoft.com/api/"

    suspend fun getRecipeByUserId(userId: Int): List<Recipe> {
        val url = "$BASE_URL/recipes"
        return client.get(url) {
            parameter("userId", userId)
        }.body()
    }

    suspend fun generateRecipe(request: Prompt): RecipeReview {
        val url = "$BASE_URL/recipes/ai-generate"

        return client.post(url) {
            contentType(ContentType.Application.Json)
            // Maneja el @Body: Establece el objeto 'request' como cuerpo de la petición
            setBody(request)
        }.body()
    }

    suspend fun saveRecipeInDb(request: Recipe): Recipe {
        val url = "$BASE_URL/recipes"
        return client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
}