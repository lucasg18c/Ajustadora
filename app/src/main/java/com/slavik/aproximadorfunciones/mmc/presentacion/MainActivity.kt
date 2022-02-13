package com.slavik.aproximadorfunciones.mmc.presentacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.slavik.aproximadorfunciones.mmc.presentacion.navegacion.Navegacion
import com.slavik.aproximadorfunciones.mmc.presentacion.theme.AproximadorFuncionesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AproximadorFuncionesTheme {
                navHostController = rememberNavController()
                Navegacion(navController = navHostController)
            }
        }
    }
}