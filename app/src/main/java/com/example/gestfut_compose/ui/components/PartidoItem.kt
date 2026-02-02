package com.example.gestfut_compose.ui.components

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toLong
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestfut.data.Partido
import com.example.gestfut.data.PartidoProveedor
import com.example.gestfut_compose.R
import com.example.gestfut_compose.ui.theme.ColorAccent
import com.example.gestfut_compose.ui.theme.ColorPrimaryDark
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.logging.SimpleFormatter


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
    fun partidoItem(partido: Partido) {

        var showDialog by remember { mutableStateOf(false) }


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            colors = CardDefaults.cardColors(containerColor = ColorAccent),
            elevation = CardDefaults.cardElevation(8.dp),

            onClick = { showDialog = true }
        ) {
            Column(modifier = Modifier.padding(8.dp)) {

                // Primera fila: Logos y goles
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Logo local
                    Image(
                        painter = painterResource(R.drawable.ic_soccer),
                        contentDescription = "Logo Local",
                        modifier = Modifier.size(40.dp),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    // Goles local
                    Text(
                        text = partido.goles_local.toString(),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorPrimaryDark
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // Guion "-"
                    Text(
                        text = "-",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorPrimaryDark
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // Goles visitante
                    Text(
                        text = partido.goles_visitante.toString(),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorPrimaryDark
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    // Logo visitante
                    Image(
                        painter = painterResource(R.drawable.ic_soccer),
                        contentDescription = "Logo Visitante",
                        modifier = Modifier.size(40.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Segunda fila: Nombres de equipos
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = partido.equipo_local,
                        fontSize = 12.sp
                    )
                    Text(
                        text = partido.equipo_visitante,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Fecha del partido
                Text(
                    text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(partido.fecha*1000)),
                    fontSize = 10.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                // Jornada
                Text(
                    text = partido.jornada.toString(),
                    fontSize = 10.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            if (showDialog){
                BasicAlertDialog(
                    onDismissRequest = { showDialog = false },
                    modifier = Modifier
                ){
                    Surface(
                        modifier = Modifier
                            .width(300.dp)
                            .height(200.dp)
                            .padding(8.dp)
                    ) {
                        var localTextField by remember { mutableStateOf("") }
                        var visitanteTextField by remember { mutableStateOf("") }

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("Cambiar puntuación", fontWeight = FontWeight.Bold)

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Puntos locales", fontSize = 12.sp)
                                Text("Puntos visitante", fontSize = 12.sp)
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                TextField(
                                    value = localTextField,
                                    onValueChange = { if (it.all { char -> char.isDigit() }) localTextField = it },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    modifier = Modifier.width(60.dp),
                                    singleLine = true
                                )

                                TextField(
                                    value = visitanteTextField,
                                    onValueChange = { if (it.all { char -> char.isDigit() }) visitanteTextField = it },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    modifier = Modifier.width(60.dp),
                                    singleLine = true
                                )
                            }

                            ElevatedButton(
                                onClick = {
                                    val indice = PartidoProveedor.partidos.indexOf(partido)
                                    if (localTextField.isNotEmpty() && visitanteTextField.isNotEmpty()) {
                                        partido.goles_local = localTextField.toInt()
                                        partido.goles_visitante = visitanteTextField.toInt()
                                        PartidoProveedor.editarPartido(indice, partido)

                                        showDialog = false
                                    }
                                },
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            ) {
                                Text("Guardar datos")
                            }
                        }
                    }
                }
            }

        }
    }
