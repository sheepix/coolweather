package app.coolweather.com.coolweather.model;

/**
 * Model City
 * Created by Evan on 8/13/15.
 */
public class City {

    public City() {}

    public City(int id, String name, String code, int provinceId) {
        this.id = id;
        this.cityName = name;
        this.cityCode = code;
        this.provinceId = provinceId;
    }

    private int id;
    private String cityName;
    private String cityCode;
    private int provinceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
