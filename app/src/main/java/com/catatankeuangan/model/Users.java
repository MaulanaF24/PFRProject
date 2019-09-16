package com.catatankeuangan.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Users implements Serializable, Parcelable
{

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("nama_user")
    @Expose
    private String namaUser;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("jenisKelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("noTelp")
    @Expose
    private String noTelp;
    @SerializedName("password")
    @Expose
    private String password;
    public final static Parcelable.Creator<Users> CREATOR = new Creator<Users>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        public Users[] newArray(int size) {
            return (new Users[size]);
        }

    }
            ;
    private final static long serialVersionUID = 8296087932625784742L;

    protected Users(Parcel in) {
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        this.namaUser = ((String) in.readValue((String.class.getClassLoader())));
        this.alamat = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.jenisKelamin = ((String) in.readValue((String.class.getClassLoader())));
        this.noTelp = ((String) in.readValue((String.class.getClassLoader())));
        this.password = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Users() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(username);
        dest.writeValue(namaUser);
        dest.writeValue(alamat);
        dest.writeValue(email);
        dest.writeValue(jenisKelamin);
        dest.writeValue(noTelp);
        dest.writeValue(password);
    }

    public int describeContents() {
        return 0;
    }

}