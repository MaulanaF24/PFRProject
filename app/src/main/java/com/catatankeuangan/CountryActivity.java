package com.catatankeuangan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.catatankeuangan.adapter.TransaksiAdapter;
import com.catatankeuangan.model.Transaksi;
import com.catatankeuangan.service.APIClient;
import com.catatankeuangan.service.APIInterfacesRest;
import com.catatankeuangan.test_247.model.CountryModel;
import com.catatankeuangan.test_247.model.adapter.CountryModelAdapter;

import org.json.JSONObject;

import java.util.List;

public class CountryActivity extends AppCompatActivity {

    RecyclerView RV;
    List<CountryModel> listcon;
    CountryModel conModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        RV = (RecyclerView) findViewById(R.id.countryRV);

        callTransaksi();
    }

    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;

    public void callTransaksi() {

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(CountryActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<List<CountryModel>> mulaiRequest = apiInterface.getAllData();
        mulaiRequest.enqueue(new Callback<List<CountryModel>>() {
            @Override
            public void onResponse(Call<List<CountryModel>> call, Response<List<CountryModel>> response) {
                progressDialog.dismiss();
                listcon = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (listcon != null) {
                    setupAdapterList(listcon);

                } else {

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(CountryActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(CountryActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<CountryModel>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    public void setupAdapterList(List<CountryModel> model) {
        CountryModelAdapter toadapter = new CountryModelAdapter(CountryActivity.this, model);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RV.setLayoutManager(linearLayoutManager);

        RV.setAdapter(toadapter);
    }
}
