package lk.sliit.se.espnsports.core;

/**
 * Created by dinukshakandasamanage on 3/27/18.
 */

public class SportsService {

    public static final String BASE_URL = "https://api.sportradar.us/cricket-t2/en/schedules/2018-03-25/results.json?api_key=";
    private Callback callback;
    String API_KEY;

    public SportsService(Callback callback) {
        this.callback = callback;
        //TODO: Add API KEY
        this.API_KEY = "";
    }

    public void getLiveSportsUpdates(){
        Executor exec = new Executor(BASE_URL + API_KEY, callback);
        exec.execute();
    }


}
