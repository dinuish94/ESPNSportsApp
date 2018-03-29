package lk.sliit.se.espnsports;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import lk.sliit.se.espnsports.core.Callback;
import lk.sliit.se.espnsports.core.SportsService;
import lk.sliit.se.espnsports.data.ResultsListAdapter;
import lk.sliit.se.espnsports.data.SportsEvent;
import lk.sliit.se.espnsports.data.SportsEventResult;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, Callback{

    private List<SportsEventResult> rawData = new ArrayList<>();
    private ResultsListAdapter resultsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SportsService scl = new SportsService(this);
        scl.getLiveSportsUpdates();

        ListView lv = findViewById(R.id.listView);
        lv.setOnItemClickListener(this);

        resultsListAdapter = new ResultsListAdapter(this, rawData);
        lv.setAdapter(resultsListAdapter);
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

        rawData = gson.fromJson(results, listType);
        resultsListAdapter.clear();
        resultsListAdapter.addAll(rawData.toArray());
        resultsListAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Intent intent = new Intent(this, DescriptionActivity.class);
//        intent.putExtra("data", rawData.get(i).getId());
//        MainActivity.this.startActivity(intent);
    }
}
