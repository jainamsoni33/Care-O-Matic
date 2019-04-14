package com.example.caro_matic.caro_matic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.chip.Chip;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.nex3z.flowlayout.FlowLayout;
import com.tylersuehr.chips.ChipDataSource;
import com.tylersuehr.chips.ChipsInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymptomsActivity extends AppCompatActivity implements ChipDataSource.SelectionObserver {

    private static final String TAG = "SymptomsActivity";
    ArrayList<String> FinalSymptoms ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        Button SubmitButton = findViewById(R.id.SubmitButton);
        final Chip lossofappetiteChip,vomitingChip,headacheChip,highfeverChip,fatigueChip;
        FlowLayout symptomsList;
        final ChipsInputLayout chipsInput = (ChipsInputLayout)findViewById(R.id.chips_input);
        lossofappetiteChip = findViewById(R.id.lossofappetiteChip);
        vomitingChip= findViewById(R.id.vomitingChip);
        fatigueChip=findViewById(R.id.fatigueChip);
        highfeverChip=findViewById(R.id.highfeverChip);
        headacheChip=findViewById(R.id.headacheChip);
//        symptomsList = findViewById(R.id.ShowSymptoms);

        List<SymptomsChip> chips = getChips();

        chipsInput.setFilterableChipList(chips);
        chipsInput.addSelectionObserver(this);

        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FinalSymptoms = new ArrayList<>();
                List<SymptomsChip> ChipsSymptoms = (List<SymptomsChip>) chipsInput.getSelectedChips();
                for(SymptomsChip chip : ChipsSymptoms){
                    FinalSymptoms.add(chip.getSubtitle());
                }
                if(vomitingChip.isChecked()){
                    FinalSymptoms.add("vomiting");
                }
                if(fatigueChip.isChecked()){
                    FinalSymptoms.add("fatigue");
                }
                if(highfeverChip.isChecked()){
                    FinalSymptoms.add("high_fever");
                }
                if(lossofappetiteChip.isChecked()){
                    FinalSymptoms.add("loss_of_appetite");
                }
                if(headacheChip.isChecked()){
                    FinalSymptoms.add("headache");
                }
                Log.i("TAG", FinalSymptoms.toString());

                generate_Symptoms();
                //startActivity(new Intent(SymptomsActivity.this, PredictionActivity.class));
            }
        });

