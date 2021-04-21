package com.example.covidapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int stateToUse;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DataFragment() {
        // Required empty public constructor
    }

    public DataFragment(int position) {

        stateToUse = position;

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataFragment newInstance(String param1, String param2) {
        DataFragment fragment = new DataFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_data, container, false);


        TextView title = root.findViewById(R.id.title2);
        title.setText(DataFetcher.listByState.get(stateToUse).State);

        TextView confirmed = root.findViewById(R.id.confirmedView);
        confirmed.setText("Confirmed Cases: " + DataFetcher.listByState.get(stateToUse).Confirmed);

        TextView deaths = root.findViewById(R.id.DeathView);
        deaths.setText("Number of Deaths: " + DataFetcher.listByState.get(stateToUse).Deaths);

        TextView fips = root.findViewById(R.id.fipsView);
        fips.setText("FIPS: " + DataFetcher.listByState.get(stateToUse).FIPS);

        TextView incident = root.findViewById(R.id.incidentRateView);
        incident.setText("Incident Rate: " + DataFetcher.listByState.get(stateToUse).IncidentRate);

        TextView total = root.findViewById(R.id.totalView);
        total.setText("Total Test Results: " + DataFetcher.listByState.get(stateToUse).TotalTestResults);

        TextView fatality = root.findViewById(R.id.fatalityView);
        fatality.setText("Testing Rate: " + DataFetcher.listByState.get(stateToUse).CaseFatalityRate);

        Button backButton = root.findViewById(R.id.button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HelpFragment homeFragment = new HelpFragment();
                FragmentTransaction transaction  = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, homeFragment);
                transaction.commit();

            }
        });

        return root;



    }
}