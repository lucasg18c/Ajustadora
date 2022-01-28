package com.slavik.aproximadorfunciones.mmc.presentacion.screens.editar_modelo_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.slavik.aproximadorfunciones.mmc.presentacion.componentes.TopBar
import com.slavik.aproximadorfunciones.mmc.presentacion.theme.Celeste

@Composable
fun EditarModeloScreen(
    volver: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    EditarModeloScreenContenido(
        volver = volver,
        scaffoldState = scaffoldState
    )
}

@Composable
private fun EditarModeloScreenContenido(
    volver: () -> Unit,
    scaffoldState: ScaffoldState
) {

    var nombre by remember { mutableStateOf("") }
    var ejeX by remember { mutableStateOf("") }
    var ejeY by remember { mutableStateOf("") }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                titulo = "Editar modelo",
                navegar = {},
                mostrarConfiguraciones = false
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 48.dp),
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = {
                    Text(text = "Nombre")
                },
                keyboardOptions = KeyboardOptions(KeyboardCapitalization.Sentences)
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = ejeX,
                onValueChange = { ejeX = it },
                label = {
                    Text(text = "Eje X")
                },
                keyboardOptions = KeyboardOptions(KeyboardCapitalization.Sentences)
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = ejeY,
                onValueChange = { ejeY = it },
                label = {
                    Text(text = "Eje Y")
                },
                keyboardOptions = KeyboardOptions(KeyboardCapitalization.Sentences)
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 20.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/
                        volver()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Celeste),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Text(
                        text = "Guardar 💾",
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 8.dp),
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}