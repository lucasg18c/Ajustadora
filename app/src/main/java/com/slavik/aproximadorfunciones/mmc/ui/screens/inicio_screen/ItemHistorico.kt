package com.slavik.aproximadorfunciones.mmc.ui.screens.inicio_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.slavik.aproximadorfunciones.mmc.modelo.ModeloAjuste
import com.slavik.aproximadorfunciones.mmc.ui.theme.Azul1
import com.slavik.aproximadorfunciones.mmc.util.Pruebas

@Composable
fun ItemHistorico(
    modelo: ModeloAjuste,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
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
                color = Azul1,
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
                        text = modelo.resumen(),
                        fontSize = 18.sp,
                        overflow = TextOverflow.Clip,
                        maxLines = 1
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (modelo.puntos.isNotEmpty()) "${modelo.puntos.size} puntos"
                        else "Sin puntos"
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
        modelo = Pruebas.modelos[0]
    )
}