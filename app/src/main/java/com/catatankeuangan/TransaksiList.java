package com.catatankeuangan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.catatankeuangan.adapter.TransaksiAdapter;
import com.catatankeuangan.application.AppController;
import com.catatankeuangan.model.Transaksi;
import com.catatankeuangan.service.APIClient;
import com.catatankeuangan.service.APIInterfacesRest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.queriable.StringQuery;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import org.json.JSONObject;

import java.util.List;

public class TransaksiList extends AppCompatActivity {

    RecyclerView RV;
    List<Transaksi> listTrans;
    FloatingActionButton btnAdd;
    Spinner spnJenis;
    Button btnPilih,btnReset;
    TextView txtSaldo;
    Transaksi trans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Catatan Keuangan");
        RV = (RecyclerView)findViewById(R.id.rvTrans);
        txtSaldo = (TextView)findViewById(R.id.txtSaldo);
        spnJenis = (Spinner)findViewById(R.id.spnJenis1);
        btnPilih = (Button) findViewById(R.id.btnPilih);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnAdd = (FloatingActionButton)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransaksiList.this,AddTransActivity.class);
                startActivity(intent);
            }
        });

        btnPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    callSelectedTransaksi();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPost();
            }
        });

        callPost();

    }

    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void callPost(){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(TransaksiList.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<List<Transaksi>> mulaiRequest = apiInterface.getTransaksi();
        mulaiRequest.enqueue(new Callback<List<Transaksi>>() {
            @Override
            public void onResponse(Call<List<Transaksi>> call, Response<List<Transaksi>> response) {
                progressDialog.dismiss();
                listTrans = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (listTrans !=null) {
                    setupAdapterList(listTrans);

                    int total = 0;
                    for(int i=0;i<listTrans.size();i++){
                        total = total + listTrans.get(i).getSaldoKeluar();
                    }
                    String stotal = String.valueOf(total);
                    txtSaldo.setText("Total = Rp. " + stotal);
                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(TransaksiList.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(TransaksiList.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Transaksi>> call, Throwable t) {
                progressDialog.dismiss();
                sqlQueryList();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });

    }

    public void callSelectedTransaksi(){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(TransaksiList.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<List<Transaksi>> mulaiRequest = apiInterface.selectJenisTransaksi(spnJenis.getSelectedItem().toString());
        mulaiRequest.enqueue(new Callback<List<Transaksi>>() {
            @Override
            public void onResponse(Call<List<Transaksi>> call, Response<List<Transaksi>> response) {
                progressDialog.dismiss();
                listTrans = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (listTrans !=null) {

                    setupAdapterList(listTrans);
                    int total = 0;
                    for(int i=0;i<listTrans.size();i++){
                        total = total + listTrans.get(i).getSaldoKeluar();
                    }
                    String stotal = String.valueOf(total);
                    txtSaldo.setText("Rp. " + stotal);
                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(TransaksiList.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(TransaksiList.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Transaksi>> call, Throwable t) {
                progressDialog.dismiss();
                sqlQueryList();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });

    }

    public void savedb( ){

        FlowManager.getDatabase(AppController.class)
                .beginTransactionAsync(new ProcessModelTransaction.Builder<>(
                        new ProcessModelTransaction.ProcessModel<Transaksi>() {
                            @Override
                            public void processModel(Transaksi transaksi, DatabaseWrapper wrapper) {

                                transaksi.save();
                            }

                        }).addAll(listTrans).build())  // add elements (can also handle multiple)
                .error(new Transaction.Error() {
                    @Override
                    public void onError(Transaction transaction, Throwable error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                })
                .success(new Transaction.Success() {
                    @Override
                    public void onSuccess(Transaction transaction) {
                        Toast.makeText(getApplicationContext(),"Data Tersimpan",Toast.LENGTH_LONG).show();
                        sqlQueryList();
                    }
                }).build().execute();


    }



    public void sqlQueryList(){

        String rawQuery = "SELECT * FROM `Transaksi` ";
        StringQuery<Transaksi> stringQuery = new StringQuery<>(Transaksi.class, rawQuery);
        stringQuery
                .async()
                .queryListResultCallback(new QueryTransaction.QueryResultListCallback<Transaksi>() {
                                             @Override
                                             public void onListQueryResult(QueryTransaction transaction, @NonNull List<Transaksi> models) {
                                                 setupAdapterList(models);
                                             }
                                         }
                )
                .execute();
    }

    public void setupAdapterList(List<Transaksi> model){
        TransaksiAdapter toadapter = new TransaksiAdapter (TransaksiList.this,model);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RV.setLayoutManager(linearLayoutManager);

        RV.setAdapter(toadapter);

        toadapter.setMain(this);
    }

    public void deleteTrans(int id,String ket,String tgl,String jenis,int saldo) {

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);

        Transaksi transaksi = new Transaksi();
        transaksi.setId(id);
        transaksi.setKetTransaksi(ket);
        transaksi.setTglTransaksi(tgl);
        transaksi.setJenisPengeluaran(jenis);
        transaksi.setSaldoKeluar(saldo);

        Call<Transaksi> mulaiRequest = apiInterface.deleteTransaksi(transaksi);
        mulaiRequest.enqueue(new Callback<Transaksi>() {
            @Override
            public void onResponse(Call<Transaksi> call, Response<Transaksi> response) {
                trans = response.body();
                if (trans != null) {
                    Toast.makeText(TransaksiList.this, "Data Terhapus", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(TransaksiList.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(TransaksiList.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<Transaksi> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }
}
