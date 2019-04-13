package com.example.caro_matic.caro_matic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tylersuehr.chips.ChipsInputLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SymptomsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        Button SubmitButton = findViewById(R.id.SubmitButton);
        final ChipsInputLayout chipsInput = (ChipsInputLayout)findViewById(R.id.chips_input);
        List<SymptomsChip> chips = getChips();

        chipsInput.setFilterableChipList(chips);

        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TAG", chipsInput.getSelectedChips().toString());
            }
        });
    }

    public List<SymptomsChip> getChips (){
        SymptomsChip Symptom1 = new SymptomsChip("Cough");
        SymptomsChip Symptom2 = new SymptomsChip("Fever");
        SymptomsChip Symptom3 = new SymptomsChip("Sore Throat");

        ArrayList<SymptomsChip> SymptomsList = new ArrayList<SymptomsChip>();
        SymptomsList.add(Symptom1);
        SymptomsList.add(Symptom2);
        SymptomsList.add(Symptom3);

        return SymptomsList;
    }
}
