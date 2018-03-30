package lk.sliit.se.espnsports.data;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a match entity
 *
 * Created by dinukshakandasamanage on 3/30/18.
 */

public class Match {

    @SerializedName("unique_id")
    private String id;

    @SerializedName("team-1")
    private String team1;

    @SerializedName("team-2")
    private String team2;

    @SerializedName("type")
    private String type;

    @SerializedName("date")
    private String date;

    @SerializedName("toss_winner_team")
    private String tossWinner;

    @SerializedName("matchStarted")
    private boolean started;

    private String score;

    private String summary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTossWinner() {
        return tossWinner;
    }

    public void setTossWinner(String tossWinner) {
        this.tossWinner = tossWinner;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
