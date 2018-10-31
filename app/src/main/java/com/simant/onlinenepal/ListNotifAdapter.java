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

class ListNotifAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;

    public ListNotifAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
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
        ListNotifViewHolder holder = null;
        if (convertView == null) {
            holder = new ListNotifViewHolder();
            convertView = LayoutInflater.from(activity).inflate(
                    R.layout.list_notification, parent, false);
            holder.galleryImage = convertView.findViewById(R.id.profile_image);
            holder.nmHeader = convertView.findViewById(R.id.nnmHeader);
            holder.nmDesc = convertView.findViewById(R.id.nnmDes);
            // holder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        } else {
            holder = (ListNotifViewHolder) convertView.getTag();
        }
        holder.galleryImage.setId(position);
        holder.nmHeader.setId(position);
        holder.nmDesc.setId(position);
        // holder.time.setId(position);

        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);

        try{
            holder.nmHeader.setText(song.get(NotificationActivity.KEY_HEADER));
            holder.nmDesc.setText(song.get(NotificationActivity.KEY_DESCRIPTION));
             // holder.sdetails.setText(song.get(NotificationActivity.KEY_DESCRIPTION));

            if(song.get(NotificationActivity.KEY_URLTOIMAGE).toString().length() < 5)
            {
                holder.galleryImage.setVisibility(View.GONE);
            }else{
                Picasso.with(activity)
                        .load(song.get(NotificationActivity.KEY_URLTOIMAGE).toString())
                        .resize(300, 200)
                        .into(holder.galleryImage);
            }
        }catch(Exception e) {}
        return convertView;
    }
}

class ListNotifViewHolder {
    ImageView galleryImage;
    TextView nmHeader, nmDesc, time;
}