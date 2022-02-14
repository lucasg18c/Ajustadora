package com.slavik.aproximadorfunciones.mmc.ui.screens.editar_modelo_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.slavik.aproximadorfunciones.mmc.ui.componentes.TopBar
import com.slavik.aproximadorfunciones.mmc.ui.theme.Azul1
import com.slavik.aproximadorfunciones.mmc.util.EventoUI
import kotlinx.coroutines.flow.collect

@Composable
fun EditarModeloScreen(
    volver: () -> Unit,
    vm: EditarVM = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        vm.evento.collect {
            if (it is EventoUI.Volver) volver()
        }
    }

    EditarModeloScreenContenido(
        scaffoldState = scaffoldState,
        ejeX = vm.x,
        ejeY = vm.y,
        nombre = vm.nombre,
        onEvento = vm::onEvento
    )
}

@Composable
private fun EditarModeloScreenContenido(
    scaffoldState: ScaffoldState,
    ejeX: String,
    ejeY: String,
    nombre: String,
    onEvento: (EventoEditarModelo) -> Unit
) {
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
                onValueChange = { onEvento(EventoEditarModelo.CambioNombre(it)) },
                label = {
                    Text(text = "Nombre")
                },
                keyboardOptions = KeyboardOptions(KeyboardCapitalization.Sentences)
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = ejeX,
                onValueChange = { onEvento(EventoEditarModelo.CambioX(it)) },
                label = {
                    Text(text = "Eje X")
                },
                keyboardOptions = KeyboardOptions(KeyboardCapitalization.Sentences)
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = ejeY,
                onValueChange = { onEvento(EventoEditarModelo.CambioY(it)) },
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
                    onClick = {
                        onEvento(EventoEditarModelo.Guardar)
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Azul1),
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