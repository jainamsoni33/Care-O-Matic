package com.example.caro_matic.caro_matic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class PredictionActivity extends AppCompatActivity {

    private RecyclerView resultsRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    ArrayList<String> keys, valuePairs;
    ArrayList<String> diseases = new ArrayList<>();
    ArrayList<String> percentages = new ArrayList<>();
    private ArrayList<Disease> diseaseList;
    private ResultsAdapter resultsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);

        resultsRecyclerView = findViewById(R.id.results_list);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        resultsRecyclerView.setLayoutManager(linearLayoutManager);
        diseaseList = new ArrayList<>();

//        Intent j = getIntent();
//        keys = j.getStringArrayListExtra("keys");
//        valuePairs = j.getStringArrayListExtra("valuePairs");

//        String[] dname = new String[keys.size()];
//        double[] percent = new double[keys.size()];

        String[] dname = {"Influenza", "Viral Cough", "Sore Throat"};
        double[] percent = {24, 56, 20};

//        for(int i=0;i<keys.size();i++){
//            dname[i]= keys.get(i);
//            percent[i]=Double.parseDouble(valuePairs.get(i));
//        }
        String maxname;
        double temp;
        int i;

        for (i = 0; i < 3; i++) {
            for (int k = 1; k < (3 - i); k++) {
                if (percent[k - 1] < percent[k]) {
                    //swap elements
                    temp = percent[k - 1];
                    maxname = dname[k - 1];
                    percent[k - 1] = percent[k];
                    dname[k - 1] = dname[k];
                    percent[k] = temp;
                    dname[k] = maxname;
                }
            }
        }

        for (i = 0; i < 3; i++) {
            diseaseList.add(new Disease(dname[i], Double.toString(Math.round(percent[i] * 100.0) / 100.0) + "%"));
        }

        resultsAdapter = new ResultsAdapter(diseaseList);
        resultsRecyclerView.setAdapter(resultsAdapter);
    }
}
