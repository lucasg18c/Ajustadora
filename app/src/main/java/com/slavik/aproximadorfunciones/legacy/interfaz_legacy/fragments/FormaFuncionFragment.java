package com.slavik.aproximadorfunciones.legacy.interfaz_legacy.fragments;

import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.slavik.aproximadorfunciones.R;
import com.slavik.aproximadorfunciones.legacy.modelo_legacy.DatosGlobales;

public class FormaFuncionFragment extends BaseFragment {

    RewardedAd rewardedAd;
    //RewardedAdCallback addCallback;
    RewardedAdLoadCallback adLoadCallback;
    AlertDialog dialog;
    boolean showAd = false, loadError = false, adVisto = false;

    @Override
    protected int getLayoutResource()
    {
        return R.layout.fragment_forma_funcion;
    }

    @Override
    public int getAdViewId()
    {
        return R.id.adViewFormaFuncion;
    }

    @Override
    protected void initFragment(View v)
    {
        iniciarBotones(v);
        crearDialogo();
        crearRewarded();
    }

    private void iniciarBotones(View v) {
        v.findViewById(R.id.cardLineal).setOnClickListener(new Click());
        v.findViewById(R.id.cardParabola).setOnClickListener(new Click());
        v.findViewById(R.id.cardExponencial).setOnClickListener(new Click());
        v.findViewById(R.id.cardLogaritmica).setOnClickListener(new Click());
        v.findViewById(R.id.cardAutomatica).setOnClickListener(new Click());
    }

    private void precargarAnuncio() {
        //rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
    }

    private void crearRewarded() {
//        rewardedAd = new RewardedAd(getContext(), getText(R.string.id_rewarded_auto).toString());
//
//        adLoadCallback = new RewardedAdLoadCallback() {
//            @Override
//            public void onRewardedAdLoaded() {
//                if (showAd)
//                    dialog.show();
//                showAd = false;
//                loadError = false;
//            }
//            @Override
//            public void onRewardedAdFailedToLoad(LoadAdError adError) {
//                loadError = true;
//                System.err.println("ANUNCIO: " + adError.toString());
//            }
//        };

//        addCallback = new RewardedAdCallback() {
//            @Override
//            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
//                adVisto = true;
//            }
//
//            @Override
//            public void onRewardedAdClosed() {
//                if (adVisto) {
//                    adVisto = false;
//                    mostrarResultado("automatic");
//                }
//                crearRewarded();
//            }
//        };
//        precargarAnuncio();
    }

    private void mostrarResultado(String tag) {
        DatosGlobales.setForma(tag);
        navigate(R.id.action_formaFuncionFragment_to_resultadoFragment);
    }

    private void crearDialogo() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setTitle( getText(R.string.ver_anuncio));
//        builder.setMessage(getText(R.string.opcion_requiere_anuncio));
//        builder.setPositiveButton(getText(R.string.aceptar), (dialogInterface, i) -> rewardedAd.show(getActivity(), addCallback));
//        builder.setNegativeButton(getText(R.string.cancelar), null);
//        dialog = builder.create();
    }

    private void mostrarAd() {

//        if (rewardedAd.isLoaded()) dialog.show();
//        else if (loadError){
//            mostrarResultado("automatic");
//            precargarAnuncio();
////            toast(R.string.error_anuncio);
//        }
//        else{
//            toast(R.string.cargando_anuncio);
//            showAd = true;
//        }
    }

    private class Click implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            String tag = view.getTag().toString();
            if (tag.equals("automatic")) mostrarAd();
            else mostrarResultado(tag);
        }
    }
}