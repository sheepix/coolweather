package app.coolweather.com.coolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import app.coolweather.com.coolweather.model.City;
import app.coolweather.com.coolweather.model.Province;

/**
 *
 * Created by Evan on 8/13/15.
 */
public class CoolWeatherDB {

    public static final String DB_NAME = "cool_weather";

    public static final int VERSION = 1;

    private static CoolWeatherDB coolWeatherDB;

    private SQLiteDatabase db;

    private CoolWeatherDB(Context context) {
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static CoolWeatherDB getInstance(Context context) {
        if (coolWeatherDB == null) {
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }

    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            db.insert("Province", null, values);
        }
    }

    public List<Province> loadProvinces() {
        List<Province> provinces = new ArrayList<>();
        Cursor cursor = db.query("Province",null,null,null,null,null,null);
        while (cursor.moveToNext()) {
            Province province = new Province();
            province.setId(cursor.getInt(cursor.getColumnIndex("id")));
            province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
            province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
            provinces.add(province);
        }
        cursor.close();
        return provinces;
    }

    public List<City> loadCities(int provinceId) {
        List<City> cities = new ArrayList<>();
        Cursor cursor = db.query("City",null,"province_id = ?",new String[]{String.valueOf(provinceId)},null,null,null);
        while (cursor.moveToNext()) {
            City city = new City();
            city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
            city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
            city.setProvinceId(cursor.getInt(cursor.getColumnIndex("province_id")));
            city.setId(cursor.getInt(cursor.getColumnIndex("id")));
            cities.add(city);
        }
        return cities;
    }

}
