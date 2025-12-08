package org.enfria.project.models

data class RespuestaProductos(
    val alimentos_detectados: List<Food>,
    val frescos: Int,
    val imagen_original: String,
    val mensaje: String,
    val podridos: Int,
    val scan_id: Int,
    val timestamp: String,
    val total_alimentos: Int
)