package lk.sliit.se.espnsports.data;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import lk.sliit.se.espnsports.R;

/**
 * Feeds schedules to list view
 *
 * Created by dinukshakandasamanage on 3/30/18.
 */

public class ScheduleListAdapter extends ArrayAdapter {

    private @LayoutRes
    int resourceId;

    private int lastPosition = -1;

    public ScheduleListAdapter(@NonNull Context context, @NonNull List<ScheduledMatch> scheduledMatches) {
        super(context, android.R.layout.simple_list_item_activated_2, scheduledMatches);
        this.resourceId = android.R.layout.simple_list_item_activated_2;
    }

    /**
     * view holder class to hold UI components of the custom raw
     *
     */
    private static class ViewHolder{
        TextView date;
        TextView name;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ScheduledMatch scheduledMatch = (ScheduledMatch) getItem(position);

        ScheduleListAdapter.ViewHolder viewHolder;

        //initializing ui components of the custom raw
        if (convertView == null){
            viewHolder = new ScheduleListAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.schedule_list, parent,false);
            viewHolder.date = convertView.findViewById(R.id.txt_date);
            viewHolder.name = convertView.findViewById(R.id.txt_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ScheduleListAdapter.ViewHolder)convertView.getTag();
        }

        //populate list view custom raw components with data
        lastPosition = position;
        viewHolder.name.setText(scheduledMatch.getName());
        viewHolder.date.setText(scheduledMatch.getDate());

        return convertView;
    }
}
