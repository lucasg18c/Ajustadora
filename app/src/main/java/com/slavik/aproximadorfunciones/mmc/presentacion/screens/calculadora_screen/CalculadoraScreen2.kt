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
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.flowlayout.FlowRow
import com.slavik.aproximadorfunciones.R
import com.slavik.aproximadorfunciones.mmc.modelo.ModeloAjuste
import com.slavik.aproximadorfunciones.mmc.modelo.Punto
import com.slavik.aproximadorfunciones.mmc.presentacion.componentes.Grafica
import com.slavik.aproximadorfunciones.mmc.presentacion.componentes.TopBar
import com.slavik.aproximadorfunciones.mmc.presentacion.navegacion.Destino
import com.slavik.aproximadorfunciones.mmc.presentacion.theme.*
import com.slavik.aproximadorfunciones.mmc.util.Pruebas

@Composable
fun CalculadoraScreen2(
    navegar: (Destino) -> Unit,
) {
    val modelo by remember { mutableStateOf(ModeloAjuste()) }
    var x by remember { mutableStateOf("") }
    var y by remember { mutableStateOf("") }
    var mostrarPuntos by remember { mutableStateOf(false) }
    var puntoSeleccionado by remember { mutableStateOf<Punto?>(null) }

    modelo.resolver()

    Contenido(
        navegar = navegar,
        scaffoldState = rememberScaffoldState(),
        modelo = modelo,
        x,
        y,
        mostrarPuntos,
        puntoSeleccionado,
        { mostrarPuntos = it },
        { x = it },
        { y = it },
        { puntoSeleccionado = it },
    )
}

@Composable
@Preview(showBackground = true)
fun CalculadoraScreen2Preview(
) {
    val modelo by remember { mutableStateOf(ModeloAjuste()) }
    var x by remember { mutableStateOf("") }
    var y by remember { mutableStateOf("") }
    var mostrarPuntos by remember { mutableStateOf(false) }
    var puntoSeleccionado by remember { mutableStateOf<Punto?>(null) }

    modelo.resolver()

    Contenido(
        navegar = {},
        scaffoldState = rememberScaffoldState(),
        modelo = modelo,
        x,
        y,
        mostrarPuntos,
        puntoSeleccionado,
        { mostrarPuntos = it },
        { x = it },
        { y = it },
        { puntoSeleccionado = it },
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Contenido(
    navegar: (Destino) -> Unit,
    scaffoldState: ScaffoldState,
    modelo: ModeloAjuste,
    x: String,
    y: String,
    mostrarPuntos: Boolean,
    puntoSeleccionado: Punto?,
    cambioMostrarPuntos: (Boolean) -> Unit,
    cambioX: (String) -> Unit,
    cambioY: (String) -> Unit,
    cambioPuntoSeleccionado: (Punto?) -> Unit,
) {
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                titulo = modelo.nombre,
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
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 32.dp,
                        vertical = 16.dp,
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_mathematics_bro),
                        contentDescription = "Agregá puntos",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )

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
                                cambioX(it)
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
                        )

                        Spacer(modifier = Modifier.width(20.dp))

                        TextField(
                            value = y,
                            onValueChange = {
                                cambioY(it)
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
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Naranja1),
                        onClick = {
                            // todo
                        }
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
                                            cambioX(it)
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
                                            cambioY(it)
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
                                            // todo
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
                                                cambioPuntoSeleccionado(null) //todo
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
                                        Row() {
                                            IconButton(
                                                onClick = {
                                                    cambioPuntoSeleccionado(null) // todo
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
                                                    cambioPuntoSeleccionado(null) // todo
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
                                    Row(modifier = Modifier
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
                                                            cambioPuntoSeleccionado(punto)
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
                                                text = modelo.getPuntos(),
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
                                                    cambioMostrarPuntos(!mostrarPuntos)
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
                                                        // todo
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
                                navegar(Destino.Formas)
                            },
                        elevation = 6.dp,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Grafica(modelo = modelo)
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

