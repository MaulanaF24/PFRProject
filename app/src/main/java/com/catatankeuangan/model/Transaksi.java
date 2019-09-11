package com.catatankeuangan.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.catatankeuangan.application.AppController;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppController.class)
public class Transaksi extends BaseModel implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    @PrimaryKey(autoincrement = true)
    private Integer id;
    @SerializedName("ketTransaksi")
    @Expose
    @Column
    private String ketTransaksi;
    @SerializedName("saldoKeluar")
    @Expose
    @Column
    private Integer saldoKeluar;
    @SerializedName("tglTransaksi")
    @Expose
    @Column
    private String tglTransaksi;
    @SerializedName("jenisPengeluaran")
    @Expose
    @Column
    private String jenisPengeluaran;
    public final static Parcelable.Creator<Transaksi> CREATOR = new Creator<Transaksi>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Transaksi createFromParcel(Parcel in) {
            return new Transaksi(in);
        }

        public Transaksi[] newArray(int size) {
            return (new Transaksi[size]);
        }

    }
            ;
    private final static long serialVersionUID = -2609568848807734365L;

    protected Transaksi(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.ketTransaksi = ((String) in.readValue((String.class.getClassLoader())));
        this.saldoKeluar = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.tglTransaksi = ((String) in.readValue((String.class.getClassLoader())));
        this.jenisPengeluaran = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Transaksi() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKetTransaksi() {
        return ketTransaksi;
    }

    public void setKetTransaksi(String ketTransaksi) {
        this.ketTransaksi = ketTransaksi;
    }

    public Integer getSaldoKeluar() {
        return saldoKeluar;
    }

    public void setSaldoKeluar(Integer saldoKeluar) {
        this.saldoKeluar = saldoKeluar;
    }

    public String getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(String tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }

    public String getJenisPengeluaran() {
        return jenisPengeluaran;
    }

    public void setJenisPengeluaran(String jenisPengeluaran) {
        this.jenisPengeluaran = jenisPengeluaran;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(ketTransaksi);
        dest.writeValue(saldoKeluar);
        dest.writeValue(tglTransaksi);
        dest.writeValue(jenisPengeluaran);
    }

    public int describeContents() {
        return 0;
    }

}