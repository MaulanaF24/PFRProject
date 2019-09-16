package com.catatankeuangan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.catatankeuangan.model.LoginResponse;
import com.catatankeuangan.model.Users;
import com.catatankeuangan.service.APIClient;
import com.catatankeuangan.service.APIInterfacesRest;
import com.catatankeuangan.utils.SharedPreferencesUtil;

import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin,btnSignUp;
    EditText usr,pswd;
    SharedPreferencesUtil session;
    LoginResponse loginResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SharedPreferencesUtil(LoginActivity.this);
        if (!session.getUsername().equals("")) {
            Intent intent = new Intent(LoginActivity.this, TransaksiList.class);
            startActivity(intent);
            finish();
        }

        usr = (EditText) findViewById(R.id.username);
        pswd = (EditText)findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAPI();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void loginAPI() {
        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        Users user1 = new Users();
        user1.setUsername(usr.getText().toString());
        user1.setPassword(pswd.getText().toString());

        Call<LoginResponse> mulaiRequest = apiInterface.requestLogin(user1);
        mulaiRequest.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressDialog.dismiss();
                loginResponse = response.body();
                if (loginResponse != null) {
                    kondisiLogin();
//          Toast.makeText(LoginActivity.this, responseAPI.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(LoginActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    private void kondisiLogin() {
        if (loginResponse.getStatus().equals("OK")) {
            session.setUsername(usr.getText().toString());
            Intent intent = new Intent(LoginActivity.this, TransaksiList.class);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
