package lk.sliit.se.espnsports.data;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by dinukshakandasamanage on 3/29/18.
 */

public class ResultsListAdapter extends ArrayAdapter {

    private @LayoutRes
    int resourceId;

    public ResultsListAdapter(@NonNull Context context, @NonNull List<SportsEventResult> objects) {
        super(context, android.R.layout.simple_list_item_activated_2, objects);
        this.resourceId = android.R.layout.simple_list_item_activated_2;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SportsEventResult sportsEventResult = (SportsEventResult) getItem(position);
        if (convertView == null){
            convertView = ((LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(resourceId,null);
        }
        // TODO: set values in UI

        return convertView;
    }
}
