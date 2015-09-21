package com.refugeye;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    public DrawingView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawingView = (DrawingView) findViewById(R.id.home_drawing_view);
        drawingView.setupDrawing();
    }


}
