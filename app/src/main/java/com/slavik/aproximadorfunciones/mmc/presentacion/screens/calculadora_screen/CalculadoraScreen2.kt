@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED")

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
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import com.slavik.aproximadorfunciones.R
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.ModeloAjuste
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.Punto
import com.slavik.aproximadorfunciones.mmc.presentacion.componentes.Grafica
import com.slavik.aproximadorfunciones.mmc.presentacion.componentes.TopBar
import com.slavik.aproximadorfunciones.mmc.presentacion.navegacion.Destino
import com.slavik.aproximadorfunciones.mmc.presentacion.theme.*
import com.slavik.aproximadorfunciones.mmc.util.EventoUI
import com.slavik.aproximadorfunciones.mmc.util.Pruebas
import kotlinx.coroutines.flow.collect

@Composable
fun CalculadoraScreen2(
    navegar: (String) -> Unit,
    vm: CalculadoraVM = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {

        vm.eventoUI.collect {
            when (it) {
                is EventoUI.Navegar -> navegar(it.destino.ruta)
                is EventoUI.Snackbar -> {
                    // Se usa solo para confirmar si se eliminan todos los puntos
                    val resultado = scaffoldState.snackbarHostState.showSnackbar(
                        it.mensaje,
                        it.action
                    )

                    if (resultado == SnackbarResult.ActionPerformed)
                        vm.onEvento(EventoCalculadora.EliminarPuntos(true))
                }
                else -> Unit
            }
        }
    }

    Contenido(
        navegar = navegar,
        scaffoldState = scaffoldState,
        modelo = vm.modelo,
        x = vm.x,
        y = vm.y,
        mostrarPuntos = vm.mostrarPuntos,
        puntoSeleccionado = vm.puntoSeleccionado,
        vm::onEvento
    )
}

