package com.slavik.aproximadorfunciones.interfaz.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.slavik.aproximadorfunciones.R;

public abstract class BaseFragment extends Fragment
{

    private NavController controller;

    protected abstract int getLayoutResource();
    protected abstract void initFragment(View view);

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        controller = Navigation.findNavController(getActivity(), R.id.nav_host_container);

        View view = inflater.inflate(getLayoutResource(), null);
        initFragment(view);
        initAdView(view);

        return view;
    }

    private void initAdView(View view)
    {
        int resId = getAdViewId();
        if (resId == -1) return;

        AdView mAdView = view.findViewById(resId);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public final void navigate(int action){
        controller.navigate(action);
    }

    public final void toast(String mensaje){
        Toast.makeText(
                getContext(),
                mensaje,
                Toast.LENGTH_LONG).show();
    }

    public final void toast(int resId){
        Toast.makeText(
                getContext(),
                getText(resId).toString(),
                Toast.LENGTH_LONG).show();
    }

    public int getAdViewId()
    {
        return -1;
    }
}
