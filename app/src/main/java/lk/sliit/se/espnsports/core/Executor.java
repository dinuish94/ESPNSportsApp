package lk.sliit.se.espnsports.core;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Handles HTTP calls
 *
 * Created by dinukshakandasamanage on 3/25/18.
 */

public class Executor extends AsyncTask<Integer, Void, String> {

    private String url;
    private Callback cb;
    private String matchId;
    private String apiKey;

    final static String TAG = "Executor";

    public Executor(String url, Callback cb, String apiKey) {
        this.url = url;
        this.cb = cb;
        this.apiKey = apiKey;
    }

    public Executor(String url, Callback cb, String apiKey, String matchId) {
        this.url = url;
        this.cb = cb;
        this.matchId = matchId;
        this.apiKey = apiKey;
    }

    @Override
    protected String doInBackground(Integer... integers) {
        String result = null;

        try {
            URL url = new URL(this.url +
                    (integers == null || integers.length == 0 ? "" : "/" + integers[0]));
            result = getUrlConnectionResult(url);
        } catch (IOException e) {

        }
        return result;
    }

    private String getUrlConnectionResult(URL url) {

        try{
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream stream = conn.getOutputStream();
            String templateRequest;

            if (null == this.matchId) {
                templateRequest = "{\"apikey\": \"" + apiKey + "\"}";
            } else {
                templateRequest = "{\"apikey\": \"" + apiKey + "\", \"unique_id\": \"" + this.matchId + "\"}";
            }
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
            writer.write(templateRequest);
            writer.flush();
            writer.close();
            stream.close();
            conn.connect();
            return readResponse(conn);
        } catch (IOException e){
            Log.e(TAG, "Failed to retrieve data from API", e);
        }
        return null;
    }

    @NonNull
    private String readResponse(HttpURLConnection conn) {

        try {
            InputStream stream = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);

            int MAX_READ_SIZE = 1000000;
            char[] buffer = new char[MAX_READ_SIZE];
            int readSize;
            StringBuffer buf = new StringBuffer();

            while (((readSize = reader.read(buffer)) != -1) && MAX_READ_SIZE > 0) {
                if (readSize > MAX_READ_SIZE) {
                    readSize = MAX_READ_SIZE;
                }
                buf.append(buffer, 0, readSize);
                MAX_READ_SIZE -= readSize;
            }
            return buf.toString();
        } catch (IOException e){
            Log.e(TAG, "Failed to retrieve data from API", e);
        }
        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        try {
            this.cb.onCallbackCompleted(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
