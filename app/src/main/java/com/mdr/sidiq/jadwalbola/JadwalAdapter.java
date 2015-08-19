package com.mdr.sidiq.jadwalbola;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sidiq on 12/06/2015.
 */
public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.JadwalViewHolder> {

    Activity activity;
    ArrayList<JadwalItem> listItem;

    public JadwalAdapter(Activity activity, ArrayList<JadwalItem> listItem){
        this.activity = activity;
        this.listItem = listItem;
    }


    @Override
    public JadwalViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_jadwal, viewGroup, false);
        JadwalViewHolder jadwalViewHolder = new JadwalViewHolder(view);
        return jadwalViewHolder;
    }

    @Override
    public void onBindViewHolder(JadwalViewHolder jadwalViewHolder, int i) {
        jadwalViewHolder.txtMatch.setText(listItem.get(i).getTeamHome() +" v "+ listItem.get(i).getTeamAway());
        jadwalViewHolder.txtLive.setText(listItem.get(i).getKickOff() +" di "+ listItem.get(i).getTv());
        jadwalViewHolder.txtEvent.setText(listItem.get(i).getEvent());
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public static class JadwalViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView txtMatch;
        TextView txtLive;
        TextView txtEvent;

        JadwalViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            txtMatch = (TextView)itemView.findViewById(R.id.txt_item_match);
            txtLive = (TextView)itemView.findViewById(R.id.txt_item_live);
            txtEvent = (TextView)itemView.findViewById(R.id.txt_item_event);
        }
    }
}
