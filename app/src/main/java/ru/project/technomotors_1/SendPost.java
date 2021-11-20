package ru.project.technomotors_1;

import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SendPost {

    public static final MediaType JSON= MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();


    void sendForm(String url, String json){
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
        //        .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                if (response.isSuccessful()){
                    final String myResponse = response.body().string();
                    Log.i("========TEST==========", myResponse);

                }
            }
        });

     //  Toast.makeText(HomeActivity.,"Нажата кнопка Send",Toast.LENGTH_SHORT).show();

    }
}
