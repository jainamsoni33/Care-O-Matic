package com.example.caro_matic.caro_matic;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private Button loginButton,registerButton;
    private TextInputEditText email_edittext,password_edittext;
    private String email,password;
    private ProgressDialog progressDialog;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button forgetPasswordButton = findViewById(R.id.forget_password);
        loginButton= findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);
        email_edittext= findViewById(R.id.email_editext);
        password_edittext = findViewById(R.id.password_edittext);
        ImageView hideShowPassword = findViewById(R.id.hideShowPassword);
        password_edittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT){
                    loginButton.performClick();
                    return true;
                }
                return false;
            }
        });

        hideShowPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        password_edittext.setInputType(InputType.TYPE_CLASS_TEXT);
                        password_edittext.setSelection(password_edittext.getText().length());
                        break;
                    case MotionEvent.ACTION_UP:
                        password_edittext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        password_edittext.setSelection(password_edittext.getText().length());
                        break;
                }
                return true;
            }
        });

//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
//            }
//        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = email_edittext.getText().toString();
                password = password_edittext.getText().toString();

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }

                Login();

//                if(isValid()) {
////                    showProgressDialog();
//                    Login();
//                }
//                    else {
//                      //  Toast.makeText(LoginActivity.this,R.string.network_error,Toast.LENGTH_SHORT).show();
//                    }
                }

        });

    }


    //Checking the input email and password are in valid form or not
    public boolean isValid() {
        if(!isEmailValid(email)) {
            email_edittext.setError(getString(R.string.email_error));
            email_edittext.setFocusable(true);
            return false;
        }
        else if(password.isEmpty() || password.length() < 6) {
            password_edittext.setError(getString(R.string.password_error));
            password_edittext.setFocusable(true);
            return false;
        }
        return true;
    }

    private boolean isEmailValid(String email){
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void Login() {
        String base_url = "";
        String url = base_url + "auth/login";

        Map<String ,String > params = new HashMap<String, String>();
        params.put("email",email);
        params.put("password",password);

        Log.e(TAG,"Params = " + params + " url = " + url);

        startActivity(new Intent(LoginActivity.this, ProfileActivity.class));

//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        hideProgressDialog();
//                        Log.e(TAG,"Login Successful = " + response.toString());
//                        startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
////                            SharedPreferences.Editor editor = getSharedPreferences(Constants.PREFERENCES, MODE_PRIVATE).edit();
////                            editor.putString(Constants.LOGIN_TOKEN,token);
////                            editor.putString(Constants.ROLE,login_as);
////                            editor.apply();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                hideProgressDialog();
//                Log.e(TAG,"Error = " + error);
//            }
//        });
//        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void showProgressDialog(){
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(false);
        progressDialog.setMessage("Logging into Care-o-Matic");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    private void hideProgressDialog(){
        progressDialog.dismiss();
    }


}
