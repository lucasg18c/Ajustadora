package com.slavik.aproximadorfunciones.mmc.ui.componentes

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.slavik.aproximadorfunciones.mmc.ui.navegacion.Destino

@Composable
fun TopBar(
    modifier : Modifier = Modifier,
    backgroundColor: Color = Color.White,
    contentColor: Color = Color.Black,
    titulo: String,
    mostrarConfiguraciones: Boolean = false,
    mostrarEditar: Boolean = false,
    navegar : (String) -> Unit
) {
    TopAppBar(
        title = {
            Text(text = titulo)
        },
        actions = {
            if (mostrarEditar) {
                IconButton(onClick = {
                    navegar(Destino.EditarModelo.ruta)
                }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar"
                    )
                }
            }

            if (mostrarConfiguraciones) {
                IconButton(onClick = {
                    navegar(Destino.Configuracion.ruta)
                }) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Configuracion"
                    )
                }
            }
        },
        modifier = modifier,
        backgroundColor = backgroundColor,
        contentColor = contentColor
    )
}

@Preview
@Composable
fun TopBaPreview() {
    TopBar(titulo = "Ajustadora", navegar = {})
}