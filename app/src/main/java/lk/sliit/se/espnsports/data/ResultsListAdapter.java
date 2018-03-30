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
 * Created by dinukshakandasamanage on 3/29/18.
 */

public class ResultsListAdapter extends ArrayAdapter {

    private @LayoutRes
    int resourceId;

    private int lastPosition = -1;

    public ResultsListAdapter(@NonNull Context context, @NonNull List<Match> objects) {
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
        Match match = (Match) getItem(position);

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
        viewHolder.textTitle.setText(match.getType());
        viewHolder.textTeamTwo.setText(match.getTeam1());
        viewHolder.textTeamOne.setText(match.getTeam2());
        //TODO: get score and summary
        viewHolder.textSummary.setText(match.getTeam1() +" VS "+match.getTeam2());
        viewHolder.textScoreTeamTwo.setText(match.getScore());
        viewHolder.textScoreTeamOne.setText("23-1");

        return convertView;
    }
}
