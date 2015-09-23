package com.refugeye;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.mobeta.android.dslv.DragSortListView;

public class Home extends AppCompatActivity {

    public DrawingView drawingView;
    public DragSortListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawingView = (DrawingView) findViewById(R.id.home_drawing_view);
        drawingView.setupDrawing();

        listView = (DragSortListView) findViewById(R.id.home_picto_list);

        final PictoListAdapter pictoListAdapter = new PictoListAdapter(this);


        pictoListAdapter.add(new Picto(R.drawable.ic_2440));
        pictoListAdapter.add(new Picto(R.drawable.ic_2441));
        pictoListAdapter.add(new Picto(R.drawable.ic_2442));
        pictoListAdapter.add(new Picto(R.drawable.air6));
        pictoListAdapter.add(new Picto(R.drawable.airplane66));
        pictoListAdapter.add(new Picto(R.drawable.airplane67));
        pictoListAdapter.add(new Picto(R.drawable.airplane68));
        pictoListAdapter.add(new Picto(R.drawable.barscode));
        pictoListAdapter.add(new Picto(R.drawable.black330));
        pictoListAdapter.add(new Picto(R.drawable.black331));
        pictoListAdapter.add(new Picto(R.drawable.boat17));
        pictoListAdapter.add(new Picto(R.drawable.box49));
        pictoListAdapter.add(new Picto(R.drawable.boxes1));
        pictoListAdapter.add(new Picto(R.drawable.boxes2));
        pictoListAdapter.add(new Picto(R.drawable.call36));
        pictoListAdapter.add(new Picto(R.drawable.call37));
        pictoListAdapter.add(new Picto(R.drawable.chronometer10));
        pictoListAdapter.add(new Picto(R.drawable.clipboard52));

        listView.setAdapter(pictoListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), pictoListAdapter.getItem(position).resId);
                drawingView.addBitmap(bitmap);
            }
        });

        findViewById(R.id.home_clear_canvas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.reset();
            }
        });

        findViewById(R.id.home_save_canvas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.save(Home.this);
            }
        });
    }


}
