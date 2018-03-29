package lk.sliit.se.espnsports.core;

import org.json.JSONException;

/**
 * Created by dinukshakandasamanage on 3/27/18.
 */

public interface Callback {
    void onCallbackCompleted(String data) throws JSONException;
}
