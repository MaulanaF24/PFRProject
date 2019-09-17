package com.catatankeuangan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.catatankeuangan.model.Transaksi;
import com.catatankeuangan.service.APIClient;
import com.catatankeuangan.service.APIInterfacesRest;
import com.catatankeuangan.utils.SharedPreferencesUtil;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddTransActivity extends AppCompatActivity {

    EditText txtKet, txtTgl, txtSaldo;
    Button btnSave;
    Spinner spnJenis;
    Transaksi trans;
    SharedPreferencesUtil session;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private DecimalFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trans);
        setTitle("Catat Transaksi Baru");

        initToolbar();

        txtKet = (EditText) findViewById(R.id.txtKet);
        txtTgl = (EditText) findViewById(R.id.txtTgl);
        txtSaldo = (EditText) findViewById(R.id.txtSaldo);
        spnJenis = (Spinner) findViewById(R.id.spnJenis);
        btnSave = (Button) findViewById(R.id.btnSave);

        formatter = new DecimalFormat("###,###,###");
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        session = new SharedPreferencesUtil(AddTransActivity.this);



        txtTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTrans();
                Intent intent = new Intent(AddTransActivity.this,TransaksiList.class);
                setResult(5,intent);
                finish();
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pengeluaran Baru");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent (AddTransActivity.this,TransaksiList.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
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

    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                txtTgl.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }
}


