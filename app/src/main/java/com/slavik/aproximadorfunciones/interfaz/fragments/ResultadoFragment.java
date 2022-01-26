package com.slavik.aproximadorfunciones.interfaz.fragments;

import android.graphics.Color;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.slavik.aproximadorfunciones.R;
import com.slavik.aproximadorfunciones.modelo.DatosGlobales;
import com.slavik.aproximadorfunciones.modelo.Punto;
import com.slavik.aproximadorfunciones.modelo.funciones.Funcion;
import com.slavik.aproximadorfunciones.modelo.funciones.Parabola;

import org.sufficientlysecure.htmltextview.HtmlFormatter;
import org.sufficientlysecure.htmltextview.HtmlFormatterBuilder;

import java.text.DecimalFormat;


public class ResultadoFragment extends BaseFragment {

    TextView tvDesviacion, tvResultado;

    @Override
    protected int getLayoutResource()
    {
        return R.layout.fragment_resultado;
    }

    @Override
    protected void initFragment(View v)
    {
        tvDesviacion = v.findViewById(R.id.txvDesviacion);
        tvResultado = v.findViewById(R.id.txvResultado);

        Funcion f = DatosGlobales.funcion;
        f.setPuntos(DatosGlobales.puntos);
        if (f.getClass() == Parabola.class && DatosGlobales.puntos.length == 2) {
            f.addPuntoSoporte();
        }
        f.calcularSolucion();

        setResultado(f);
        setDesviacion(f);

        GraphView grafico = v.findViewById(R.id.graphView);

        grafico.getViewport().setYAxisBoundsManual(true);
        grafico.getViewport().setXAxisBoundsManual(true);

        grafico.getViewport().setScalable(true);
        grafico.getViewport().setScalableY(true);
        grafico.getViewport().setScrollable(true);
        grafico.getViewport().setScrollableY(true);

        PointsGraphSeries<DataPoint> puntos = new PointsGraphSeries<>();
        double mayX = 0, menX = 0, mayY = 0, menY = 0;
        for (Punto p : f.getPuntos()){
            if (p.visible)
                puntos.appendData(new DataPoint(p.x, p.y), false, 150);

            if (p.x < menX) menX = p.x;
            if (p.x > mayX) mayX = p.x;
            if (p.y < menY) menY = p.y;
            if (p.y > mayY) mayY = p.y;
        }

        double dx = (mayX - menX)/2;
        double dy = (mayY - menY)/2;

        grafico.getViewport().setMaxX(mayX + dx);
        grafico.getViewport().setMinX(menX - dx);
        grafico.getViewport().setMaxY(mayY + dy);
        grafico.getViewport().setMinY((menY - dy));

        puntos.setShape(PointsGraphSeries.Shape.POINT);
        puntos.setSize(10);
        puntos.setColor(Color.GREEN);

        grafico.addSeries(f.generarGrafica());
        grafico.addSeries(puntos);
    }

    private void setDesviacion(Funcion f) {
        DecimalFormat df = new DecimalFormat("#.####");
        String text =  getText(R.string.desviacion) + df.format(f.desviacion());
        tvDesviacion.setText(text);
    }

    private void setResultado(Funcion f) {
        Spanned text = HtmlFormatter.formatHtml(new HtmlFormatterBuilder().setHtml(f.toString()));
        tvResultado.setText(text);
    }
}