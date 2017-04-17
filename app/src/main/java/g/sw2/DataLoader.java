package g.sw2;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.GsonBuilder;

import java.io.IOException;

import g.sw2.data.DataInfo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Kush Agrawal on 4/4/2017.
 */

public class DataLoader  extends AsyncTask<Void, Void, Void> {

    DataInfo dataInfo;
    @Override
    protected Void doInBackground(Void... voids) {
        Request request = new Request.Builder()
                .url("https://s3.ap-south-1.amazonaws.com/0kingg/data.json")
                .build();

        OkHttpClient client;
        client = new OkHttpClient.Builder().build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

               Log.e("Zenius","On Response");
                //Log.e("MemoryApp", response.body().string());
                //Log.e("Zenius", "Content "+response.body().string());
                String jsonString = response.body().string();
                if (!BuildConfig.FLAVOR.equals(jsonString)) {

                    DataLoader.this.dataInfo = (DataInfo) new GsonBuilder().create().fromJson(jsonString, DataInfo.class);

                    Log.e("Zenius","ClaasLevel  "+DataLoader.this.dataInfo.getClassLevel());

                }

            }


        });

        return null;
    }
}
