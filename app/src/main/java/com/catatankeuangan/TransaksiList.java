package com.catatankeuangan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
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
    Spinner spnJenis;
    Button btnPilih;
    TextView txtSaldo;
    Transaksi trans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Catatan Keuangan");

        initToolbar();

        RV = (RecyclerView)findViewById(R.id.rvTrans);
        txtSaldo = (TextView)findViewById(R.id.txtSaldo);
        spnJenis = (Spinner)findViewById(R.id.spnJenis1);
        btnPilih = (Button) findViewById(R.id.btnPilih);
        btnPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    callSelectedTransaksi();
            }
        });

        callPost();
    }

    //====================TOOLBAR=======================================================

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Catat Pengeluaran");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_basic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            close();
        } else {
           // Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();

            if (item.getTitle().toString().toLowerCase().equals("refresh")) {
                callPost();
            }  else if (item.getTitle().toString().toLowerCase().equals("search")) {
                Intent intent = new Intent(TransaksiList.this,AddTransActivity.class);
                startActivity(intent);
                finish();
            } else if (item.getTitle().toString().toLowerCase().equals("about us")) {
                Intent intent = new Intent(TransaksiList.this,AbousUS.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    //=================END=OF=TOOLBAR=======================================================

    //===================GET ALL DATA FROM WEB SERVICES======================================================
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
    //===================GET ALL DATA FROM WEB SERVICES======================================================

    //===================GET SELECTED JENIS TRANSAKSI DATA FROM WEB SERVICES======================================================
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

    //===================GET SELECTED JENIS TRANSAKSI DATA FROM WEB SERVICES=========================================

    //===================SAVING DATA FROM WEB SERVICES TO SQLite=====================================================
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

    //===================SAVING DATA FROM WEB SERVICES TO SQLite=====================================================

    //===================GET DATA FROM SQLite=====================================================
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

    //===================GET DATA FROM SQLite=====================================================

    //===================SET DATA TO ADAPTER=====================================================
    public void setupAdapterList(List<Transaksi> model){
        TransaksiAdapter toadapter = new TransaksiAdapter (TransaksiList.this,model);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RV.setLayoutManager(linearLayoutManager);

        RV.setAdapter(toadapter);

        toadapter.setMain(this);
    }
    //===================SET DATA TO ADAPTER=====================================================

    //===================DELETE SELECTED DATA on WEB Service=====================================
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
    //===================DELETE SELECTED DATA on WEB Service=====================================

    //===================CLOSE DIALOG=====================================
    public void close(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Udah Itung-itungnya ? Besok ngirit ya !")
                .setCancelable(false)
                .setPositiveButton("Udah",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                               TransaksiList.this.finish();
                            }
                        })
                .setNegativeButton("Belum deng",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {
                        dialog.cancel();

                    }
                }).show();
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            close();

        }
        return super.onKeyDown(keyCode, event);
    }
    //===================CLOSE DIALOG=====================================
}

