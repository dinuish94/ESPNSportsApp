package lk.sliit.se.espnsports.data;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dinukshakandasamanage on 3/25/18.
 */

public class Tournament {

    @SerializedName("id")
    private String tId;

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        try {
            if (this.gettId() != null)
                jsonObject.put("id",this.gettId());

            jsonObject.put("name",this.getName());
            jsonObject.putOpt("type", this.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static Tournament fromJson(JSONObject jsonObject){
        Tournament tournament = new Tournament();
        try {
            tournament.settId(jsonObject.getString("id"));
            tournament.setName(jsonObject.getString("name"));
            tournament.setType(jsonObject.getString("type"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tournament;
    }

    protected Tournament clone() {
        Tournament clonedTournament = new Tournament();
        clonedTournament.settId(this.gettId());
        clonedTournament.setName(this.getName());
        clonedTournament.setType(this.getType());
        return clonedTournament;

    }
}
