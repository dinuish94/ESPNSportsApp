package lk.sliit.se.espnsports.core;

import lk.sliit.se.espnsports.utils.ServiceEndpoints;

/**
 * Contains all operations related to Data retrieving
 *
 * Created by dinukshakandasamanage on 3/27/18.
 */

public class SportsService {

    private Callback callback;
    private String apiKey;

    public SportsService(Callback callback, String apiKey) {
        this.callback = callback;
        this.apiKey = apiKey;
    }

    public void getLiveSportsUpdates(){
        Executor exec = new Executor(ServiceEndpoints.DAILY_RESULTS_ENDPOINT, callback, apiKey);
        exec.execute();
    }

    public void getFixtures() {
        Executor exec = new Executor(ServiceEndpoints.FIXTURES_ENDPOINT, callback, apiKey);
        exec.execute();
    }

    public void getScore(String matchId) {
        Executor exec = new Executor(ServiceEndpoints.GET_SCORES_ENDPOINT, callback, apiKey, matchId);
        exec.execute();
    }


}
