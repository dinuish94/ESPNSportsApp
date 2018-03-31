package lk.sliit.se.espnsports;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ScoreSummaryFragment extends Fragment {

    private ProgressDialog progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        progress = new ProgressDialog(getContext(), 3);
        progress.setMessage("Loading fixtures");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();

        View view = inflater.inflate(R.layout.fragment_score_summary, container, false);

        return view;
    }

}
