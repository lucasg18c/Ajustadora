package com.slavik.aproximadorfunciones.mmc.ui.screens.formas_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.slavik.aproximadorfunciones.R
import com.slavik.aproximadorfunciones.mmc.modelo.funciones.*
import com.slavik.aproximadorfunciones.mmc.ui.componentes.TopBar
import com.slavik.aproximadorfunciones.mmc.util.EventoUI
import kotlinx.coroutines.flow.collect

@Composable
fun FormasScreen(
    volver: () -> Unit,
    vm: FormasVM = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        vm.evento.collect {
            when (it){
                is EventoUI.Volver -> volver()
                else -> Unit
            }
        }
    }

    FormasScreenContenido(
        volver = volver,
        scaffoldState = scaffoldState,
        formaElegida = vm::elegirForma
    )

}

@Composable
private fun FormasScreenContenido(
    volver: () -> Unit,
    scaffoldState: ScaffoldState,
    formaElegida: (Funcion) -> Unit
) {

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                titulo = "Elegir forma",
                navegar = {},
                mostrarConfiguraciones = false
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
        ) {

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                Card(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F)
                        .clickable {
                            formaElegida(Lineal())
                        }
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(
                            id = R.drawable.ic_lineal
                        ),
                        contentDescription = "Lineal",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentScale = ContentScale.FillWidth
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Card(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F)
                        .clickable {
                            formaElegida(Parabola())
                        }
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(
                            id = R.drawable.ic_parabola
                        ),
                        contentDescription = "Parábola",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                Card(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F)
                        .clickable {
                            formaElegida(Exponencial())
                        }
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(
                            id = R.drawable.ic_exponencial
                        ),
                        contentDescription = "Exponencial",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentScale = ContentScale.FillWidth
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Card(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F)
                        .clickable {
                            formaElegida(Logaritmica())
                        }
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(
                            id = R.drawable.ic_logaritmica
                        ),
                        contentDescription = "Logarítimca",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                elevation = 8.dp,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        volver()
                    }
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(
                        id = R.drawable.ic_automatica
                    ),
                    contentDescription = "Automática",
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .padding(16.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Prev() {
    FormasScreenContenido(
        volver = { /*TODO*/ },
        scaffoldState = rememberScaffoldState(),
        {}
    )
}