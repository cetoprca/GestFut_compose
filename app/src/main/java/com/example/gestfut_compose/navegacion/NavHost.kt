package com.example.gestfut_compose.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gestfut.data.EquipoProveedor
import com.example.gestfut.data.PartidoProveedor
import com.example.gestfut_compose.ui.pantallas.pantallaCalendario
import com.example.gestfut_compose.ui.pantallas.pantallaClasificacion

@Composable
fun miNavHost(modificador: Modifier =Modifier,controlador_navegacion: NavHostController){



    NavHost(navController = controlador_navegacion,
        startDestination = Calendario_ruta,
        modifier=modificador){

        //Defino las rutas
        composable<Calendario_ruta> {
            var jornadas by remember { mutableStateOf(listOf("1","2","3","4","5")) }
            var jornada_sel by remember { mutableStateOf("1") }

            pantallaCalendario(modificador= modificador, jornadas = jornadas, selectedJornada = jornada_sel,{valor_spinner->jornada_sel=valor_spinner},partidos= PartidoProveedor.partidos.filter {partido->partido.jornada==jornada_sel.toInt()  })

        }

        composable<Clasficacion_ruta> {
            pantallaClasificacion(modifier=modificador, EquipoProveedor.equipos)
        }

    }


}