@Composable
@Preview(showBackground = true)
fun CalculadoraScreen2Preview(
) {
    Contenido(
        navegar = {},
        scaffoldState = rememberScaffoldState(),
        modelo = Pruebas.modeloCompleto,
        x = "12",
        y = "5",
        mostrarPuntos = false,
        puntoSeleccionado = null,
        {}
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Contenido(
    navegar: (String) -> Unit,
    scaffoldState: ScaffoldState,
    modelo: ModeloAjuste,
    x: String,
    y: String,
    mostrarPuntos: Boolean,
    puntoSeleccionado: Punto?,
    onEvento: (EventoCalculadora) -> Unit
) {
    val scrollState = rememberScrollState()
    var focusX by remember { mutableStateOf(false) }
    var focusY by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                titulo = modelo.nombre.ifBlank { "Modelo nuevo" },
                navegar = navegar,
                mostrarConfiguraciones = true,
                mostrarEditar = true
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = scrollState),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (modelo.puntos.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            horizontal = 32.dp,
                            vertical = 16.dp,
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (!(focusX || focusY)) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_mathematics_bro),
                            contentDescription = "Agregá puntos",
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.FillWidth
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Agrega un punto",
                        style = Titulo2,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Agrega puntos a tu modelo para comenzar a ajustar la función.",
                        style = Texto,
                        textAlign = TextAlign.Center
                    )


                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        TextField(
                            value = x,
                            onValueChange = {
                                onEvento(EventoCalculadora.CambioX(it))
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Azul1,
                                backgroundColor = Azul3,
                                unfocusedBorderColor = Azul1,
                                cursorColor = Azul1,
                                focusedBorderColor = Azul1
                            ),
                            placeholder = {
                                Text(
                                    text = modelo.ejeX,
                                    textAlign = TextAlign.Center,
                                    color = Azul1,
                                    style = TextoOscuro
                                )
                            },
                            textStyle = TextoOscuro.copy(textAlign = TextAlign.Center),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .onFocusChanged {
                                    focusX = it.isFocused
                                }
                        )

                        Spacer(modifier = Modifier.width(20.dp))

                        TextField(
                            value = y,
                            onValueChange = {
                                onEvento(EventoCalculadora.CambioY(it))
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Azul1,
                                backgroundColor = Azul3,
                                unfocusedBorderColor = Azul1,
                                cursorColor = Azul1,
                                focusedBorderColor = Azul1
                            ),
                            placeholder = {
                                Text(
                                    text = modelo.ejeY,
                                    textAlign = TextAlign.Center,
                                    color = Azul1,
                                    style = TextoOscuro
                                )
                            },
                            textStyle = TextoOscuro.copy(textAlign = TextAlign.Center),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .onFocusChanged {
                                    focusY = it.isFocused
                                }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Naranja1),
                        onClick = {
                            onEvento(EventoCalculadora.AgregarEditarPunto)
                        },
                        enabled = x.isNotBlank() && y.isNotBlank()
                    ) {
                        Text(
                            text = "Agregar",
                            style = Boton,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Puntos
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .padding(top = 24.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1F),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {

                                    TextField(
                                        value = x,
                                        onValueChange = {
                                            onEvento(EventoCalculadora.CambioX(it))
                                        },
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            textColor = Azul1,
                                            backgroundColor = Azul3,
                                            unfocusedBorderColor = Azul1,
                                            cursorColor = Azul1,
                                            focusedBorderColor = Azul1
                                        ),
                                        placeholder = {
                                            Text(
                                                text = modelo.ejeX,
                                                textAlign = TextAlign.Center,
                                                color = Azul1,
                                                style = TextoOscuro
                                            )
                                        },
                                        textStyle = TextoOscuro.copy(textAlign = TextAlign.Center),
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Number
                                        ),
                                        modifier = Modifier.weight(1f)
                                    )

                                    Spacer(modifier = Modifier.width(20.dp))

                                    TextField(
                                        value = y,
                                        onValueChange = {
                                            onEvento(EventoCalculadora.CambioY(it))
                                        },
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            textColor = Azul1,
                                            backgroundColor = Azul3,
                                            unfocusedBorderColor = Azul1,
                                            cursorColor = Azul1,
                                            focusedBorderColor = Azul1
                                        ),
                                        placeholder = {
                                            Text(
                                                text = modelo.ejeY,
                                                textAlign = TextAlign.Center,
                                                color = Azul1,
                                                style = TextoOscuro
                                            )
                                        },
                                        textStyle = TextoOscuro.copy(textAlign = TextAlign.Center),
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Number
                                        ),
                                        modifier = Modifier.weight(1f)
                                    )
                                    Spacer(modifier = Modifier.width(20.dp))
                                }

                                if (puntoSeleccionado == null) {
                                    IconButton(
                                        onClick = {
                                            onEvento(EventoCalculadora.AgregarEditarPunto)
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
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        IconButton(
                                            onClick = {
                                                onEvento(EventoCalculadora.AgregarEditarPunto)
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
                                        Row {
                                            IconButton(
                                                onClick = {
                                                    onEvento(
                                                        EventoCalculadora.CambioPuntoSeleccionado(
                                                            null
                                                        )
                                                    )
                                                },
                                                modifier = Modifier
                                                    .size(40.dp)
                                                    .clip(CircleShape)
                                                    .background(AmarilloOscuro)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Clear,
                                                    contentDescription = "Cancelar editar puntos",
                                                    tint = Color.Black
                                                )
                                            }
                                            Spacer(modifier = Modifier.width(8.dp))

                                            IconButton(
                                                onClick = {
                                                    onEvento(EventoCalculadora.EliminarPunto)
                                                },
                                                modifier = Modifier
                                                    .size(40.dp)
                                                    .clip(CircleShape)
                                                    .background(NaranjaOscuro)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Delete,
                                                    contentDescription = "Borrar punto",
                                                    tint = Color.White
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {

                                Card(
                                    modifier = Modifier.fillMaxSize(),
                                    elevation = 6.dp,
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        if (mostrarPuntos || modelo.puntos.size < 4) {
                                            FlowRow(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .weight(1F)
                                            ) {

                                                modelo.puntos.forEach { punto ->
                                                    TextButton(
                                                        onClick = {
                                                            onEvento(
                                                                EventoCalculadora.CambioPuntoSeleccionado(
                                                                    punto
                                                                )
                                                            )
                                                        },
                                                        colors = ButtonDefaults.textButtonColors(
                                                            contentColor = Azul2
                                                        )
                                                    ) {

                                                        Text(
                                                            text = punto.toString(),
                                                            style = TextoChico
                                                                .copy(textAlign = TextAlign.Center),
                                                            color = Color.Black
                                                        )
                                                    }
                                                }
                                            }
                                        } else {
                                            Text(
                                                text = modelo.puntosString(),
                                                style = TextoChico,
                                                overflow = TextOverflow.Clip,
                                                maxLines = 1,
                                                modifier = Modifier.weight(1f)
                                            )
                                        }

                                        Column(
                                            verticalArrangement = Arrangement.SpaceAround,
                                            modifier = Modifier.fillMaxHeight()
                                        ) {
                                            IconButton(
                                                onClick = {
                                                    onEvento(EventoCalculadora.CambioMostrarPuntos)
                                                }
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Outlined.ArrowDropDown,
                                                    contentDescription = "Expandir puntos"
                                                )
                                            }

                                            if (mostrarPuntos) {
                                                IconButton(
                                                    onClick = {
                                                        onEvento(
                                                            EventoCalculadora.EliminarPuntos(
                                                                false
                                                            )
                                                        )
                                                    }
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Outlined.Delete,
                                                        contentDescription = "Borrar puntos"
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Resultado
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(topStart = 20.dp, bottomEnd = 20.dp),
                        elevation = 6.dp,
                        backgroundColor = Naranja1
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = modelo.funcion.getFormula(),
                                style = Titulo2,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Error: ${modelo.getError()}",
                                style = Boton,
                                color = Color.White
                            )
                        }
                    }

                    // Gráfica
                    Spacer(modifier = Modifier.height(16.dp))

                    Card(
                        modifier = Modifier
                            .width(230.dp)
                            .height(230.dp)
                            .clickable {
                                navegar(Destino.Formas.ruta)
                            },
                        elevation = 6.dp,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Grafica(
                                modelo = modelo,
                                modifier = Modifier
                                    .requiredWidth(240.dp)
                                    .height(230.dp)
                            )
                        }

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            Surface(
                                shape = RoundedCornerShape(
                                    bottomStart = 10.dp
                                ),
                                color = Azul2,
                                modifier = Modifier
                                    .width(40.dp)
                                    .height(40.dp)
                            ) {
                                Image(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.icono_lineal),
                                    contentDescription = "Forma de la función",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(8.dp),
                                    contentScale = ContentScale.FillBounds
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

