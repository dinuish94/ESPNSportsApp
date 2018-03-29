package lk.sliit.se.espnsports.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dinukshakandasamanage on 3/29/18.
 */

public class SportsEventStatus {

    @SerializedName("match_status")
    private String matchStatus;

    @SerializedName("status")
    private String status;

    @SerializedName("winner_id")
    private String winnerId;

    @SerializedName("match_result")
    private String matchResult;

    @SerializedName("match_result_type")
    private String matchResultType;

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(String winnerId) {
        this.winnerId = winnerId;
    }

    public String getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(String matchResult) {
        this.matchResult = matchResult;
    }

    public String getMatchResultType() {
        return matchResultType;
    }

    public void setMatchResultType(String matchResultType) {
        this.matchResultType = matchResultType;
    }
}
