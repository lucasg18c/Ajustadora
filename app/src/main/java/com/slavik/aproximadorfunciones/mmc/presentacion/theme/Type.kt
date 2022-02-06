package com.slavik.aproximadorfunciones.mmc.presentacion.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.slavik.aproximadorfunciones.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val poppins = FontFamily(
    Font(R.font.poppins)
)

val robotoCondensed = FontFamily(
    Font(R.font.roboto_condensed)
)

val Titulo1 = TextStyle(
    fontFamily = poppins,
    fontSize = 35.sp
)
val Titulo1Negrita = TextStyle(
    fontFamily = poppins,
    fontSize = 35.sp,
    fontWeight = FontWeight.SemiBold
)

val Titulo2 = TextStyle(
    fontFamily = poppins,
    fontSize = 24.sp
)

val Titulo2Negrita = TextStyle(
    fontFamily = poppins,
    fontSize = 24.sp,
    fontWeight = FontWeight.SemiBold
)

val TextoClaro = TextStyle(
    fontFamily = poppins,
    fontSize = 18.sp,
    fontWeight = FontWeight.Thin
)

val Texto = TextStyle(
    fontFamily = poppins,
    fontSize = 18.sp,
    fontWeight = FontWeight.ExtraLight
)

val TextoOscuro = TextStyle(
    fontFamily = poppins,
    fontSize = 18.sp,
    fontWeight = FontWeight.SemiBold
)

val TextoChico = TextStyle(
    fontFamily = poppins,
    fontSize = 14.sp,
    fontWeight = FontWeight.Light
)

val Boton = TextStyle(
    fontFamily = robotoCondensed,
    fontSize = 18.sp
)