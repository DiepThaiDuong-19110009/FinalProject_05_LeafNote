package hcmute.edu.vn.leafnote.activity;

import static hcmute.edu.vn.leafnote.activity.Draw.colorList;
import static hcmute.edu.vn.leafnote.activity.Draw.current_brush;
import static hcmute.edu.vn.leafnote.activity.Draw.pathList;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.leafnote.R;

public class DrawActivity extends AppCompatActivity {

    TextView black, red, yellow, green, blue, eraser, saveDraw;

    public static Path path = new Path();
    public static Paint paint_brush = new Paint();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        addControl();

        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint_brush.setColor(Color.BLACK);
                currentColor(paint_brush.getColor());
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint_brush.setColor(Color.RED);
                currentColor(paint_brush.getColor());
            }
        });

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint_brush.setColor(Color.YELLOW);
                currentColor(paint_brush.getColor());
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint_brush.setColor(Color.GREEN);
                currentColor(paint_brush.getColor());
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint_brush.setColor(Color.BLUE);
                currentColor(paint_brush.getColor());
            }
        });

        eraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pathList.clear();
                colorList.clear();
            }
        });

        //Save bản vẽ
        saveDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DrawActivity.this, "Đã lưu bản phát thảo", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    private void addControl() {
        black = (TextView) findViewById(R.id.colorBlack);
        red = (TextView) findViewById(R.id.colorRed);
        yellow = (TextView) findViewById(R.id.colorYellow);
        green = (TextView) findViewById(R.id.colorGreen);
        blue = (TextView) findViewById(R.id.colorBlue);
        eraser = (TextView) findViewById(R.id.eraser);

        saveDraw = (TextView) findViewById(R.id.txtSaveDraw);

    }

    public void currentColor(int c){
        current_brush = c;
        path = new Path();
    }
}