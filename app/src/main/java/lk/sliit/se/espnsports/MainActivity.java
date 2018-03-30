package lk.sliit.se.espnsports;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
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
    AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

//        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#914242"));
        bottomNavigation.setAccentColor(Color.parseColor("#1b5a99"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        bottomNavigation.setElevation(100);

        SportsService scl = new SportsService(this);
        scl.getLiveSportsUpdates();

        ListView lv = findViewById(R.id.listView);
        lv.setOnItemClickListener(this);

        resultsListAdapter = new ResultsListAdapter(this, rawData);
        lv.setAdapter(resultsListAdapter);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                System.out.println(
                        position
                );
            }
        });

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Match Summary", R.drawable.ic_map_24dp);
        bottomNavigation.addItem(item1);

        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Fixtures", R.drawable.ic_library_books_black_24dp);;
        bottomNavigation.addItem(item2);

    }

//    bottomNavigation.(new AHBottomNavigation.OnTabSelectedListener() {
//        @Override
//        public void onTabSelected(int position, boolean wasSelected) {
////            fragment.updateColor(Color.parseColor(colors[position]));
//        }
//    });

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
//        intent.putExtra("data", rawData.get(i).getSportEvent().getTournament().getName());
//        MainActivity.this.startActivity(intent);
    }
}
