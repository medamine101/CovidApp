package com.example.covidapp;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MapFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    Button edit;
    Button add;
    ListView list;
    String[] testslist = new String[]{};
    final List<String> tests = new ArrayList<String>((Arrays.asList(testslist)));
    int currFocus = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate( R.layout.fragment_map,container, false );
        edit = view.findViewById(R.id.editinfo);
        add = view.findViewById(R.id.addinfo);
        list = view.findViewById(R.id.list);



        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_list_item_1, tests);

        // DataBind ListView with items from ArrayAdapter
        list.setAdapter(arrayAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
                arrayAdapter.notifyDataSetChanged();


            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                for (int i=0;i<tests.size();i++){
                    list.getChildAt(i).setBackgroundColor(Color.WHITE);
                }
                currFocus = myItemInt;
                list.getChildAt(currFocus).setBackgroundColor(Color.GRAY);
            }
                                }

        );

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = tests.get(currFocus);
                if (val.charAt(8) =='W') {
                    val = "Status: Negative" + val.substring(15);
                } else if (val.charAt(8) =='N') {
                    val = "Status: Positive" + val.substring(16);
               } else {
                    val = "Status: Waiting" + val.substring(16);
                }
                tests.set(currFocus,val);
                arrayAdapter.notifyDataSetChanged();
        };
        });
        return view;
        }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth){
        String date = month + "/" + dayOfMonth + "/" + year;
        tests.add("Status: Waiting, Date: " + date);

    }

}
