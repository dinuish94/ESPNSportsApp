package lk.sliit.se.espnsports;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lk.sliit.se.espnsports.core.Callback;
import lk.sliit.se.espnsports.core.SportsService;
import lk.sliit.se.espnsports.data.LiveMatch;
import lk.sliit.se.espnsports.data.ResultsListAdapter;
import lk.sliit.se.espnsports.utils.Constants;
import lk.sliit.se.espnsports.utils.PropertyFileUtils;


public class ResultsFragment extends Fragment implements Callback{

    private List<LiveMatch> liveMatches;
    private List<LiveMatch> dailyLiveMatches;
    private ResultsListAdapter resultsListAdapter;

    // Keeps track of total matches
    private int matchCount;

    // Keeps track of the matches the scores are retrieved for
    private int scoreCount;
    private SportsService sportsService;
    private String apiKey = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Read properties file and get API key
        setAPIKey();

        liveMatches = new ArrayList<>();
        dailyLiveMatches = new ArrayList<>();
        sportsService = new SportsService(this, apiKey);

        View view = inflater.inflate(R.layout.fragment_data, container, false);
        resultsListAdapter = new ResultsListAdapter(getActivity(), new ArrayList<LiveMatch>());

        ListView upcomingMatcheslv = (ListView) view.findViewById(R.id.resultsList);

        upcomingMatcheslv.setAdapter(resultsListAdapter);

        resultsListAdapter = new ResultsListAdapter(getActivity(), liveMatches);
        upcomingMatcheslv.setAdapter(resultsListAdapter);

        sportsService.getFixtures();
        sportsService.getLiveSportsUpdates();

        return view;
    }

    private void setAPIKey() {
        try {
            apiKey = PropertyFileUtils.getPropertyValue(Constants.API_KEY_PROPERTY, getActivity().getAssets().open(Constants.APP_PROPERTIES_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCallbackCompleted(String data) throws JSONException {
        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(data, JsonElement.class).getAsJsonObject();
        Type listType;

        // Check if the request has been to retrieve the matches
        // if it is we cannot directly process it since the scores are not returned in the same service call
        if (null != jsonObject.get("matches")){
            listType = new TypeToken<List<LiveMatch>>(){}.getType();

            liveMatches = gson.fromJson(jsonObject.get("matches").toString(), listType);

            // Filter the matches that are scheduled for today
            filterMatchesForToday(liveMatches);

            matchCount = dailyLiveMatches.size();

            sportsService = new SportsService(this, apiKey);

            // Retrieve scores for each match
            for (LiveMatch liveMatch : dailyLiveMatches) {
                sportsService.getScore(liveMatch.getId());
            }

        } else if (null != jsonObject.get("matchStarted")){

            // If the element match started is present, this is response for the score retrieval call
            if (null != jsonObject.get("score")){
                String score = jsonObject.get("score").getAsString();
                Pattern pattern = Pattern.compile("[0-9]{1,3}[\\/][0-9]{0,2}");
                Matcher m1 = pattern.matcher(score.split(" v ")[0].toString());
                Matcher m2 = pattern.matcher(score.split(" v ")[1].toString());

                if (m1.find()) {
                    dailyLiveMatches.get(scoreCount).setTeam1Score(m1.group());
                } else {
                    dailyLiveMatches.get(scoreCount).setTeam1Score("-");
                }

                if (m2.find()) {
                    dailyLiveMatches.get(scoreCount).setTeam2Score(m2.group());
                } else {
                    dailyLiveMatches.get(scoreCount).setTeam1Score("-");
                }
                dailyLiveMatches.get(scoreCount).setScore(jsonObject.get("score").getAsString());
            }

            if (null != jsonObject.get("description")){
                dailyLiveMatches.get(scoreCount).setSummary(jsonObject.get("description").getAsString());
            }

            scoreCount ++;

            // Finally when all scores are retrieved, the data is loaded to the list
            if (matchCount == scoreCount){

                resultsListAdapter.clear();
                resultsListAdapter.addAll(dailyLiveMatches.toArray());
                resultsListAdapter.notifyDataSetChanged();
                scoreCount = 0;
            }
        }
    }

    /**
     * Filter out liveMatches that are scheduled for today
     *
     * @param liveMatches
     */
    private void filterMatchesForToday(List<LiveMatch> liveMatches) {
        Iterator<LiveMatch> matchIterator = liveMatches.iterator();

        while (matchIterator.hasNext()) {
            LiveMatch liveMatch = matchIterator.next();

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date scheduledDate = null;
            try {
                scheduledDate = format.parse(liveMatch.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0);
            today.set(Calendar.MILLISECOND, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);

            if(scheduledDate.equals(today.getTime())){
                dailyLiveMatches.add(liveMatch);
            }
        }
    }

}
