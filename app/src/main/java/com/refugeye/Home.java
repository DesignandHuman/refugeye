package com.refugeye;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Home extends AppCompatActivity {

    public DrawingView drawingView;
    public ListView listView;
    private EditText search;
    private SwipeView swipeView;
    private View successOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final PictoListAdapter pictoListAdapter = new PictoListAdapter(this);

        drawingView = (DrawingView) findViewById(R.id.home_drawing_view);
        drawingView.setupDrawing();
        drawingView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {

                if (event.getAction() == DragEvent.ACTION_DROP) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), pictoListAdapter.getItem(pictoListAdapter.selectedPosition).resId);
                    bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * 2, bitmap.getHeight() * 2, false);
                    drawingView.addBitmap(bitmap, event.getX(), event.getY());

                }
                if (event.getAction() == DragEvent.ACTION_DRAG_STARTED) {
                    swipeView.close();
                }
                return true;
            }
        });

        swipeView = (SwipeView) findViewById(R.id.sliding_pannel);

        listView = (ListView) findViewById(R.id.home_picto_list);

        pictoListAdapter.add(new Picto(R.drawable.all_icons_01, new String[] {"camping", "camping"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_02, new String[]{"24"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_03, new String[]{"24"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_04, new String[]{"plane", "avion"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_05, new String[]{"plane", "avion"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_06, new String[]{"plane", "avion"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_07, new String[]{"plane", "avion"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_08, new String[]{"barcode", "code-barre"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_09, new String[]{"umbrella", "parapluie"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_10, new String[]{"car", "voiture"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_11, new String[]{"boat", "bateau"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_12, new String[]{"box", "boite"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_13, new String[]{"boxes", "boites"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_14, new String[]{"boxes", "boites"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_15, new String[]{"call", "appel"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_16, new String[]{"call", "appel"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_17, new String[]{"time", "heure"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_18, new String[]{"clipboard", "presse-papier"}));

        listView.setAdapter(pictoListAdapter);


        findViewById(R.id.home_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().add(R.id.drawer_layout, new About()).commit();
            }
        });

        findViewById(R.id.home_clear_canvas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.reset();
            }
        });

        successOverlay = findViewById(R.id.success_overlay);

        findViewById(R.id.home_save_canvas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.save(Home.this);
                successOverlay.setVisibility(View.VISIBLE);
                successOverlay.setAlpha(1.0f);
                successOverlay.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        successOverlay.animate().alpha(-1.0f).setDuration(800).start();
                    }
                }, 500);
            }
        });
        search = (EditText) findViewById(R.id.home_search);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
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

    public Bitmap convertToBitmap(Drawable drawable, int widthPixels, int heightPixels) {
        Bitmap mutableBitmap = Bitmap.createBitmap(widthPixels, heightPixels, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mutableBitmap);
        drawable.setBounds(0, 0, widthPixels, heightPixels);
        drawable.draw(canvas);

        return mutableBitmap;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
