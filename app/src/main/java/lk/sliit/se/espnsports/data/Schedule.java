package lk.sliit.se.espnsports.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dinukshakandasamanage on 3/29/18.
 */

public class Schedule {

    @SerializedName("sport_events")
    private List<SportsEvent> sportsEvents;

    public List<SportsEvent> getSportsEvents() {
        return sportsEvents;
    }

    public void setSportsEvents(List<SportsEvent> sportsEvents) {
        this.sportsEvents = sportsEvents;
    }
}
