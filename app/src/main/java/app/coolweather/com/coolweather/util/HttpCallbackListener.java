package app.coolweather.com.coolweather.util;

/**
 * Http请求监听器
 * Created by Evan on 8/13/15.
 */
public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);
}
