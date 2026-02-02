package com.example.gestfut_compose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestfut.data.Equipo
import com.example.gestfut_compose.R
import com.example.gestfut_compose.ui.theme.ColorAccent
import com.example.gestfut_compose.ui.theme.ColorPrimaryDark
import com.example.gestfut_compose.ui.theme.Divider

@Composable
fun equipoCard(
    equipo: Equipo
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = ColorAccent),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {

            Row() {
                Box(
                    modifier = Modifier.width(60.dp)
                ) {
                    Column() {

                        Image(
                            painter = painterResource(R.drawable.ic_soccer),
                            contentDescription = "Logo Local",
                            modifier = Modifier.size(40.dp),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(equipo.nombre, fontSize = 10.sp)

                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray)
                ) {
                    val pj = equipo.pg + equipo.PE + equipo.pp
                    val datos = listOf(
                        listOf("PJ", "PG", "PE", "PP", "PUNTOS"),
                        listOf(pj, equipo.pg, equipo.PE, equipo.pp, equipo.puntos)
                    )

                    datos.forEach { row ->
                        Row(modifier = Modifier.fillMaxWidth()) {
                            row.forEachIndexed { index, cell ->
                                Box(
                                    modifier = Modifier
                                        .weight(if(index == row.lastIndex) 2f else 1f)
                                        .border(1.dp, Color.Gray)
                                        .padding(5.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = cell.toString(), fontSize = 10.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}