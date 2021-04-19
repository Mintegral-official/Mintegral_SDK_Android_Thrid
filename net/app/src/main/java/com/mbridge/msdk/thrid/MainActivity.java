package com.mbridge.msdk.thrid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;

import com.mbridge.msdk.thrid.okhttp.OkHttpClient;
import com.mbridge.msdk.thrid.okhttp.Request;
import com.mbridge.msdk.thrid.okhttp.Response;

import java.io.IOException;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String URL = "https://net.rayjump.com/openapi/ad/v5?app_id=118690&unit_id=146874&placement_id=138786&sign=9329a7706dd43d6ed64d022ad0e7b13b&req_type=2&ad_num=20&tnum=1&only_impression=1&ping_mode=1&ttc_ids=%5B%5D&display_info=%5B%5D&ad_source_id=1&session_id=607927e596a1260001c04b5c&ad_type=94&offset=0&rw_plus=1&ts=1618552822283&st=8c2d54f625e207b6b841b3b9a0d9e921&channel=&band_width=598286&open=0&platform=1&os_version=8.0.0&package_name=com.mbridge.sdk.demo&app_version_name=1.0&app_version_code=1&orientation=1&model=SM-G9550&brand=samsung&gaid=&gaid2=inJ3inVPHUDwDFibD0TTiUiAWn3BfURwHUR9irc3f7RPGaVA&mnc=&mcc=&network_type=9&network_str=&language=zh-Hans-CN&timezone=GMT%2B08%3A00&useragent=Mozilla%2F5.0+%28Linux%3B+Android+8.0.0%3B+SM-G9550+Build%2FR16NW%3B+wv%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Version%2F4.0+Chrome%2F90.0.4430.66+Mobile+Safari%2F537.36&sdk_version=MAL_15.5.12&gp_version=11.6.18-all+%5B0%5D+%5BPR%5D+211882939&screen_size=1080x2220&is_clever=2&version_flag=1&has_wx=true&cache1=57928&cache2=35025&power_rate=98&charging=1&web_env=%7B%22webgl%22%3A1%7D&http_req=2&dvi=4BzXDkQ3RUE0R0M0DkP3hrKuHcKuHoR1RUxUfrH0inH3HnRBDkieHaV0WozULkN0G0RMR0M0H72TRUE0iAVBi0RsRrxwH0R1inDPiBM0DbN0G0zYD%2BzwfUNwJUttWoSthr2QDkzuW%2BDbDZMlD%2BzwHkc0L5T06N%3D%3D&unknown_source=1&sys_id=b27bdaee-aae0-5e34-b8db-f0c7e6540d39&api_version=2.1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_request).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        HandlerThread handlerThread = new HandlerThread("test");
        handlerThread.start();
        final Handler handler = new Handler(handlerThread.getLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                OkHttpClient.Builder client = new OkHttpClient.Builder();
                Request request = new Request.Builder().url(URL).build();
                try {
                    Response response = client.build().newCall(request).execute();
                    Logger.getLogger(MainActivity.class.getName()).info("response: " + response.code() + "\n" + response.body().string());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                handler.postDelayed(this, 3 * 1000);
            }
        }, 5 * 1000);
    }
}
