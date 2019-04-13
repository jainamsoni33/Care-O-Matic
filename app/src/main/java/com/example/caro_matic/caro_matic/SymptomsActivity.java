package com.example.caro_matic.caro_matic;

import android.os.Bundle;
import android.support.design.chip.Chip;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import com.nex3z.flowlayout.FlowLayout;
import com.tylersuehr.chips.ChipDataSource;
import com.tylersuehr.chips.ChipsInputLayout;

import java.util.ArrayList;
import java.util.List;

public class SymptomsActivity extends AppCompatActivity implements ChipDataSource.SelectionObserver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        Button SubmitButton = findViewById(R.id.SubmitButton);
        Chip coughChip, feverChip;
        FlowLayout symptomsList;
        final ChipsInputLayout chipsInput = (ChipsInputLayout)findViewById(R.id.chips_input);
        coughChip = findViewById(R.id.coughChip);
        feverChip = findViewById(R.id.feverChip);
        symptomsList = findViewById(R.id.ShowSymptoms);

        List<SymptomsChip> chips = getChips();

        chipsInput.setFilterableChipList(chips);
        chipsInput.addSelectionObserver(this);

        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TAG", chipsInput.getSelectedChips().toString());
            }
        });

        CompoundButton.OnCheckedChangeListener filterChipListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
