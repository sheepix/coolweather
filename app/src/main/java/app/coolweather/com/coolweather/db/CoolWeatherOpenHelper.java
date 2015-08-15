package app.coolweather.com.coolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import app.coolweather.com.coolweather.model.City;
import app.coolweather.com.coolweather.model.Province;

/**
 * 数据库助手
 * Created by Evan on 8/13/15.
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper {

    private static Province[] provinces = {
            new Province(1, "广东", "广东"),
            new Province(2, "广西", "广西")
    };

    private static City[] cities = {
            new City(1, "江门", "江门", 1),
            new City(2, "广州", "广州", 1),
            new City(3, "深圳", "深圳", 1),
            new City(4, "桂林", "桂林", 2)
    };


    private static final String CREATE_PROVINCE = "create table Province (" +
            "id integer primary key autoincrement," +
            "province_name text," +
            "province_code text)";

    private static final String CREATE_CITY = "create table City (" +
            "id integer primary key autoincrement," +
            "city_name text," +
            "city_code text," +
            "province_id integer)";

    private static final String CREATE_COUNTY = "create table County (" +
            "id integer primary key autoincrement," +
            "county_name text," +
            "county_code text," +
            "city_id integer)";


    public CoolWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTY);

        for (Province province : provinces) {
            ContentValues values = new ContentValues();
            values.put("id", province.getId());
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            db.insert("Province", null, values);
        }

        for (City city : cities) {
            ContentValues values = new ContentValues();
            values.put("id", city.getId());
            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityCode());
            values.put("province_id", city.getProvinceId());
            db.insert("City", null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
