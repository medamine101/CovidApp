package com.example.covidapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);

        UpdaterForTextView statBoxUpdater = new UpdaterForTextView(root.findViewById(R.id.statbox), getActivity());

        DataFetcher dataFetcher = new DataFetcher();

        dataFetcher.FetchTodayData(getActivity());

        return root;
    }

    
    public static void toResponse(){
        TextView textView = root.findViewById(R.id.statbox);

        textView.setText(DataFetcher.listByState.get(0).State);
        
        textView.setVisibility(View.VISIBLE);

    }
    
}