//                    Chip chip;
//                    chip = new Chip();

                }
                else{
                }
            }
        };

        coughChip.setOnCheckedChangeListener(filterChipListener);

    }

    public List<SymptomsChip> getChips (){
        SymptomsChip Symptom1 = new SymptomsChip("skin_rash");
        SymptomsChip Symptom2 = new SymptomsChip("itching");
        SymptomsChip Symptom3 = new SymptomsChip("chills");
        SymptomsChip Symptom4 = new SymptomsChip("joint_pain");
        SymptomsChip Symptom5 = new SymptomsChip("acidity");
        SymptomsChip Symptom6 = new SymptomsChip("vomiting");
        SymptomsChip Symptom7 = new SymptomsChip("burning_micturition");
        SymptomsChip Symptom8 = new SymptomsChip("fatigue");
        SymptomsChip Symptom9 = new SymptomsChip("mood_swings");
        SymptomsChip Symptom10 = new SymptomsChip("weight_loss");
        SymptomsChip Symptom11 = new SymptomsChip("restlessness");
        SymptomsChip Symptom12 = new SymptomsChip("lethargy");
        SymptomsChip Symptom13 = new SymptomsChip("cough");
        SymptomsChip Symptom14 = new SymptomsChip("high_fever");
        SymptomsChip Symptom15 = new SymptomsChip("breathlessness");
        SymptomsChip Symptom16 = new SymptomsChip("sweating");
        SymptomsChip Symptom17 = new SymptomsChip("indigestion");
        SymptomsChip Symptom18 = new SymptomsChip("headache");
        SymptomsChip Symptom19 = new SymptomsChip("nausea");
        SymptomsChip Symptom20 = new SymptomsChip("loss_of_appetite");
        SymptomsChip Symptom21 = new SymptomsChip("pain_behind_the_eyes");
        SymptomsChip Symptom22 = new SymptomsChip("back_pain");
        SymptomsChip Symptom23 = new SymptomsChip("abdominal_pain");
        SymptomsChip Symptom24 = new SymptomsChip("diarrhoea");
        SymptomsChip Symptom25 = new SymptomsChip("mild_fever");
        SymptomsChip Symptom26 = new SymptomsChip("yellowing_of_eyes");
        SymptomsChip Symptom27 = new SymptomsChip("swelled_lymph_nodes");
        SymptomsChip Symptom28 = new SymptomsChip("malaise");
        SymptomsChip Symptom29 = new SymptomsChip("blurred_and_distorted_vision");
        SymptomsChip Symptom30 = new SymptomsChip("phlegm");
        SymptomsChip Symptom31 = new SymptomsChip("chest_pain");
        SymptomsChip Symptom32 = new SymptomsChip("fast_heart_rate");
        SymptomsChip Symptom33 = new SymptomsChip("dizziness");
        SymptomsChip Symptom34 = new SymptomsChip("excessive_hunger");
        SymptomsChip Symptom35 = new SymptomsChip("muscle_weakness");
        SymptomsChip Symptom36 = new SymptomsChip("stiff_neck");
        SymptomsChip Symptom37 = new SymptomsChip("loss_of_balance");
        SymptomsChip Symptom38 = new SymptomsChip("bladder_discomfort");
        SymptomsChip Symptom39 = new SymptomsChip("foul_smell_of urine");
        SymptomsChip Symptom40 = new SymptomsChip("continuous_feel_of_urine");
        SymptomsChip Symptom41 = new SymptomsChip("passage_of_gases");
        SymptomsChip Symptom42 = new SymptomsChip("internal_itching");
        SymptomsChip Symptom43 = new SymptomsChip("depression");
        SymptomsChip Symptom44 = new SymptomsChip("irritability");
        SymptomsChip Symptom45 = new SymptomsChip("muscle_pain");
        SymptomsChip Symptom46 = new SymptomsChip("red_spots_over_body");
        SymptomsChip Symptom47 = new SymptomsChip("abnormal_menstruation");
        SymptomsChip Symptom48 = new SymptomsChip("family_history");
        SymptomsChip Symptom49 = new SymptomsChip("mucoid_sputum");
        SymptomsChip Symptom50 = new SymptomsChip("lack_of_concentration");
        SymptomsChip Symptom51 = new SymptomsChip("visual_disturbances");
        SymptomsChip Symptom52 = new SymptomsChip("blood_in_sputum");
        SymptomsChip Symptom53 = new SymptomsChip("blister");
        SymptomsChip Symptom54 = new SymptomsChip("red_sore_around_nose");
        SymptomsChip Symptom55 = new SymptomsChip("yellow_crust_ooze");

        ArrayList<SymptomsChip> SymptomsList = new ArrayList<SymptomsChip>();
        SymptomsList.add(Symptom1);
        SymptomsList.add(Symptom2);
        SymptomsList.add(Symptom3);
        SymptomsList.add(Symptom4);
        SymptomsList.add(Symptom5);
        SymptomsList.add(Symptom6);
        SymptomsList.add(Symptom7);
        SymptomsList.add(Symptom8);
        SymptomsList.add(Symptom9);
        SymptomsList.add(Symptom10);
        SymptomsList.add(Symptom11);
        SymptomsList.add(Symptom12);
        SymptomsList.add(Symptom13);
        SymptomsList.add(Symptom14);
        SymptomsList.add(Symptom15);
        SymptomsList.add(Symptom16);
        SymptomsList.add(Symptom17);
        SymptomsList.add(Symptom18);
        SymptomsList.add(Symptom19);
        SymptomsList.add(Symptom20);
        SymptomsList.add(Symptom21);
        SymptomsList.add(Symptom22);
        SymptomsList.add(Symptom23);
        SymptomsList.add(Symptom24);
        SymptomsList.add(Symptom25);
        SymptomsList.add(Symptom26);
        SymptomsList.add(Symptom27);
        SymptomsList.add(Symptom28);
        SymptomsList.add(Symptom29);
        SymptomsList.add(Symptom30);
        SymptomsList.add(Symptom31);
        SymptomsList.add(Symptom32);
        SymptomsList.add(Symptom33);
        SymptomsList.add(Symptom34);
        SymptomsList.add(Symptom35);
        SymptomsList.add(Symptom36);
        SymptomsList.add(Symptom37);
        SymptomsList.add(Symptom38);
        SymptomsList.add(Symptom39);
        SymptomsList.add(Symptom40);
        SymptomsList.add(Symptom41);
        SymptomsList.add(Symptom42);
        SymptomsList.add(Symptom43);
        SymptomsList.add(Symptom44);
        SymptomsList.add(Symptom45);
        SymptomsList.add(Symptom46);
        SymptomsList.add(Symptom47);
        SymptomsList.add(Symptom48);
        SymptomsList.add(Symptom49);
        SymptomsList.add(Symptom50);
        SymptomsList.add(Symptom51);
        SymptomsList.add(Symptom52);
        SymptomsList.add(Symptom53);
        SymptomsList.add(Symptom54);
        SymptomsList.add(Symptom55);

        return SymptomsList;
    }

    @Override
    public void onChipSelected(com.tylersuehr.chips.Chip chip) {

    }

    @Override
    public void onChipDeselected(com.tylersuehr.chips.Chip chip) {

    }
}
