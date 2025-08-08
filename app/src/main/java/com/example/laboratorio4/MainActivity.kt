// MainActivity.kt

// Universidad del Valle
// Programación de Plataformas Móviles
// Sección 20
// Laboratorio 4
// Hugo Méndez Lee - 241265

package com.example.laboratorio4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laboratorio4.ui.theme.Laboratorio4Theme

data class Mascota(val id: Int, val nombre: String, val raza: String, val imagen: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                Mascotas()
            }
        }
    }
}

@Composable
fun Mascotas() {
    val mascotas = listOf(
        Mascota(1, "Max", "Terrier", R.mipmap.perro1_foreground),
        Mascota(2, "Chip", "Beagle", R.mipmap.perro2_foreground),
        Mascota(3, "Scott", "Golden", R.mipmap.perro3_foreground),
        Mascota(4, "Spiky", "Siames", R.mipmap.gato1_foreground),
        Mascota(5, "Rocky", "Tuxedo", R.mipmap.gato2_foreground),
        Mascota(6, "Sombra", "Siberiano", R.mipmap.gato3_foreground)
    )

    LazyColumn {
        items(mascotas) { mascota ->
            TarjetaMascota(mascota = mascota)
        }
    }
}

@Composable
fun TarjetaMascota(mascota: Mascota) {
    var adoptado by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = mascota.imagen),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = mascota.nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = mascota.raza,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            Button(onClick = { adoptado = !adoptado }) {
                Text(text = if (adoptado) "¡Adoptado! ❤" else "Adoptar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Laboratorio4Theme {
        Mascotas()
    }
}