//        Chip chip = new Chip(this);
//        chip.setText("Hello");

        CompoundButton.OnCheckedChangeListener filterChipListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                }
                else{
                }
            }
        };

        vomitingChip.setOnCheckedChangeListener(filterChipListener);
        fatigueChip.setOnCheckedChangeListener(filterChipListener);
        highfeverChip.setOnCheckedChangeListener(filterChipListener);
        lossofappetiteChip.setOnCheckedChangeListener(filterChipListener);
        headacheChip.setOnCheckedChangeListener(filterChipListener);
    }
    private void generate_Symptoms() {
        String base_url = "http://192.168.1.102:8000/";
        String url = base_url + "predict_symptoms/";

        Map<String ,ArrayList > params = new HashMap<String, ArrayList>();
        params.put("symptoms",FinalSymptoms);

        Log.e(TAG,"Params = " + params + " url = " + url);

        //startActivity(new Intent(SymptomsActivity.this, PredictionActivity.class));

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        //hideProgressDialog();
                        try {
                            //ArrayList<JSONObject> result = response.getString("result");
                            //String[] arr = result.split(",");

                            //Log.e(TAG,result);

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //hideProgressDialog();

                Log.e(TAG,"Error = " + error);
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public List<SymptomsChip> getChips (){
        SymptomsChip Symptom1 = new SymptomsChip("Skin Rash", "skin_rash");
        SymptomsChip Symptom2 = new SymptomsChip("Itching",  "itching");
        SymptomsChip Symptom3 = new SymptomsChip("Chills",  "chills");
        SymptomsChip Symptom4 = new SymptomsChip("Joint pain",  "joint_pain");
        SymptomsChip Symptom5 = new SymptomsChip("Acidity",  "acidity");
//        SymptomsChip Symptom6 = new SymptomsChip("Vomiting",  "vomiting");
        SymptomsChip Symptom7 = new SymptomsChip("Burning micturition",  "burning_micturition");
//        SymptomsChip Symptom8 = new SymptomsChip("Fatigue",  "fatigue");
        SymptomsChip Symptom9 = new SymptomsChip("Mood swings",  "mood_swings");
        SymptomsChip Symptom10 = new SymptomsChip("Weight loss",  "weight_loss");
        SymptomsChip Symptom11 = new SymptomsChip("Restlessness",  "restlessness");
        SymptomsChip Symptom12 = new SymptomsChip("Lethargy",  "lethargy");
        SymptomsChip Symptom13 = new SymptomsChip("Cough",  "cough");
//        SymptomsChip Symptom14 = new SymptomsChip("High fever",  "high_fever");
        SymptomsChip Symptom15 = new SymptomsChip("Breathlessness",  "breathlessness");
        SymptomsChip Symptom16 = new SymptomsChip("Sweating",  "sweating");
        SymptomsChip Symptom17 = new SymptomsChip("Indigestion",  "indigestion");
//        SymptomsChip Symptom18 = new SymptomsChip("Headache",  "headache");
        SymptomsChip Symptom19 = new SymptomsChip("Nausea",  "nausea");
//        SymptomsChip Symptom20 = new SymptomsChip("Loss of appetite",  "loss_of_appetite");
        SymptomsChip Symptom21 = new SymptomsChip("Pain behind the eyes",  "pain_behind_the_eyes");
        SymptomsChip Symptom22 = new SymptomsChip("Back pain",  "back_pain");
        SymptomsChip Symptom23 = new SymptomsChip("Abdominal pain",  "abdominal_pain");
        SymptomsChip Symptom24 = new SymptomsChip("Diarrhoea",  "diarrhoea");
        SymptomsChip Symptom25 = new SymptomsChip("Mild fever",  "mild_fever");
        SymptomsChip Symptom26 = new SymptomsChip("Yellowing of eyes",  "yellowing_of_eyes");
        SymptomsChip Symptom27 = new SymptomsChip("Swelled lymph nodes",  "swelled_lymph_nodes");
        SymptomsChip Symptom28 = new SymptomsChip("Malaise",  "malaise");
        SymptomsChip Symptom29 = new SymptomsChip("Blurred and distorted vision",  "blurred_and_distorted_vision");
        SymptomsChip Symptom30 = new SymptomsChip("Phlegm",  "phlegm");
        SymptomsChip Symptom31 = new SymptomsChip("Chest pain",  "chest_pain");
        SymptomsChip Symptom32 = new SymptomsChip("Fast heart rate",  "fast_heart_rate");
        SymptomsChip Symptom33 = new SymptomsChip("Dizziness",  "dizziness");
        SymptomsChip Symptom34 = new SymptomsChip("Excessive hunger",  "excessive_hunger");
        SymptomsChip Symptom35 = new SymptomsChip("Muscle weakness",  "muscle_weakness");
        SymptomsChip Symptom36 = new SymptomsChip("Stiff neck",  "stiff_neck");
        SymptomsChip Symptom37 = new SymptomsChip("Loss of balance",  "loss_of_balance");
        SymptomsChip Symptom38 = new SymptomsChip("Bladder discomfort",  "bladder_discomfort");
        SymptomsChip Symptom39 = new SymptomsChip("Foul smell of urine",  "foul_smell_of urine");
        SymptomsChip Symptom40 = new SymptomsChip("Continuous feel of urine",  "continuous_feel_of_urine");
        SymptomsChip Symptom41 = new SymptomsChip("Passage of gases",  "passage_of_gases");
        SymptomsChip Symptom42 = new SymptomsChip("Internal itching",  "internal_itching");
        SymptomsChip Symptom43 = new SymptomsChip("Depression",  "depression");
        SymptomsChip Symptom44 = new SymptomsChip("Irritability",  "irritability");
        SymptomsChip Symptom45 = new SymptomsChip("Muscle pain",  "muscle_pain");
        SymptomsChip Symptom46 = new SymptomsChip("Red spots over body",  "red_spots_over_body");
        SymptomsChip Symptom47 = new SymptomsChip("Abnormal menstruation",  "abnormal_menstruation");
        SymptomsChip Symptom48 = new SymptomsChip("Family history",  "family_history");
        SymptomsChip Symptom49 = new SymptomsChip("Mucoid sputum",  "mucoid_sputum");
        SymptomsChip Symptom50 = new SymptomsChip("Lack of concentration",  "lack_of_concentration");
        SymptomsChip Symptom51 = new SymptomsChip("Visual disturbances",  "visual_disturbances");
        SymptomsChip Symptom52 = new SymptomsChip("Blood in sputum",  "blood_in_sputum");
        SymptomsChip Symptom53 = new SymptomsChip("Blister",  "blister");
        SymptomsChip Symptom54 = new SymptomsChip("Red sore around nose",  "red_sore_around_nose");
        SymptomsChip Symptom55 = new SymptomsChip("Yellow crust ooze",  "yellow_crust_ooze");

        ArrayList<SymptomsChip> SymptomsList = new ArrayList<SymptomsChip>();
        SymptomsList.add(Symptom1);
        SymptomsList.add(Symptom2);
        SymptomsList.add(Symptom3);
        SymptomsList.add(Symptom4);
        SymptomsList.add(Symptom5);
//        SymptomsList.add(Symptom6);
        SymptomsList.add(Symptom7);
//        SymptomsList.add(Symptom8);
        SymptomsList.add(Symptom9);
        SymptomsList.add(Symptom10);
        SymptomsList.add(Symptom11);
        SymptomsList.add(Symptom12);
        SymptomsList.add(Symptom13);
//        SymptomsList.add(Symptom14);
        SymptomsList.add(Symptom15);
        SymptomsList.add(Symptom16);
        SymptomsList.add(Symptom17);
//        SymptomsList.add(Symptom18);
        SymptomsList.add(Symptom19);
//        SymptomsList.add(Symptom20);
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
