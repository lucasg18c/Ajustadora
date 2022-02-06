package com.slavik.aproximadorfunciones.mmc.presentacion.screens.inicio_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.slavik.aproximadorfunciones.R
import com.slavik.aproximadorfunciones.mmc.modelo.ModeloAjuste
import com.slavik.aproximadorfunciones.mmc.presentacion.componentes.TopBar
import com.slavik.aproximadorfunciones.mmc.presentacion.navegacion.Destino
import com.slavik.aproximadorfunciones.mmc.presentacion.theme.Naranja1
import com.slavik.aproximadorfunciones.mmc.util.Pruebas

@Composable
fun InicioScreen(
    navegar: (Destino) -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    InicioScreenContenido(
        { navegar(it) },
        scaffoldState = scaffoldState,
        emptyList()
    )
}

@Composable
@Preview(showBackground = true)
fun InicioScreenPreview() {
    InicioScreenContenido(
        {},
        rememberScaffoldState(),
        Pruebas.modelos
    )
}


@Composable
private fun InicioScreenContenido(
    navegar: (Destino) -> Unit,
    scaffoldState: ScaffoldState,
    modelos: List<ModeloAjuste>,
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                titulo = "Ajustadora",
                navegar = { navegar(it) }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navegar(Destino.Calculadora)
                    },
                backgroundColor = Naranja1,
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 32.dp,
                            horizontal = 16.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Image(
                            imageVector = ImageVector
                                .vectorResource(
                                    id = R.drawable.ic_logo_blanco
                                ),
                            contentDescription = "Logo",
                            modifier = Modifier
                                .width(70.dp)
                                .height(70.dp),
                            contentScale = ContentScale.FillBounds
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "Ajustar Función",
                        color = Color.White,
                        fontSize = 26.sp
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Histórico",
                    modifier = Modifier
                        .padding(
                            vertical = 24.dp
                        ),
                    fontSize = 24.sp
                )
            }

            if (modelos.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(modelos) { modelo ->
                        ItemHistorico(modelo)

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            } else {
                Spacer(modifier = Modifier.height(12.dp))

                Image(
                    imageVector = ImageVector.vectorResource(
                        id = R.drawable.ic_pizarra
                    ),
                    contentDescription = "Sin modelos",
                    modifier = Modifier
                        .fillMaxWidth(.7F),
                    contentScale = ContentScale.FillWidth
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Todavía no has creado modelos 🧐",
                    textAlign = TextAlign.Center,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = Color(0xBA000000)
                )
            }
        }
    }
}
