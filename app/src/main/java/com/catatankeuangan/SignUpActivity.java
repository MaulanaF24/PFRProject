package com.catatankeuangan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.catatankeuangan.model.Transaksi;
import com.catatankeuangan.model.Users;
import com.catatankeuangan.service.APIClient;
import com.catatankeuangan.service.APIInterfacesRest;

import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    EditText txtPassword,txtUsername,txtEmail,txtNamauser,txtAlamat,txtHp;
    RadioButton rdBtnPria,rdBtnWanita;
    Button btnSign;
    Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtEmail = findViewById(R.id.txtEmail);
        txtHp = findViewById(R.id.txtHp);
        txtNamauser = findViewById(R.id.txtNamauser);
        txtAlamat = findViewById(R.id.txtAlamat);
        rdBtnPria = findViewById(R.id.rdBtnPria);
        rdBtnWanita = findViewById(R.id.rdBtnWanita);


        btnSign = (Button) findViewById(R.id.btnSign);
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTrans();
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    APIInterfacesRest apiInterface;

    public void saveTrans() {

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);

        Users users = new Users();
       users.setEmail(txtEmail.getText().toString());
       users.setNamaUser(txtNamauser.getText().toString());
       users.setAlamat(txtAlamat.getText().toString());
       users.setNoTelp(txtHp.getText().toString());
       users.setPassword(txtPassword.getText().toString());
       users.setUsername(txtUsername.getText().toString());

       if (rdBtnPria.isChecked()){
           users.setJenisKelamin("Male");
       } else if (rdBtnWanita.isChecked()){
           users.setJenisKelamin("Female");
       }

        Call<Users> mulaiRequest = apiInterface.saveUser(users);
        mulaiRequest.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                user = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (user != null) {
                    Toast.makeText(SignUpActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                } else {

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(SignUpActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {


                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}