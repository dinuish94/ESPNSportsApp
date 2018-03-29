//package lk.sliit.se.espnsports.core;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.reflect.TypeToken;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.SynchronousQueue;
//
//import lk.sliit.se.espnsports.data.ResultsListAdapter;
//import lk.sliit.se.espnsports.data.SportsEvent;
//import lk.sliit.se.espnsports.data.SportsEventResult;
//
///**
// * Created by dinukshakandasamanage on 3/29/18.
// */
//
//public class SportsServiceCallbackListener implements Callback {
//
//    SportsService ss;
////    volatile SynchronousQueue<SportsEventResult> output;
//    private volatile ArrayList<SportsEventResult> result;
//
//
//    public SportsServiceCallbackListener() {
//        ss = new SportsService(this);
//
//    }
//
//    public List<SportsEventResult> getDailyResults(){
//        result  = new ArrayList<>();
//        ss.getLiveSportsUpdates();
//        return result;
//    }
//
//    public void getFixtures(){
//        ss.getFixtures();
//    }
//
//
//}
