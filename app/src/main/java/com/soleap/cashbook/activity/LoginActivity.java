package com.soleap.cashbook.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.soleap.cashbook.R;
import com.soleap.cashbook.content.AppPrefrences;
import com.soleap.cashbook.document.DocumentName;
import com.soleap.cashbook.document.User;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.restapi.APIInterface;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText textInputUserName;
    TextInputEditText textInputPassword;
    CheckBox ckbRememberMe;
    Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_activity);
        textInputUserName = findViewById(R.id.textuserName);
        textInputPassword = findViewById(R.id.textPassword);
        ckbRememberMe = findViewById(R.id.chbRememberMe);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        String userName = textInputUserName.getText().toString();
        String password = textInputPassword.getText().toString();
        final boolean isRememberMe = ckbRememberMe.isChecked();
        final APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Map<String,Object> params = new ArrayMap<>();
        params.put("username", userName);
        params.put("password", password);
        apiInterface.login(params).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (isRememberMe) {
                   AppPrefrences.signIn(LoginActivity.this, user);
                }
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra(DocumentName.DOCUMENT_NAME, DocumentName.ITEM);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });

    }
}