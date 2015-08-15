package app.coolweather.com.coolweather.model;

/**
 *
 * Created by Evan on 8/13/15.
 */
public class Province {

    public Province() {}

    public Province(int id, String provinceName, String provinceCode) {
        this.id = id;
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
    }

    private int id;

    private String provinceName;

    private String provinceCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}
