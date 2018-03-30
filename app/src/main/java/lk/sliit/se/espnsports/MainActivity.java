package lk.sliit.se.espnsports;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
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
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import lk.sliit.se.espnsports.core.Callback;
import lk.sliit.se.espnsports.core.SportsService;
import lk.sliit.se.espnsports.data.Match;
import lk.sliit.se.espnsports.data.ResultsListAdapter;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, Callback {

    private List<Match> matches = new ArrayList<>();
    private List<Match> dailyMatches = new ArrayList<>();
    private ResultsListAdapter resultsListAdapter;
    AHBottomNavigation bottomNavigation;
    private int matchCount;
    private int scoreCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        bottomNavigation.setAccentColor(Color.parseColor("#1b5a99"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        bottomNavigation.setElevation(100);

        scoreCount = 0;

        final SportsService scl = new SportsService(this);
        scl.getLiveSportsUpdates();

        ListView lv = findViewById(R.id.listView);
        lv.setOnItemClickListener(this);

        resultsListAdapter = new ResultsListAdapter(this, matches);
        lv.setAdapter(resultsListAdapter);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
//                System.out.println(
//                        position
//                );
//
//                intent.putExtra("data", "test");
//                MainActivity.this.startActivity(intent);
                if (position == 0){
                    scl.getLiveSportsUpdates();
                    Fragment fr = new ResultsFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_place, fr);
                    fragmentTransaction.commit();
                } else {
                    scl.getFixtures();
                    Fragment fr = new FixturesFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_place, fr);
                    fragmentTransaction.commit();
                }


            }
        });

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Match Summary", R.drawable.ic_map_24dp);
        bottomNavigation.addItem(item1);

        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Fixtures", R.drawable.ic_library_books_black_24dp);;
        bottomNavigation.addItem(item2);

    }

    @Override
    public void onCallbackCompleted(String data) throws JSONException {
        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(data, JsonElement.class).getAsJsonObject();
        Type listType;

        if (null != jsonObject.get("matches")){
            listType = new TypeToken<List<Match>>(){}.getType();

            matches = gson.fromJson(jsonObject.get("matches").toString(), listType);
            filterMatchesForToday(matches);

            matchCount = dailyMatches.size();
            final SportsService scl = new SportsService(this);

            for (Match rawDatum : dailyMatches) {
                scl.getScore(rawDatum.getId());
            }

        } else if (null != jsonObject.get("matchStarted")){
            if(null != jsonObject.get("score")){
                dailyMatches.get(scoreCount).setScore(jsonObject.get("score").getAsString());
            }
            if(null != jsonObject.get("description")){
                dailyMatches.get(scoreCount).setSummary(jsonObject.get("description").getAsString());
            }

            scoreCount ++;
        }

        if (matchCount == scoreCount){

            resultsListAdapter.clear();
            resultsListAdapter.addAll(dailyMatches.toArray());
            resultsListAdapter.notifyDataSetChanged();
        }



        // If the result is returned from the Sports events results call
//        if (null != element){
//            listType = new TypeToken<ArrayList<SportsEventResult>>(){}.getType();
//            results = element.getAsJsonArray();
//
//        } else {
//
//            // The result is returned from Fixtures service call
//            listType = new TypeToken<List<SportsEvent>>(){}.getType();
//            results = gson.fromJson(data, JsonElement.class).getAsJsonObject().get("sport_events").getAsJsonArray();
//        }

        // The result is returned from Fixtures service call


    }

    /**
     * Filter out matches that are scheduled for today
     * @param matches
     */
    private void filterMatchesForToday(List<Match> matches) {
        Iterator<Match> matchIterator = matches.iterator();

        while (matchIterator.hasNext()) {
            Match match = matchIterator.next();

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date scheduledDate = null;
            try {
                scheduledDate = format.parse(match.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0);
            today.set(Calendar.MILLISECOND, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);

            if(scheduledDate.equals(today.getTime())){
                dailyMatches.add(match);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Intent intent = new Intent(this, DescriptionActivity.class);
//        intent.putExtra("data", rawData.get(i).getSportEvent().getTournament().getName());
//        MainActivity.this.startActivity(intent);
    }

}
