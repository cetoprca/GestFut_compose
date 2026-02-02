package com.example.gestfut_compose.ui.pantallas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gestfut.data.Equipo
import com.example.gestfut_compose.ui.components.equipoItem
import com.example.gestfut_compose.ui.components.partidoItem

@Composable
fun pantallaClasificacion(modifier: Modifier= Modifier,equipos:List<Equipo>) {
    // LazyColumn como reemplazo de RecyclerView
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(equipos) { equipo ->
            equipoItem(equipo)
        }
    }
}