package com.example.covidapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    View root;
    ListView listView;
    List list = new ArrayList<>();
    ArrayAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);

        //UpdaterForTextView statBoxUpdater = new UpdaterForTextView(root.findViewById(R.id.statbox), getActivity());

        listView = root.findViewById(R.id.StateList);

        DataFetcher dataFetcher = new DataFetcher();

        dataFetcher.FetchTodayData(getActivity(), HomeFragment.this);

        return root;


    }


    
    public void toResponse(){

        for(int i = 0; i < DataFetcher.listByState.size(); i++){

            list.add(DataFetcher.listByState.get(i).State);

        }

        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(getActivity(),String.valueOf(position),Toast.LENGTH_SHORT).show();

                DataFragment dataFragment = new DataFragment(position);
                FragmentTransaction transaction  = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, dataFragment);
                transaction.commit();
            }
        });




    }
    
}
