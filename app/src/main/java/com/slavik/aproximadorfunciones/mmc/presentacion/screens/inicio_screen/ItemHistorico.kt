package com.slavik.aproximadorfunciones.mmc.presentacion.screens.inicio_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.slavik.aproximadorfunciones.mmc.modelo.funciones.Funcion
import com.slavik.aproximadorfunciones.mmc.modelo.ModeloAjuste
import com.slavik.aproximadorfunciones.mmc.modelo.Punto
import com.slavik.aproximadorfunciones.mmc.modelo.funciones.Lineal
import com.slavik.aproximadorfunciones.mmc.presentacion.theme.Celeste
import java.util.*

@Composable
fun ItemHistorico(
    modelo: ModeloAjuste
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Surface(
                modifier = Modifier
                    .width(26.dp)
                    .height(26.dp),
                color = Celeste,
                shape = RoundedCornerShape(13.dp)
            ) {}

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = modelo.resumen(20),
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${modelo.cantidadPuntos()} puntos"
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = modelo.fechaAsString()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemHistoricoPreview() {
    ItemHistorico(
        modelo = ModeloAjuste(
            mutableListOf(
                Punto(1f, 4f),
                Punto(5f, 8f),
                Punto(7f, 14f),
                Punto(11f, 24f),
            ),
            fecha = Calendar.getInstance(),
            funcion = Lineal()
        )
    )
}