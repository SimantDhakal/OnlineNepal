package com.simant.onlinenepal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

class ListAllAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;

    public ListAllAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
    }
    public int getCount() {
        return data.size();
    }
    public Object getItem(int position) {
        return position;
    }
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ListAllViewHolder holder = null;
        if (convertView == null) {
            holder = new ListAllViewHolder();
            convertView = LayoutInflater.from(activity).inflate(
                    R.layout.list_news, parent, false);
            holder.galleryImage = convertView.findViewById(R.id.galleryImage);
            holder.author = convertView.findViewById(R.id.author);
            holder.title = convertView.findViewById(R.id.title);
            holder.sdetails = convertView.findViewById(R.id.sdetails);
            holder.time = convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        } else {
            holder = (ListAllViewHolder) convertView.getTag();
        }
        holder.galleryImage.setId(position);
        holder.author.setId(position);
        holder.title.setId(position);
        holder.sdetails.setId(position);
        holder.time.setId(position);

        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);

        try{
            holder.author.setText(song.get(NewsFragment.KEY_SOURCE));
            holder.title.setText(song.get(NewsFragment.KEY_TITLE));
            holder.time.setText(song.get(NewsFragment.KEY_PUBLISHEDAT));
            holder.sdetails.setText(song.get(NewsFragment.KEY_DESCRIPTION));

            if(song.get(NewsFragment.KEY_URLTOIMAGE).toString().length() < 5)
            {
                holder.galleryImage.setVisibility(View.GONE);
            }else{
                Picasso.with(activity)
                        .load(song.get(NewsFragment.KEY_URLTOIMAGE).toString())
                        .resize(300, 200)
                        .into(holder.galleryImage);
            }
        }catch(Exception e) {}
        return convertView;
    }
}

class ListAllViewHolder {
    ImageView galleryImage;
    TextView author, title, sdetails, time;
}