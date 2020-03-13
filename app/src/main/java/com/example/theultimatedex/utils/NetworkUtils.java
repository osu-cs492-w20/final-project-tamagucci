package com.example.theultimatedex.utils;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkUtils {

    //private static final

    public static String doHttpGet(String url) throws IOException {
        OkHttpClient mHTTPClient = new OkHttpClient();

        Log.d("UltimateDex/NetworkUtil", "Url Passed = " + url);
        Request request = new Request.Builder().url(url).get().build();
        Log.d("UltimateDex/NetworkUtil", "Request builder request = " + request);

    Log.d("UltimateDex/G3 Test", mHTTPClient.toString());
        Response response = mHTTPClient.newCall(request).execute();

        try {
            return response.body().string();
        } finally {
            response.close();
        }
    }
}
