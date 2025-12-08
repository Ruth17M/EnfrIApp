package org.enfria.project.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.enfria.project.models.Food
import org.enfria.project.services.EnfriaService
import kotlin.collections.listOf
import androidx.lifecycle.viewModelScope // Importante
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


class EnfriaViewModel(
    private val enfriaService: EnfriaService
) : ViewModel() {

    var productos by mutableStateOf<List<Food>>(listOf())
        private set

    var isLoading by mutableStateOf(false) // Estado de carga
        private set

    fun getProductos() {
        viewModelScope.launch {
            isLoading = true
            try {
                val result = enfriaService.getProductos()
                productos = result.alimentos_detectados
                println("Productos cargados: $productos")
            } catch (e: Exception) {
                println("Error al obtener productos: ${e}")
            } finally {
                isLoading = false
            }
        }
    }

    init {
        getProductos()
    }
}