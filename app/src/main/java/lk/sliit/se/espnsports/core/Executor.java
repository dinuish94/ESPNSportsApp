package lk.sliit.se.espnsports.core;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dinukshakandasamanage on 3/25/18.
 */

public class Executor extends AsyncTask<Integer, Void, String> {

    private String url;
    private Callback cb;
    private String matchId;
    private static boolean responseSent = false;
    private static final String API_KEY = "BojZjCDPPqUsugN7z0NkxKxZhz62";
    private static final String request = "{\"apikey\": \"BojZjCDPPqUsugN7z0NkxKxZhz62\"}";

    public Executor(String url, Callback cb) {
        this.url = url;
        this.cb = cb;
    }

    public Executor(String url, Callback cb, String matchId) {
        this.url = url;
        this.cb = cb;
        this.matchId = matchId;
    }

    @Override
    protected String doInBackground(Integer... integers) {
        String result = null;

        try{
            URL url = new URL(this.url +
                    (integers == null || integers.length == 0 ? "" : "/" + integers[0]));
            result = getUrlConnectionResult(url);
        } catch (IOException e){

        }
        return result;
    }

    private String getUrlConnectionResult(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        OutputStream stream = conn.getOutputStream();
        String templateRequest;

        if(null == this.matchId){
            templateRequest = "{\"apikey\": \"BojZjCDPPqUsugN7z0NkxKxZhz62\"}";
        } else {
            templateRequest = "{\"apikey\": \"BojZjCDPPqUsugN7z0NkxKxZhz62\", \"unique_id\": \""+this.matchId+"\"}";
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
        writer.write(templateRequest);
        writer.flush();
        writer.close();
        stream.close();
        conn.connect();
        return readResponse(conn);
    }

    @NonNull
    private String readResponse(HttpURLConnection conn) throws IOException {
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
