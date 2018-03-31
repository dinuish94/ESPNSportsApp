package lk.sliit.se.espnsports;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import lk.sliit.se.espnsports.core.Callback;
import lk.sliit.se.espnsports.core.SportsService;
import lk.sliit.se.espnsports.data.ScheduleListAdapter;
import lk.sliit.se.espnsports.data.ScheduledMatch;


public class FixturesFragment extends Fragment implements Callback{

    ScheduleListAdapter scheduleListAdapter;
    private List<ScheduledMatch> upcomingMatches = new ArrayList<>();

    SportsService sp = new SportsService(this);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fixtures, container, false);
        scheduleListAdapter = new ScheduleListAdapter(getActivity(), new ArrayList<ScheduledMatch>());

        ListView upcomingMatcheslv = (ListView) view.findViewById(R.id.scheduledList);
        upcomingMatcheslv.setAdapter(scheduleListAdapter);

        scheduleListAdapter = new ScheduleListAdapter(getActivity(), upcomingMatches);
        upcomingMatcheslv.setAdapter(scheduleListAdapter);

        sp.getFixtures();

        return view;
    }

    @Override
    public void onCallbackCompleted(String data) throws JSONException {
        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(data, JsonElement.class).getAsJsonObject();
        Type listType;

        if (null != jsonObject.get("data")) {
            listType = new TypeToken<ArrayList<ScheduledMatch>>(){}.getType();
            upcomingMatches = gson.fromJson(jsonObject.get("data").toString(), listType);
            scheduleListAdapter.clear();
            scheduleListAdapter.addAll(upcomingMatches);
            scheduleListAdapter.notifyDataSetChanged();
        }


    }
}