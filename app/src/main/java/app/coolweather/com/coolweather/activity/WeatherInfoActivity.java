package app.coolweather.com.coolweather.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import app.coolweather.com.coolweather.R;
import app.coolweather.com.coolweather.util.HttpCallbackListener;
import app.coolweather.com.coolweather.util.HttpUtil;

public class WeatherInfoActivity extends Activity {

    private static final int WEATHER_INFO = 0;

    private TextView tvCityName;
    private TextView tvWeather;
    private TextView tvHTemp;
    private TextView tvLTemp;
    private TextView tvPublishDatetime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.weather_info);

        tvCityName = (TextView) findViewById(R.id.tv_city_name);
        tvWeather = (TextView) findViewById(R.id.tv_weather);
        tvHTemp = (TextView) findViewById(R.id.tv_high_temp);
        tvLTemp = (TextView) findViewById(R.id.tv_low_temp);
        tvPublishDatetime = (TextView) findViewById(R.id.tv_publish);

        String cityName = this.getIntent().getStringExtra("cityName");
        queryWeatherInfo(cityName);

    }

    private void queryWeatherInfo(String cityName) {
        String address = "http://apis.baidu.com/apistore/weatherservice/cityname?cityname=";
        try {
            address += URLEncoder.encode(cityName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                final JSONObject resultJson = JSON.parseObject(response);
                if (resultJson.get("errNum") != null && (int) resultJson.get("errNum") == 0 ) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateWeatherInfo(resultJson.getJSONObject("retData"));
                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {
                Log.e("WeatherInfoActivity", e.getMessage());
            }
        });
    }

    private void updateWeatherInfo(JSONObject weatherInfo) {
        String cityName = weatherInfo.getString("city");
        String publishDatetime = weatherInfo.getString("date") +
                " " +
                weatherInfo.getString("time");
        String weather = weatherInfo.getString("weather");
        String lTemp = weatherInfo.getString("l_tmp");
        String hTemp = weatherInfo.getString("h_tmp");

        tvCityName.setText(cityName);
        tvHTemp.setText(hTemp);
        tvLTemp.setText(lTemp);
        tvWeather.setText(weather);
        tvPublishDatetime.setText(publishDatetime);

    }

}
