package com.example.caro_matic.caro_matic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private static final int MIN_PASSWORD_LENGTH = 6;
    private TextInputEditText uidEdit,firstNameEdit,middleNameEdit,lastNameEdit;
    private TextInputEditText emailEdit,passwordEdit,cPasswordEdit;
    private Spinner branchSpin, courseSpin, yearSpin;
    private ProgressDialog progressDialog;
    private String uid,firstName,middleName,lastName,email,password,course,cPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        uidEdit = findViewById(R.id.uid);
        firstNameEdit = findViewById(R.id.firstName);
        middleNameEdit = findViewById(R.id.middleName);
        lastNameEdit = findViewById(R.id.lastName);
        emailEdit = findViewById(R.id.email);
        passwordEdit = findViewById(R.id.password);
        cPasswordEdit = findViewById(R.id.confirm_password);
        branchSpin = findViewById(R.id.branch);
        courseSpin = findViewById(R.id.course);
        yearSpin = findViewById(R.id.year);
        Button submitButton = findViewById(R.id.submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uid = uidEdit.getText().toString();
                firstName = firstNameEdit.getText().toString();
                middleName = middleNameEdit.getText().toString();
                lastName = lastNameEdit.getText().toString();
                email = emailEdit.getText().toString();
                course = courseSpin.getSelectedItem().toString().replace("/","");
                password = passwordEdit.getText().toString();
                cPassword = cPasswordEdit.getText().toString();

                if(isValid()){
                    showProgressDialog();
                    register();
                }
            }
        });

        courseSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (adapterView.getSelectedItem().toString()){
                    case "MCA":
                        branchSpin.setAdapter(setMCACourse());
                        yearSpin.setAdapter(setMCAYear());
                        break;
                    case "ME/M.Tech":
                        branchSpin.setAdapter(setMECourse());
                        yearSpin.setAdapter(setMEYear());
                        break;
                    case "BE/B.Tech":
                        branchSpin.setAdapter(setBECourse());
                        yearSpin.setAdapter(setBEYear());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private ArrayAdapter<String> setMCACourse()
    {
        String list[] = {"MCA"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_list_item_1,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        return adapter;
    }

    private ArrayAdapter<String> setMCAYear()
    {
        String list[] = {"FE/FY","SE/SY","TE/TY"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_list_item_1,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        return adapter;
    }

    private ArrayAdapter<String> setMECourse()
    {
        String list[] = {"COMPS","EXTC"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_list_item_1,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        return adapter;
    }

    private ArrayAdapter<String> setMEYear()
    {
        String list[] = {"FE/FY","SE/SY"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_list_item_1,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        return adapter;
    }

    private ArrayAdapter<String> setBECourse()
    {
        String list[] = {"COMPS","EXTC","ETRX","IT"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_list_item_1,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        return adapter;
    }

    private ArrayAdapter<String> setBEYear()
    {
        String list[] = {"FE/FY","SE/SY","TE/TY","BE"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_list_item_1,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        return adapter;
    }

//    private boolean isUIDValid(String uid){
//        Date now = new Date();
//        int year = now.getYear();
//        int ye0 = year - 4;
//        int ye1 = year - 3;
//        int ye2 = year - 2;
//        int ye3 = year - 1;
//        String uid_pattern = "^(" + ye0 + "|" + ye1 + "|" + ye2 + "|" + ye3 + "|" + year + ")(11|12|13|14|15|42|43|45)00\\d\\d$";
//        Pattern pattern = Pattern.compile(uid_pattern);
//        Matcher matcher = pattern.matcher(uid);
//        while (matcher.find())
//            return true;
//        return false;
//    }

    private boolean isValid(){


        if(firstName.isEmpty()){
            firstNameEdit.setError(getString(R.string.firstname_error));
            firstNameEdit.setFocusable(true);
            return false;
        }
        else if(middleName.isEmpty()){
            middleNameEdit.setError(getString(R.string.middlename_error));
            middleNameEdit.setFocusable(true);
            return false;
        }
        else if(lastName.isEmpty()){
            lastNameEdit.setError(getString(R.string.lastname_error));
            lastNameEdit.setFocusable(true);
            return false;
        }
        else if(!isEmailValid(email)){
            emailEdit.setError(getString(R.string.email_error));
            emailEdit.setFocusable(true);
            return false;
        }
        else if(course.isEmpty() || course.equals("Choose branch")){
            branchSpin.setFocusable(true);
            Toast.makeText(RegisterActivity.this,R.string.branch_error,Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(password.isEmpty()){
            passwordEdit.setError(getString(R.string.password_error));
            passwordEdit.setFocusable(true);
            return false;
        }
        else if(password.length() < MIN_PASSWORD_LENGTH){
            passwordEdit.setError(getString(R.string.password_length_error));
            passwordEdit.setFocusable(true);
            return false;
        }
        else if(cPassword.isEmpty()){
            cPasswordEdit.setError(getString(R.string.password_error));
            cPasswordEdit.setFocusable(true);
            return false;
        }
        else if (!cPassword.equals(password)){
            Toast.makeText(RegisterActivity.this,R.string.password_match_error,Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean isEmailValid(String email){
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void register(){
        String base_url = "";
        String url = base_url;

        Map<String ,String > params = new HashMap<String, String>();
        params.put("name",firstName);
        params.put("middlename",middleName);
        params.put("lastname",lastName);
        params.put("email",email);
        params.put("UID",uid);
        params.put("course",course.replace("/",""));
        params.put("branch",branchSpin.getSelectedItem().toString());
        params.put("year",yearSpin.getSelectedItem().toString().replace("/",""));
        params.put("password",password);

        Log.e(TAG,"Params = " + params + " url = " + url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideProgressDialog();
                        Log.e(TAG,"Response = " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideProgressDialog();
                Toast.makeText(RegisterActivity.this,R.string.error,Toast.LENGTH_SHORT).show();
                Log.e(TAG,"Error = " + error);
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }


    private void showProgressDialog(){
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setIndeterminate(false);
        progressDialog.setMessage("Registring to Care-o-Matic");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    private void hideProgressDialog(){
        progressDialog.dismiss();
    }

}

