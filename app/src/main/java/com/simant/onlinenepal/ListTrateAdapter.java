package com.simant.onlinenepal;

/**
 * Created by Ethical on 3/25/2018.
 */

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

class ListTrateAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;

    public ListTrateAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
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
        ListTrateViewHolder holder = null;
        if (convertView == null) {
            holder = new ListTrateViewHolder();
            convertView = LayoutInflater.from(activity).inflate(
                    R.layout.list_vege, parent, false);
            holder.galleryImage = convertView.findViewById(R.id.galleryImage);
            holder.item = convertView.findViewById(R.id.item);
            holder.quantity = convertView.findViewById(R.id.quantity);
            holder.least = convertView.findViewById(R.id.least);
            holder.maximum = convertView.findViewById(R.id.maximum);
            holder.minimum = convertView.findViewById(R.id.minimum);
            convertView.setTag(holder);
        } else {
            holder = (ListTrateViewHolder) convertView.getTag();
        }
        holder.galleryImage.setId(position);
        holder.item.setId(position);
        holder.quantity.setId(position);
        holder.least.setId(position);
        holder.maximum.setId(position);
        holder.minimum.setId(position);

        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);

        try{
            holder.item.setText(song.get(Ebazzar.KEY_ITEM));
            holder.quantity.setText(song.get(Ebazzar.KEY_QUANTITY));
            holder.least.setText(song.get(Ebazzar.KEY_LEAST));
            holder.maximum.setText(song.get(Ebazzar.KEY_MAXIMUM));
            holder.minimum.setText(song.get(Ebazzar.KEY_MINIMUM));

            if(song.get(Ebazzar.KEY_URLTOIMAGE).toString().length() < 5)
            {
                holder.galleryImage.setVisibility(View.GONE);
            }else{
                Picasso.with(activity)
                        .load(song.get(Ebazzar.KEY_URLTOIMAGE).toString())
                        .resize(300, 200)
                        .into(holder.galleryImage);
            }

        }catch(Exception e) {}
        return convertView;
    }
}

class ListTrateViewHolder {
    ImageView galleryImage;
    TextView item, quantity, least, maximum, minimum;
}

