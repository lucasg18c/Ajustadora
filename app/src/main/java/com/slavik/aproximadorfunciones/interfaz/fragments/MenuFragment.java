package com.slavik.aproximadorfunciones.interfaz.fragments;

import android.view.View;

import com.slavik.aproximadorfunciones.R;

public class MenuFragment extends BaseFragment {

    @Override
    protected int getLayoutResource()
    {
        return R.layout.fragment_menu;
    }

    @Override
    protected void initFragment(View v)
    {
        v.findViewById(R.id.cardViewTutorial).setOnClickListener(view ->
                navigate(R.id.action_menuFragment_to_tutorialFragment)
        );

        v.findViewById(R.id.cardViewCalcular).setOnClickListener(view ->
                navigate(R.id.action_menuFragment_to_puntosFragment));
    }

    @Override
    public int getAdViewId()
    {
        return R.id.adView;
    }
}