package lk.sliit.se.espnsports.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dinukshakandasamanage on 3/29/18.
 */

public class Venue {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("capacity")
    private String capacity;

    @SerializedName("city_name")
    private String cityName;

    @SerializedName("country_name")
    private String countryName;

    @SerializedName("country_code")
    private String countryCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
