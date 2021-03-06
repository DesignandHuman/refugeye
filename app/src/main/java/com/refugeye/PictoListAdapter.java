package com.refugeye;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.HashSet;

public class PictoListAdapter extends ArrayAdapter<Picto> {

    private final LayoutInflater inflater;
    private HashSet<Picto> pictos = new HashSet<>();
    public int selectedPosition = -1;

    public PictoListAdapter(Context context) {
        super(context, 0);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.r_picto, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

        holder.image.setImageResource(getItem(position).resId);
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PictoListAdapter.this.selectedPosition = position;
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            }
        });

        return convertView;
    }

    @Override
    public void add(Picto object) {
        super.add(object);
        pictos.add(object);
    }

    public void filter(String text) {
        clear();
        if (text.isEmpty()) {
            addAll(pictos);
            return;
        }

        for (Picto picto : pictos) {
            nameLoop(text, picto);
        }
        notifyDataSetChanged();
    }

    private void nameLoop(String text, Picto picto) {
        for (String name : picto.names) {
            if (name.toLowerCase().startsWith(text.toLowerCase())) {
                add(picto);
                return;
            }
        }
    }

    private class ViewHolder {
        public final ImageView image;

        public ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.picto_image);
        }
    }
}
