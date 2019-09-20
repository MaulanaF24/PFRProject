
package com.catatankeuangan.test_247.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Translations implements Serializable, Parcelable
{

    @SerializedName("de")
    @Expose
    private String de;
    @SerializedName("es")
    @Expose
    private String es;
    @SerializedName("fr")
    @Expose
    private String fr;
    @SerializedName("ja")
    @Expose
    private String ja;
    @SerializedName("it")
    @Expose
    private String it;
    @SerializedName("br")
    @Expose
    private String br;
    @SerializedName("pt")
    @Expose
    private String pt;
    @SerializedName("nl")
    @Expose
    private String nl;
    @SerializedName("hr")
    @Expose
    private String hr;
    @SerializedName("fa")
    @Expose
    private String fa;
    public final static Creator<Translations> CREATOR = new Creator<Translations>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Translations createFromParcel(Parcel in) {
            return new Translations(in);
        }

        public Translations[] newArray(int size) {
            return (new Translations[size]);
        }

    }
    ;
    private final static long serialVersionUID = -3137219546169281302L;

    protected Translations(Parcel in) {
        this.de = ((String) in.readValue((String.class.getClassLoader())));
        this.es = ((String) in.readValue((String.class.getClassLoader())));
        this.fr = ((String) in.readValue((String.class.getClassLoader())));
        this.ja = ((String) in.readValue((String.class.getClassLoader())));
        this.it = ((String) in.readValue((String.class.getClassLoader())));
        this.br = ((String) in.readValue((String.class.getClassLoader())));
        this.pt = ((String) in.readValue((String.class.getClassLoader())));
        this.nl = ((String) in.readValue((String.class.getClassLoader())));
        this.hr = ((String) in.readValue((String.class.getClassLoader())));
        this.fa = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Translations() {
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getEs() {
        return es;
    }

    public void setEs(String es) {
        this.es = es;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getJa() {
        return ja;
    }

    public void setJa(String ja) {
        this.ja = ja;
    }

    public String getIt() {
        return it;
    }

    public void setIt(String it) {
        this.it = it;
    }

    public String getBr() {
        return br;
    }

    public void setBr(String br) {
        this.br = br;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getFa() {
        return fa;
    }

    public void setFa(String fa) {
        this.fa = fa;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(de);
        dest.writeValue(es);
        dest.writeValue(fr);
        dest.writeValue(ja);
        dest.writeValue(it);
        dest.writeValue(br);
        dest.writeValue(pt);
        dest.writeValue(nl);
        dest.writeValue(hr);
        dest.writeValue(fa);
    }

    public int describeContents() {
        return  0;
    }

}
