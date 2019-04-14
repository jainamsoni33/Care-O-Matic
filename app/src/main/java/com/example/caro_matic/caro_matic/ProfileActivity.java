package com.example.caro_matic.caro_matic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";
    private String height,weight,age,gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final EditText HeightEditText, WeightEditText, AgeEditText, BGEditText;
        Button ContinueButton;
        final RadioButton MaleButton, FemaleButton;
        HeightEditText = findViewById(R.id.HeightEditText);
        WeightEditText = findViewById(R.id.WeightEditText);
        BGEditText = findViewById(R.id.BGEditText);
        AgeEditText = findViewById(R.id.AgeEditText);
        ContinueButton = findViewById(R.id.ContinueButton);
        MaleButton = findViewById(R.id.MaleButton);
        FemaleButton = findViewById(R.id.FemaleButton);

        ContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height = HeightEditText.getText().toString();
                weight = WeightEditText.getText().toString();
                age = AgeEditText.getText().toString();

                if(MaleButton.isChecked())
                    gender = "male";
                else
                    gender = "female";

                AddDetails();

                //startActivity(new Intent(ProfileActivity.this, SymptomsActivity.class));

                //Log.i("TAG", Height + Weight + Age + Male.toString() + Female.toString());
            }
        });
    }
    private void AddDetails() {
        String base_url = "http://192.168.1.102:8000/";
        String url = base_url + "add_details/";

        SharedPreferences sp = getSharedPreferences("Credentials", MODE_PRIVATE);


        Map<String ,String > params = new HashMap<String, String>();
        params.put("height",height);
        params.put("weight",weight);
        params.put("age",age);
        params.put("gender",gender);
        params.put("username",sp.getString("username",null));

        Log.e(TAG,"Params = " + params + " url = " + url);

        //startActivity(new Intent(LoginActivity.this, ProfileActivity.class));

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        //hideProgressDialog();
                        try {
                            String status = response.getString("status");
                            Log.e(TAG,status);
                            if(status.equals("success")) {
                                Toast.makeText(ProfileActivity.this,"Details added successfully", Toast.LENGTH_SHORT).show();
//                                SharedPreferences.Editor editor = getSharedPreferences("Credentials", MODE_PRIVATE).edit();
//                                editor.putString("username",username);
//                                editor.apply();
                                Log.e(TAG, "Details added Successfully = " + response.toString());
                                startActivity(new Intent(ProfileActivity.this, SymptomsActivity.class));
                            }
                            else{
                                Toast.makeText(ProfileActivity.this,"Error", Toast.LENGTH_SHORT).show();
                            }
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
}
