
package com.catatankeuangan.test_247.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegionalBloc implements Serializable, Parcelable
{

    @SerializedName("acronym")
    @Expose
    private String acronym;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("otherAcronyms")
    @Expose
    private List<Object> otherAcronyms = null;
    @SerializedName("otherNames")
    @Expose
    private List<Object> otherNames = null;
    public final static Creator<RegionalBloc> CREATOR = new Creator<RegionalBloc>() {


        @SuppressWarnings({
            "unchecked"
        })
        public RegionalBloc createFromParcel(Parcel in) {
            return new RegionalBloc(in);
        }

        public RegionalBloc[] newArray(int size) {
            return (new RegionalBloc[size]);
        }

    }
    ;
    private final static long serialVersionUID = -7036571823431799724L;

    protected RegionalBloc(Parcel in) {
        this.acronym = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.otherAcronyms, (Object.class.getClassLoader()));
        in.readList(this.otherNames, (Object.class.getClassLoader()));
    }

    public RegionalBloc() {
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getOtherAcronyms() {
        return otherAcronyms;
    }

    public void setOtherAcronyms(List<Object> otherAcronyms) {
        this.otherAcronyms = otherAcronyms;
    }

    public List<Object> getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(List<Object> otherNames) {
        this.otherNames = otherNames;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(acronym);
        dest.writeValue(name);
        dest.writeList(otherAcronyms);
        dest.writeList(otherNames);
    }

    public int describeContents() {
        return  0;
    }

}
