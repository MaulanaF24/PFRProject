
package com.catatankeuangan.test_247.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Language implements Serializable, Parcelable
{

    @SerializedName("iso639_1")
    @Expose
    private String iso6391;
    @SerializedName("iso639_2")
    @Expose
    private String iso6392;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nativeName")
    @Expose
    private String nativeName;
    public final static Creator<Language> CREATOR = new Creator<Language>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Language createFromParcel(Parcel in) {
            return new Language(in);
        }

        public Language[] newArray(int size) {
            return (new Language[size]);
        }

    }
    ;
    private final static long serialVersionUID = 4396995175625459288L;

    protected Language(Parcel in) {
        this.iso6391 = ((String) in.readValue((String.class.getClassLoader())));
        this.iso6392 = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.nativeName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Language() {
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getIso6392() {
        return iso6392;
    }

    public void setIso6392(String iso6392) {
        this.iso6392 = iso6392;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(iso6391);
        dest.writeValue(iso6392);
        dest.writeValue(name);
        dest.writeValue(nativeName);
    }

    public int describeContents() {
        return  0;
    }

}
