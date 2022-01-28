package com.slavik.aproximadorfunciones.mmc.presentacion.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.PointsGraphSeries
import com.slavik.aproximadorfunciones.mmc.modelo.Funcion
import com.slavik.aproximadorfunciones.mmc.modelo.ModeloAjuste
import com.slavik.aproximadorfunciones.mmc.modelo.Punto
import com.slavik.aproximadorfunciones.mmc.presentacion.theme.Celeste
import com.slavik.aproximadorfunciones.mmc.presentacion.theme.NaranjaClaro
import java.util.*
import kotlin.math.ceil

@Composable
fun Grafica(
    modifier: Modifier = Modifier,
    modelo: ModeloAjuste
) {

    AndroidView(
        factory = { context ->
            GraphView(context)
        },
        update = { view ->
            view.apply {
                addSeries(
                    PointsGraphSeries<DataPoint>().apply {
                        modelo.puntos
                            .sortedBy { it.x }
                            .forEach { punto ->
                                appendData(
                                    DataPoint(
                                        punto.x.toDouble(),
                                        punto.y.toDouble()
                                    ),
                                    false,
                                    150
                                )
                            }

                        shape = PointsGraphSeries.Shape.POINT
                        size = 10f
                        color = Celeste.toArgb()

                    }
                )

                var minX = modelo.puntos.minOf { it.x }.toInt()
                var maxX = modelo.puntos.maxOf { it.x }.toInt()
                val margen = ceil((maxX - minX) * .2).toInt()
                minX -= margen
                maxX += margen
                val paso = (maxX - minX) / 150F
                var x = minX.toDouble()

                var minY : Double? = null
                var maxY : Double? = null

                addSeries(
                    LineGraphSeries<DataPoint>().apply {
                        repeat(150) {
                            appendData(
                                DataPoint(
                                    x,
                                    modelo.funcion.valuar(x).also {
                                        if (minY == null) {
                                            minY = it
                                            maxY = it
                                            return@also
                                        }

                                        if (it < minY!!) minY = it
                                        if (it > maxY!!) maxY = it
                                    }
                                ),
                                false,
                                150
                            )
                            x += paso
                        }

                        color = NaranjaClaro.toArgb()

                    }
                )

                viewport.apply {
                    isXAxisBoundsManual = true
                    isYAxisBoundsManual = true
                    isScalable = false
                    setScalableY(false)
                    isScrollable = false
                    setScrollableY(false)

                    setMaxX(maxX.toDouble())
                    setMinX(minX.toDouble())

                    val margenY = ((maxY!! - minY!!) * 0.2F).toInt()

                    setMaxY(maxY!! + margenY)
                    setMinY(minY!! - margenY)

                }

            }
        },
        modifier = modifier
    )

}

@Composable
@Preview(showBackground = true)
fun GraficaPreview() {

    Column(modifier = Modifier.fillMaxSize()) {
        Grafica(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),

            modelo = ModeloAjuste(
                puntos = listOf(
                    Punto(1f, 2f),
                    Punto(2f, 3f),
                    Punto(3f, 4f),
                ),
                funcion = Funcion(),
                Calendar.getInstance()
            )
        )
    }
}