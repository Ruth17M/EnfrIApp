package org.enfria.project.services

import de.jensklingenberg.ktorfit.http.GET
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.enfria.project.models.Food
import org.enfria.project.models.RespuestaProductos
import org.lasalle.recipeapp.models.Recipe

class EnfriaService(){
    private val client= httpClient
    private val BASE_URL = "http://192.168.1.66:5000"

    suspend fun getProductos(): RespuestaProductos {
        val url = "$BASE_URL/obtener_estado"
        val result = client.get(url)
        return result.body()
    }
}