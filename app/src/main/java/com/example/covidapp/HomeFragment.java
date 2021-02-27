package com.example.covidapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private TextView stat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        UpdaterForTextView statBoxUpdater = new UpdaterForTextView(root.findViewById(R.id.statbox), getActivity());

        statBoxUpdater.fetchAndUpdateText("https://corona.lmao.ninja/v2/all?yesterday", "cases");

        return root;
    }

}
