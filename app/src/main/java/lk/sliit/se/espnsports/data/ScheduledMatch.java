package lk.sliit.se.espnsports.data;

import com.google.gson.annotations.SerializedName;

/**
 * Represents an upcoming match entity
 *
 * Created by dinukshakandasamanage on 3/30/18.
 */

public class ScheduledMatch {

    @SerializedName("name")
    private String name;

    @SerializedName("date")
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
