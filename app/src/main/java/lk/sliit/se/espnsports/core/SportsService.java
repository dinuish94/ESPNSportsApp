package lk.sliit.se.espnsports.core;

/**
 * Created by dinukshakandasamanage on 3/27/18.
 */

public class SportsService {

    private Callback callback;
    String API_KEY;

    public SportsService(Callback callback) {
        this.callback = callback;
        this.API_KEY = "cqn7fsuue9nczptm6j7a5t98";
    }

    public void getLiveSportsUpdates(){
        Executor exec = new Executor(ServiceEndpoints.DAILY_RESULTS_ENDPOINT + API_KEY, callback);
        exec.execute();
    }

    public void getFixtures() {
        Executor exec = new Executor(ServiceEndpoints.FIXTURES_ENDPOINT + API_KEY, callback);
        exec.execute();
    }


}
