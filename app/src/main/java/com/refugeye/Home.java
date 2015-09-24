package com.refugeye;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Locale;

public class Home extends AppCompatActivity {

    public DrawingView drawingView;
    public ListView listView;
    private EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawingView = (DrawingView) findViewById(R.id.home_drawing_view);
        drawingView.setupDrawing();

        listView = (ListView) findViewById(R.id.home_picto_list);

        final PictoListAdapter pictoListAdapter = new PictoListAdapter(this);

        pictoListAdapter.add(new Picto(R.drawable.ic_2440, new String[] {"call"}));
        pictoListAdapter.add(new Picto(R.drawable.ic_2441, new String[]{"24"}));
        pictoListAdapter.add(new Picto(R.drawable.ic_2442, new String[]{"24"}));
        pictoListAdapter.add(new Picto(R.drawable.air6, new String[]{"plane"}));
        pictoListAdapter.add(new Picto(R.drawable.airplane66, new String[]{"plane"}));
        pictoListAdapter.add(new Picto(R.drawable.airplane67, new String[]{"plane"}));
        pictoListAdapter.add(new Picto(R.drawable.airplane68, new String[]{"plane"}));
        pictoListAdapter.add(new Picto(R.drawable.barscode, new String[]{"barcode"}));
        pictoListAdapter.add(new Picto(R.drawable.black330, new String[]{"umbrella"}));
        pictoListAdapter.add(new Picto(R.drawable.black331, new String[]{"car"}));
        pictoListAdapter.add(new Picto(R.drawable.boat17, new String[]{"boat"}));
        pictoListAdapter.add(new Picto(R.drawable.box49, new String[]{"box"}));
        pictoListAdapter.add(new Picto(R.drawable.boxes1, new String[]{"boxes"}));
        pictoListAdapter.add(new Picto(R.drawable.boxes2, new String[]{"boxes"}));
        pictoListAdapter.add(new Picto(R.drawable.call36, new String[]{"call"}));
        pictoListAdapter.add(new Picto(R.drawable.call37, new String[]{"call"}));
        pictoListAdapter.add(new Picto(R.drawable.chronometer10, new String[]{"time"}));
        pictoListAdapter.add(new Picto(R.drawable.clipboard52, new String[]{"clipboard"}));

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
        search = (EditText) findViewById(R.id.home_search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = search.getText().toString().toLowerCase(Locale.getDefault());
                pictoListAdapter.filter(text);

            }
        });
    }


}
