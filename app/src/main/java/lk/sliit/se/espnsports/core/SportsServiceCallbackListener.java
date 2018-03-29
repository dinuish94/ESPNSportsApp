package lk.sliit.se.espnsports.core;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import lk.sliit.se.espnsports.data.SportsEventResult;

/**
 * Created by dinukshakandasamanage on 3/29/18.
 */

public class SportsServiceCallbackListener implements Callback {

    SportsService ss;
    List<SportsEventResult> output;

    public SportsServiceCallbackListener() {
        ss = new SportsService(this);
    }

    public List<SportsEventResult> getDailyResults(){
        ss.getLiveSportsUpdates();
        return (output == null ? new ArrayList<SportsEventResult>() : output);
    }

    public void getFixtures(){

    }

    @Override
    public void onCallbackCompleted(String data) throws JSONException {
        Gson gson = new Gson();
        System.out.println(data);
//        JsonArray results = gson.fromJson(data, JsonElement.class).getAsJsonObject().get("results").getAsJsonArray();
        JsonArray results = gson.fromJson(data, JsonElement.class).getAsJsonObject().get("sport_events").getAsJsonArray();
//        Type listType = new TypeToken<ArrayList<SportsEventResult>>(){}.getType();
        Type listType = new TypeToken<ArrayList<SportsEventResult>>(){}.getType();
        output = gson.fromJson(results, listType);
    }
}
