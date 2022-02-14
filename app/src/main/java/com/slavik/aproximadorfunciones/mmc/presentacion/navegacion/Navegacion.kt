package com.slavik.aproximadorfunciones.mmc.presentacion.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.slavik.aproximadorfunciones.mmc.presentacion.screens.ConfiguracionScreen
import com.slavik.aproximadorfunciones.mmc.presentacion.screens.calculadora_screen.CalculadoraScreen2
import com.slavik.aproximadorfunciones.mmc.presentacion.screens.editar_modelo_screen.EditarModeloScreen
import com.slavik.aproximadorfunciones.mmc.presentacion.screens.calculadora_screen.FormasScreen
import com.slavik.aproximadorfunciones.mmc.presentacion.screens.inicio_screen.InicioScreen
import com.slavik.aproximadorfunciones.mmc.presentacion.screens.resultado_screen.ResultadoScreen

@Composable
fun Navegacion(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Destino.Inicio.ruta
    ) {

        composable(
            route = Destino.Inicio.ruta
        ) {
            InicioScreen(
                navegar = {destino ->
                    navController.navigate(destino)
                }
            )
        }

        composable(
            route = Destino.Calculadora.ruta
        ) {
            CalculadoraScreen2(
                navegar = { destino ->
                    navController.navigate(destino)
                }
            )
        }

        composable(
            route = Destino.EditarModelo.ruta
        ) {
            EditarModeloScreen(
                volver = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Destino.Resultado.ruta
        ) {
            ResultadoScreen(
                navegar = {destino ->
                    navController.navigate(destino)
                }
            )
        }

        composable(
            route = Destino.Formas.ruta
        ) {
            FormasScreen(
                volver = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Destino.Configuracion.ruta
        ) {
            ConfiguracionScreen(

            )
        }
    }
}