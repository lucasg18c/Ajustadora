package com.slavik.aproximadorfunciones.legacy.interfaz_legacy.fragments;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.slavik.aproximadorfunciones.R;
import com.slavik.aproximadorfunciones.legacy.modelo_legacy.DatosGlobales;
import com.slavik.aproximadorfunciones.legacy.modelo_legacy.Punto;

public class PuntosFragment extends BaseFragment {

    LinearLayout tablaPuntos;
    int filas, maxFilas, startFilas;

    @Override
    protected int getLayoutResource()
    {
        return R.layout.fragment_puntos;
    }

    @Override
    protected void initFragment(View v)
    {
        tablaPuntos = v.findViewById(R.id.tablaPuntos);
        v.findViewById(R.id.btnAddFila).setOnClickListener(view -> addFila());

        maxFilas = 50;
        filas = 0;
        startFilas = 5;

        if (DatosGlobales.puntos == null)
            for (int i = 0; i < startFilas; i++)
                addFila();
        else {
            cargarPuntos();
        }

        v.findViewById(R.id.btnCalcular).setOnClickListener(view -> {

            try{
                DatosGlobales.puntos = getPuntos();
                navigate(R.id.action_puntosFragment_to_formaFuncionFragment);
            }
            catch (Exception e){
                toast(e.getMessage());
            }
        });
    }

    private void cargarPuntos() {
        for (Punto p : DatosGlobales.puntos){
            if (p.visible) addFila(p);
        }

        if (DatosGlobales.puntos.length < startFilas){
            for (int i = 0; i < startFilas - DatosGlobales.puntos.length; i++){
                addFila();
            }
        }
    }

    private Punto[] getPuntos() {
        int n = tablaPuntos.getChildCount();
        int last = -1;
        Punto[] puntos = new Punto[n];

        for (int i = 0; i < n; i++){
            View fila = tablaPuntos.getChildAt(i);
            String x = ((TextView)fila.findViewById(R.id.txtPuntoX)).getText().toString();
            String y = ((TextView)fila.findViewById(R.id.txtPuntoY)).getText().toString();

            if (x.length() == 0 || y.length() == 0) continue;

            Punto p = new Punto(
                    Float.parseFloat(x),
                    Float.parseFloat(y)
            );
            puntos[++last] = p;
        }

        if (++last == n) return puntos;

        if (last == 0) throw new RuntimeException(getText(R.string.error_sin_puntos).toString());

        Punto[] puntosTrim = new Punto[last];
        System.arraycopy(puntos, 0, puntosTrim, 0, last);

        return puntosTrim;
    }

    private void addFila(){
        addFila(null);
    }

    private void addFila(@Nullable Punto punto){
        if (filas == maxFilas) {
            toast(R.string.error_max_puntos);
            return;
        }
        filas++;

        @SuppressLint("InflateParams") final View fila = getLayoutInflater()
                .inflate(R.layout.fila_tabla_puntos, null, false);

        fila.findViewById(R.id.btnBorrarPunto).setOnClickListener(view -> removeFila(fila));

        if (punto != null){
            ((TextView)fila.findViewById(R.id.txtPuntoX)).setText( String.valueOf(punto.x));
            ((TextView)fila.findViewById(R.id.txtPuntoY)).setText( String.valueOf(punto.y));
        }
        tablaPuntos.addView(fila);
    }

    private void removeFila(View fila) {
        if (filas == 2){
            toast(R.string.error_min_puntos);
            return;
        }
        tablaPuntos.removeView(fila);
        filas--;
    }
}