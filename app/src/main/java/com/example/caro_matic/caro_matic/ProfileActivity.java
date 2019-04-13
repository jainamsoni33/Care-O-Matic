package com.example.caro_matic.caro_matic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class ProfileActivity extends AppCompatActivity {

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
                String Height = HeightEditText.getText().toString();
                String Weight = WeightEditText.getText().toString();
                String Age = AgeEditText.getText().toString();
                Boolean Male = MaleButton.isChecked();
                Boolean Female = FemaleButton.isChecked();

                startActivity(new Intent(ProfileActivity.this, SymptomsActivity.class));

                Log.i("TAG", Height + Weight + Age + Male.toString() + Female.toString());
            }
        });
    }
}
