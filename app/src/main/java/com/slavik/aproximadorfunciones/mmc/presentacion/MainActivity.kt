package com.slavik.aproximadorfunciones.mmc.presentacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.slavik.aproximadorfunciones.R
import com.slavik.aproximadorfunciones.mmc.datos.repositorio.Repositorio
import com.slavik.aproximadorfunciones.mmc.presentacion.navegacion.Navegacion
import com.slavik.aproximadorfunciones.mmc.presentacion.theme.AproximadorFuncionesTheme
import com.slavik.aproximadorfunciones.mmc.presentacion.theme.Naranja1
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    @Inject lateinit var repositorio: Repositorio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AproximadorFuncionesTheme {
                Ajustadora()
            }
        }
    }

    override fun onStop() {
        repositorio.guardar()
        super.onStop()
    }
}

@Composable
private fun Ajustadora(
    vm: MainActivityVM = hiltViewModel()
) {
    val navHostController = rememberNavController()

    if (vm.mostrarSplash) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo_blanco),
                contentDescription = "Ajustadora cargando",
                modifier = Modifier.fillMaxWidth(.4f),
                contentScale = ContentScale.FillWidth,
                colorFilter = ColorFilter.tint(Naranja1)
            )
        }
    } else {
        Navegacion(navController = navHostController)
    }
}
