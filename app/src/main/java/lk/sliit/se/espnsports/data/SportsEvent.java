package lk.sliit.se.espnsports.data;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by dinukshakandasamanage on 3/25/18.
 */

public class SportsEvent {

    @SerializedName("tournament")
    private Tournament tournament;

    @SerializedName("scheduled")
    private String scheduled;

    @SerializedName("competitors")
    private List<Competitor> competitors;

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        try {
            if (this.getTournament() != null)
                jsonObject.put("tournament",this.getTournament());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static SportsEvent fromJson(JSONObject jsonObject){
        SportsEvent sportsEvent = new SportsEvent();
        try {
            sportsEvent.setTournament(Tournament.fromJson(jsonObject.getJSONObject("tournament")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sportsEvent;
    }

//    protected Tournament clone() {
//
//    }

}
