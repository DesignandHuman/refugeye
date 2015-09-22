package com.refugeye;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class PictoListAdapter extends ArrayAdapter<Picto> {

    private final LayoutInflater inflater;

    public PictoListAdapter(Context context) {
        super(context, 0);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.r_picto, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

        holder.image.setImageResource(getItem(position).resId);
        return convertView;
    }

    private class ViewHolder {
        public final ImageView image;

        public ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.picto_image);
        }
    }
}
