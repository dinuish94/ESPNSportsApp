package lk.sliit.se.espnsports.core;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import lk.sliit.se.espnsports.data.SportsEvent;
import lk.sliit.se.espnsports.data.SportsEventResult;

/**
 * Created by dinukshakandasamanage on 3/29/18.
 */

public class SportsServiceCallbackListener implements Callback {

    SportsService ss;
//    volatile SynchronousQueue<SportsEventResult> output;
    private volatile ArrayList<SportsEventResult> result;

    public SportsServiceCallbackListener() {
        ss = new SportsService(this);
    }

    public List<SportsEventResult> getDailyResults(){
        result  = new ArrayList<>();
        ss.getLiveSportsUpdates();
        return result;
    }

    public void getFixtures(){
        ss.getFixtures();
    }

    @Override
    public void onCallbackCompleted(String data) throws JSONException {
        Gson gson = new Gson();

        JsonElement element = gson.fromJson(data, JsonElement.class).getAsJsonObject().get("results");
        JsonArray results;
        Type listType;

        // If the result is returned from the Sports events results call
        if (null != element){
            listType = new TypeToken<ArrayList<SportsEventResult>>(){}.getType();
            results = element.getAsJsonArray();

        } else {

            // The result is returned from Fixtures service call
            listType = new TypeToken<List<SportsEvent>>(){}.getType();
            results = gson.fromJson(data, JsonElement.class).getAsJsonObject().get("sport_events").getAsJsonArray();
        }

        result = gson.fromJson(results, listType);
    }
}
