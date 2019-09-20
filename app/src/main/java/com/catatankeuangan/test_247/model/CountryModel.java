
package com.catatankeuangan.test_247.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryModel implements Serializable, Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("topLevelDomain")
    @Expose
    private List<String> topLevelDomain = null;
    @SerializedName("alpha2Code")
    @Expose
    private String alpha2Code;
    @SerializedName("alpha3Code")
    @Expose
    private String alpha3Code;
    @SerializedName("callingCodes")
    @Expose
    private List<String> callingCodes = null;
    @SerializedName("capital")
    @Expose
    private String capital;
    @SerializedName("altSpellings")
    @Expose
    private List<String> altSpellings = null;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("subregion")
    @Expose
    private String subregion;
    @SerializedName("population")
    @Expose
    private Integer population;
    @SerializedName("latlng")
    @Expose
    private List<Double> latlng = null;
    @SerializedName("demonym")
    @Expose
    private String demonym;
    @SerializedName("area")
    @Expose
    private Double area;
    @SerializedName("gini")
    @Expose
    private Object gini;
    @SerializedName("timezones")
    @Expose
    private List<String> timezones = null;
    @SerializedName("borders")
    @Expose
    private List<String> borders = null;
    @SerializedName("nativeName")
    @Expose
    private String nativeName;
    @SerializedName("numericCode")
    @Expose
    private String numericCode;
    @SerializedName("currencies")
    @Expose
    private List<Currency> currencies = null;
    @SerializedName("languages")
    @Expose
    private List<Language> languages = null;
    @SerializedName("translations")
    @Expose
    private Translations translations;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("regionalBlocs")
    @Expose
    private List<RegionalBloc> regionalBlocs = null;
    @SerializedName("cioc")
    @Expose
    private String cioc;
    public final static Creator<CountryModel> CREATOR = new Creator<CountryModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CountryModel createFromParcel(Parcel in) {
            return new CountryModel(in);
        }

        public CountryModel[] newArray(int size) {
            return (new CountryModel[size]);
        }

    }
    ;
    private final static long serialVersionUID = 5109039516029534423L;

    protected CountryModel(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.topLevelDomain, (String.class.getClassLoader()));
        this.alpha2Code = ((String) in.readValue((String.class.getClassLoader())));
        this.alpha3Code = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.callingCodes, (String.class.getClassLoader()));
        this.capital = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.altSpellings, (String.class.getClassLoader()));
        this.region = ((String) in.readValue((String.class.getClassLoader())));
        this.subregion = ((String) in.readValue((String.class.getClassLoader())));
        this.population = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.latlng, (Double.class.getClassLoader()));
        this.demonym = ((String) in.readValue((String.class.getClassLoader())));
        this.area = ((Double) in.readValue((Double.class.getClassLoader())));
        this.gini = ((Object) in.readValue((Object.class.getClassLoader())));
        in.readList(this.timezones, (String.class.getClassLoader()));
        in.readList(this.borders, (String.class.getClassLoader()));
        this.nativeName = ((String) in.readValue((String.class.getClassLoader())));
        this.numericCode = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.currencies, (com.catatankeuangan.test_247.model.Currency.class.getClassLoader()));
        in.readList(this.languages, (com.catatankeuangan.test_247.model.Language.class.getClassLoader()));
        this.translations = ((Translations) in.readValue((Translations.class.getClassLoader())));
        this.flag = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.regionalBlocs, (com.catatankeuangan.test_247.model.RegionalBloc.class.getClassLoader()));
        this.cioc = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CountryModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(List<String> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public List<String> getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(List<String> callingCodes) {
        this.callingCodes = callingCodes;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public List<String> getAltSpellings() {
        return altSpellings;
    }

    public void setAltSpellings(List<String> altSpellings) {
        this.altSpellings = altSpellings;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public List<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Object getGini() {
        return gini;
    }

    public void setGini(Object gini) {
        this.gini = gini;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public Translations getTranslations() {
        return translations;
    }

    public void setTranslations(Translations translations) {
        this.translations = translations;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<RegionalBloc> getRegionalBlocs() {
        return regionalBlocs;
    }

    public void setRegionalBlocs(List<RegionalBloc> regionalBlocs) {
        this.regionalBlocs = regionalBlocs;
    }

    public String getCioc() {
        return cioc;
    }

    public void setCioc(String cioc) {
        this.cioc = cioc;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeList(topLevelDomain);
        dest.writeValue(alpha2Code);
        dest.writeValue(alpha3Code);
        dest.writeList(callingCodes);
        dest.writeValue(capital);
        dest.writeList(altSpellings);
        dest.writeValue(region);
        dest.writeValue(subregion);
        dest.writeValue(population);
        dest.writeList(latlng);
        dest.writeValue(demonym);
        dest.writeValue(area);
        dest.writeValue(gini);
        dest.writeList(timezones);
        dest.writeList(borders);
        dest.writeValue(nativeName);
        dest.writeValue(numericCode);
        dest.writeList(currencies);
        dest.writeList(languages);
        dest.writeValue(translations);
        dest.writeValue(flag);
        dest.writeList(regionalBlocs);
        dest.writeValue(cioc);
    }

    public int describeContents() {
        return  0;
    }

}
