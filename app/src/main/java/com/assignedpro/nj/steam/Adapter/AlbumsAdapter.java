package com.assignedpro.nj.steam.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.assignedpro.nj.steam.R;
import com.assignedpro.nj.steam.Request.Games;
import com.assignedpro.nj.steam.sharedprefrences.SharedPrefrences;
import com.assignedpro.nj.steam.ui.SupportActivity;
import com.bumptech.glide.Glide;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Games> albumList;
    private final SharedPrefrences shPrfs;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;
        private final CardView cardView;
        private final RelativeLayout rel_layout;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            cardView = (CardView)view.findViewById(R.id.card_view);
            rel_layout = (RelativeLayout)view.findViewById(R.id.rel_layout);
        }
    }


    public AlbumsAdapter(Context mContext, List<Games> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
        shPrfs = SharedPrefrences.getsharedprefInstance(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Games album = albumList.get(position);
        holder.title.setText(album.getName());


        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shPrfs.setAppId(String.valueOf(albumList.get(position).getAppid()));
                Intent i = new Intent(mContext, SupportActivity.class);
                mContext.startActivity(i);

            }
        });

    }
    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
