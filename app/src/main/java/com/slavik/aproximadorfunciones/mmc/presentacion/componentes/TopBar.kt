package com.slavik.aproximadorfunciones.mmc.presentacion.componentes

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
import com.slavik.aproximadorfunciones.mmc.presentacion.navegacion.Destino

@Composable
fun TopBar(
    modifier : Modifier = Modifier,
    backgroundColor: Color = Color.White,
    contentColor: Color = Color.Black,
    titulo: String,
    mostrarConfiguraciones: Boolean = true,
    mostrarEditar: Boolean = false,
    navegar : (Destino) -> Unit
) {
    TopAppBar(
        title = {
            Text(text = titulo)
        },
        actions = {
            if (mostrarEditar) {
                IconButton(onClick = {
                    navegar(Destino.EditarModelo)
                }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar"
                    )
                }
            }

            if (mostrarConfiguraciones) {
                IconButton(onClick = {
                    navegar(Destino.Configuracion)
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