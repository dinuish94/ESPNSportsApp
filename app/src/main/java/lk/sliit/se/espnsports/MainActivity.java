package lk.sliit.se.espnsports;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import lk.sliit.se.espnsports.utils.FragmentNames;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Styles for bottom navigation
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#ff0099cc"));
        bottomNavigation.setAccentColor(Color.parseColor("#fdfdfd"));
        bottomNavigation.setInactiveColor(Color.parseColor("#bcbcbc"));
        bottomNavigation.setElevation(100);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        // Select match results fragment at the start up
        selectFragment(FragmentNames.RESULT_FRAGMENT);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (position == 0){
                    selectFragment(FragmentNames.RESULT_FRAGMENT);
                } else {
                    selectFragment(FragmentNames.SCHEDULE_FRAGMENT);
                }
                return true;
            }
        });

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Match Summary", R.drawable.ic_map_24dp);
        bottomNavigation.addItem(item1);

        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Fixtures", R.drawable.ic_library_books_black_24dp);
        bottomNavigation.addItem(item2);

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    }

    private void selectFragment(String fragmentName){
        if (fragmentName.equals(FragmentNames.RESULT_FRAGMENT)){
            Fragment fr = new ResultsFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_place, fr);
            fragmentTransaction.commit();
        } else if(fragmentName.equals(FragmentNames.SCHEDULE_FRAGMENT)){
            Fragment fr = new FixturesFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_place, fr);
            fragmentTransaction.commit();
        }
    }

}
