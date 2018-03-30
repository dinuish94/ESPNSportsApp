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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lk.sliit.se.espnsports.R;

/**
 * Feeds match results to list view
 *
 * Created by dinukshakandasamanage on 3/29/18.
 */

public class ResultsListAdapter extends ArrayAdapter {

    private @LayoutRes
    int resourceId;

    private int lastPosition = -1;

    public ResultsListAdapter(@NonNull Context context, @NonNull List<LiveMatch> objects) {
        super(context, android.R.layout.simple_list_item_activated_2, objects);
        this.resourceId = android.R.layout.simple_list_item_activated_2;
    }

    /**
     * view holder class to hold UI components of the custom raw
     *
     */
    private static class ViewHolder{
        TextView textTitle;
        TextView textTeamOne;
        TextView textTeamTwo;
        TextView textScoreTeamOne;
        TextView textScoreTeamTwo;
        TextView textSummary;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LiveMatch liveMatch = (LiveMatch) getItem(position);
        ViewHolder viewHolder;

        //initializing ui components of the custom raw
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_tile_view, parent,false);
            viewHolder.textScoreTeamOne = convertView.findViewById(R.id.txt_team_one_summary);
            viewHolder.textScoreTeamTwo = convertView.findViewById(R.id.txt_team_two_summary);
            viewHolder.textSummary = convertView.findViewById(R.id.txt_event_summary);
            viewHolder.textTeamOne = convertView.findViewById(R.id.txt_team_one);
            viewHolder.textTeamTwo = convertView.findViewById(R.id.txt_team_two);
            viewHolder.textTitle = convertView.findViewById(R.id.txt_game_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        //populate list view custom raw components with data
        lastPosition = position;
        viewHolder.textTitle.setText(liveMatch.getType());
        viewHolder.textTeamTwo.setText(liveMatch.getTeam1());
        viewHolder.textTeamOne.setText(liveMatch.getTeam2());
        viewHolder.textSummary.setText(liveMatch.getTeam1() +" VS "+ liveMatch.getTeam2());
        viewHolder.textScoreTeamOne.setText(liveMatch.getTeam1Score());
        viewHolder.textScoreTeamTwo.setText(liveMatch.getTeam2Score());

        return convertView;
    }
}
