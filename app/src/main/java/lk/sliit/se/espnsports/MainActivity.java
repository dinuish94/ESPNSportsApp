package lk.sliit.se.espnsports;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lk.sliit.se.espnsports.core.SportsServiceCallbackListener;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SportsServiceCallbackListener scl = new SportsServiceCallbackListener();
        scl.getDailyResults();
    }
}
