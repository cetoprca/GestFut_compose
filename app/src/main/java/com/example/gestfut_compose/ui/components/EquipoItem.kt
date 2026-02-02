package com.example.gestfut_compose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestfut.data.Equipo
import com.example.gestfut_compose.R
import com.example.gestfut_compose.ui.theme.ColorAccent

@Composable
fun equipoItem(equipo: Equipo)
{
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = ColorAccent),
        elevation = CardDefaults.cardElevation(8.dp)
    ){

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)){
            Column(){

                Image(painter = painterResource(R.drawable.ic_soccer), contentDescription = "")
                Text(text=equipo.nombre)

            }
            Text(text="")
            val pj=equipo.pg+equipo.pp+equipo.PE
            Text(text= pj.toString())
            Text(text=equipo.pg.toString())
            Text(text=equipo.PE.toString())
            Text(text=equipo.pp.toString())
            Text(text=equipo.puntos.toString(), fontSize = 10.sp)
        }

    }
}

@Preview
@Composable
fun equipoItempreview()
{
    equipoItem(Equipo("R.Valladolid",0,0,0,"","",1902,0,0,"Jose Zorrilla",""))
}