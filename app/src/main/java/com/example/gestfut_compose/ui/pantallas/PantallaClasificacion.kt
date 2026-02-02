package com.example.gestfut_compose.ui.pantallas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gestfut.data.Equipo
import com.example.gestfut_compose.ui.components.equipoCard

@Composable
fun pantallaClasificacion(
    equipos: List<Equipo>
){
    LazyColumn(
        Modifier.fillMaxSize()
    ) {
        items(equipos){ equipo ->
            equipoCard(equipo)
        }
    }
}