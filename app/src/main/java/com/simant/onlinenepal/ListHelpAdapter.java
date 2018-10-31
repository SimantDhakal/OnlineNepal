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

class ListHelpAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;

    public ListHelpAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
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
        ListHelpViewHolder holder = null;
        if (convertView == null) {
            holder = new ListHelpViewHolder();
            convertView = LayoutInflater.from(activity).inflate(
                    R.layout.list_help, parent, false);
            // holder.galleryImage = (ImageView) convertView.findViewById(R.id.galleryImage);
            holder.name = convertView.findViewById(R.id.explore_name);
            holder.phone = convertView.findViewById(R.id.explore_phone);
            holder.address = convertView.findViewById(R.id.explore_address);
            holder.sex = convertView.findViewById(R.id.explore_sex);
            holder.bloodgroup = convertView.findViewById(R.id.explore_blood);
            convertView.setTag(holder);
        } else {
            holder = (ListHelpViewHolder) convertView.getTag();
        }
       // holder.galleryImage.setId(position);
        holder.name.setId(position);
        holder.phone.setId(position);
        holder.address.setId(position);
        holder.sex.setId(position);
        holder.bloodgroup.setId(position);

        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);

        try{
            holder.name.setText(song.get(ReceiveFragment.KEY_NAME));
            holder.phone.setText(song.get(ReceiveFragment.KEY_PHONE));
            holder.address.setText(song.get(ReceiveFragment.KEY_ADDRESS));
            holder.sex.setText(song.get(ReceiveFragment.KEY_SEX));
            holder.bloodgroup.setText(song.get(ReceiveFragment.KEY_BLOODGROUP));

            /*
            if(song.get(ReceiveFragment.KEY_URLTOIMAGE).toString().length() < 5)
            {
                holder.galleryImage.setVisibility(View.GONE);
            }else{
                Picasso.with(activity)
                        .load(song.get(NewsFragment.KEY_URLTOIMAGE).toString())
                        .resize(300, 200)
                        .into(holder.galleryImage);
            }
            */
        }catch(Exception e) {}
        return convertView;
    }
}

class ListHelpViewHolder {
    TextView name, phone, address, sex, bloodgroup;
}