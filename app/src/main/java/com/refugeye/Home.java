package com.refugeye;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class Home extends AppCompatActivity {

    public DrawingView drawingView;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawingView = (DrawingView) findViewById(R.id.home_drawing_view);
        drawingView.setupDrawing();

        listView = (ListView) findViewById(R.id.home_picto_list);
    }


}
