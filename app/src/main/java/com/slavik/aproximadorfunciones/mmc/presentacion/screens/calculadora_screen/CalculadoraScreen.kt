@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED", "EXPERIMENTAL_IS_NOT_ENABLED")

package com.slavik.aproximadorfunciones.mmc.presentacion.screens.calculadora_screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.flowlayout.FlowRow
import com.slavik.aproximadorfunciones.R
import com.slavik.aproximadorfunciones.mmc.modelo.ModeloAjuste
import com.slavik.aproximadorfunciones.mmc.modelo.Punto
import com.slavik.aproximadorfunciones.mmc.presentacion.componentes.Grafica
import com.slavik.aproximadorfunciones.mmc.presentacion.componentes.TopBar
import com.slavik.aproximadorfunciones.mmc.presentacion.navegacion.Destino
import com.slavik.aproximadorfunciones.mmc.presentacion.theme.AmarilloOscuro
import com.slavik.aproximadorfunciones.mmc.presentacion.theme.Azul1
import com.slavik.aproximadorfunciones.mmc.presentacion.theme.Naranja1
import com.slavik.aproximadorfunciones.mmc.presentacion.theme.NaranjaOscuro
import com.slavik.aproximadorfunciones.mmc.util.EventoUI
import com.slavik.aproximadorfunciones.mmc.util.Pruebas
import kotlinx.coroutines.flow.collect

@Composable
fun CalculadoraScreen(
    navegar: (Destino) -> Unit,
    vm: CalculadoraVM = viewModel() //todo usar di
) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = true) {
        vm.cambioModelo()
        vm.eventoUI.collect {
            when (it) {
                is EventoUI.Snackbar -> {
                    if (scaffoldState.snackbarHostState.showSnackbar(it.mensaje, it.action) == SnackbarResult.ActionPerformed) {
                        vm.eliminarPuntosConfirmado()
                    }
                }
                else -> Unit
            }
        }
    }

    DisposableEffect(key1 = vm) {
        onDispose {
            vm.onDispose()
        }
    }

    Contenido(
        navegar = navegar,
        scaffoldState = scaffoldState,
        scrollState = scrollState,
        vm.modelo,
        vm.puntos,
        x = vm.x,
        y = vm.y,
        vm.puntoSeleccionado,
        vm::cambioX,
        vm::cambioY,
        vm::cambioPuntoSeleccionado,
        vm::agregarEditarPunto,
        vm::eliminarPunto,
        vm::eliminarPuntos
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Contenido(
    navegar: (Destino) -> Unit,
    scaffoldState: ScaffoldState,
    scrollState: ScrollState,
    modelo: ModeloAjuste,
    puntos: List<Punto>,
    x: String,
    y: String,
    puntoSeleccionado: Punto?,
    cambioX: (String) -> Unit,
    cambioY: (String) -> Unit,
    cambioPuntoSeleccionado: (Punto?) -> Unit,
    agregarEditarPunto: () -> Unit,
    eliminarPunto: () -> Unit,
    eliminarPuntos: () -> Unit,
) {

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                titulo = modelo.nombre ?: "Ajustadora",
                navegar = navegar,
                mostrarEditar = true
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navegar(Destino.Formas)
                    },
                elevation = 8.dp,
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = modelo.funcion.imagen),
                        contentDescription = "Forma de la función",
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp),
                        contentScale = ContentScale.FillBounds
                    )
                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = modelo.funcion.nombre,
                        fontSize = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navegar(Destino.Resultado)
                    },
                elevation = 8.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    if (puntos.isNotEmpty()) {

                        Grafica(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp)
                                .padding(12.dp),
                            modelo = modelo
                        )

                        Text(
                            text = modelo.funcion.getFormula(),
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Error: ${modelo.getError()}",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                    } else {
                        Spacer(modifier = Modifier.height(12.dp))

                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_grafica_ilustracion),
                            contentDescription = "Sin puntos cargados",
                            modifier = Modifier
                                .fillMaxWidth(.6F),
                            contentScale = ContentScale.FillWidth
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Agregá algunos puntos 🥺",
                            fontSize = 18.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Puntos",
                    fontSize = 22.sp,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = x,
                        onValueChange = { cambioX(it) },
                        label = {
                            Text(
                                text = modelo.ejeX
                            )
                        },
                        modifier = Modifier.width(80.dp),
                        textStyle = TextStyle(textAlign = TextAlign.Center),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    OutlinedTextField(
                        value = y,
                        onValueChange = { cambioY(it) },
                        label = {
                            Text(
                                text = modelo.ejeY
                            )
                        },
                        modifier = Modifier.width(80.dp),
                        textStyle = TextStyle(textAlign = TextAlign.Center),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }

                if (puntoSeleccionado == null) {

                    IconButton(
                        onClick = {
                            agregarEditarPunto()
                        },
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Naranja1)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Agregar punto",
                            tint = Color.White
                        )
                    }

                } else {
                    Column {
                        IconButton(
                            onClick = {
                                agregarEditarPunto()
                            },
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Azul1)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Editar punto",
                                tint = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        IconButton(
                            onClick = {
                                cambioPuntoSeleccionado(null)
                            },
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(AmarilloOscuro)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Cancelar edición punto",
                                tint = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        IconButton(
                            onClick = {
                                eliminarPunto()
                            },
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(NaranjaOscuro)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Eliminar punto",
                                tint = Color.White
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (puntos.isNotEmpty()) {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = 10.dp,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {

                        FlowRow {
                            puntos.forEach { punto ->
                                Text(
                                    text = punto.toString(),
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .clickable {
                                            cambioPuntoSeleccionado(punto)
                                        }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(
                                onClick = {
                                    eliminarPuntos()
                                }) {
                                Text(
                                    text = "Limpiar 🗑",
                                    textAlign = TextAlign.End,
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CalculadoraScreenPreview(
) {
    Contenido(
        navegar = {},
        rememberScaffoldState(),
        rememberScrollState(),
        modelo = Pruebas.modelos[2],
        puntos = Pruebas.modelos[2].puntos,
        "12",
        "12",
        null,
        {},
        {},
        {},
        {},
        {},
        {}
    )
}