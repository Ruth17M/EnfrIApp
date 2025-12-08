package org.enfria.project.ui.screeens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController



data class Recipe(
    val name: String,
    val time: String,
    val rating: String,
    val backgroundColor: Color
)
val fakeRecipes = listOf(
    Recipe("Manzana", "3 días", "4.3", Color(0xFFFFF3D1)),
    Recipe("Platano", "10 días", "4.3", Color(0xFFEFFFD9)),
    Recipe("Leche", "30 días", "4.3", Color(0xFFFFE1BD)),
    Recipe("Huevo", "45 días", "4.3", Color(0xFFE3FFCE)),
    Recipe("Queso", "15 días", "4.3", Color(0xFFF5F5F5)),
    Recipe("Jitomate", "25 días", "4.3", Color(0xFFFFD6D6))
)

val categories = listOf("Lacteos", "Fruta", "Verdura", "Embutidos")


@Composable
fun RecipeScreen(navController: NavController) {
    val colors = MaterialTheme.colorScheme
    var selectedCategory by remember { mutableStateOf("Lacteos") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F4F3))
            .padding(16.dp)
    ) {

        // TOP BAR
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(28.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "All recipes",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F2C1D)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        val orangeColor = Color(0xFFE67C24)
        // SEARCH BAR
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search here") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null, tint = orangeColor)
            },
            shape = RoundedCornerShape(50),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = orangeColor
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // CATEGORIES ROW
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            categories.forEach { cat ->
                val isSelected = cat == selectedCategory

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(if (isSelected) orangeColor else Color.White)
                        .clickable { selectedCategory = cat }
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = cat,
                        color = if (isSelected) Color.White else orangeColor
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // LIST
        LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            items(fakeRecipes) { recipe ->
                RecipeItem(recipe)
            }
        }
    }
}


@Composable
fun RecipeItem(recipe: Recipe) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(14.dp)
    ) {

        // ICON BOX (FAKE)
        Box(
            modifier = Modifier
                .size(55.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        )

        Spacer(modifier = Modifier.width(14.dp))

        Column(modifier = Modifier.weight(1f)) {

            Text("recipe.name", fontWeight = FontWeight.Bold, fontSize = 18.sp)

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("⏱ ${"recipe.time"}", fontSize = 14.sp)
                Spacer(modifier = Modifier.width(8.dp))
//                Text("⭐ ${recipe.rating}", fontSize = 14.sp)
            }
        }

//        Icon(
//            imageVector = Icons.Default.ArrowBack,
//            contentDescription = null,
//            modifier = Modifier.size(18.dp)
//        )
    }
}
