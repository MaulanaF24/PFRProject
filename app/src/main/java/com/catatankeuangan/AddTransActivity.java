package com.catatankeuangan;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.catatankeuangan.model.Transaksi;
import com.catatankeuangan.service.APIClient;
import com.catatankeuangan.service.APIInterfacesRest;
import com.catatankeuangan.utils.SharedPreferencesUtil;

import org.json.JSONObject;

import java.util.List;

public class AddTransActivity extends AppCompatActivity {

    EditText txtKet, txtTgl, txtSaldo;
    Button btnSave;
    Spinner spnJenis;
    Transaksi trans;
    SharedPreferencesUtil session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trans);
        setTitle("Catat Transaksi Baru");

        txtKet = (EditText) findViewById(R.id.txtKet);
        txtTgl = (EditText) findViewById(R.id.txtTgl);
        txtSaldo = (EditText) findViewById(R.id.txtSaldo);
        spnJenis = (Spinner) findViewById(R.id.spnJenis);
        btnSave = (Button) findViewById(R.id.btnSave);

        session = new SharedPreferencesUtil(AddTransActivity.this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTrans();
                Intent intent = new Intent(AddTransActivity.this,TransaksiList.class);
                startActivity(intent);
                finish();
            }
        });
    }

    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;

    public void saveTrans() {

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
//        progressDialog = new ProgressDialog(getApplicationContext());
//        progressDialog.setTitle("Loading");
//        progressDialog.show();
        Transaksi transaksi = new Transaksi();
        transaksi.setId(100000);
        transaksi.setKetTransaksi(txtKet.getText().toString());
        transaksi.setTglTransaksi(txtTgl.getText().toString());
        transaksi.setJenisPengeluaran(spnJenis.getSelectedItem().toString());
        transaksi.setSaldoKeluar(Integer.parseInt(txtSaldo.getText().toString()));
        transaksi.setUsername(session.getUsername());

        Call<Transaksi> mulaiRequest = apiInterface.saveTransaksi(transaksi);


        mulaiRequest.enqueue(new Callback<Transaksi>() {
            @Override
            public void onResponse(Call<Transaksi> call, Response<Transaksi> response) {
//              progressDialog.dismiss();
                trans = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (trans != null) {
                    Toast.makeText(AddTransActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                } else {

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(AddTransActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(AddTransActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Transaksi> call, Throwable t) {
               // progressDialog.dismiss();

                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }


}
