package hcmute.edu.vn.leafnote.custom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import hcmute.edu.vn.leafnote.ChangeBackgroundActivity;
import hcmute.edu.vn.leafnote.R;

public class CustomActivity extends AppCompatActivity {

    LinearLayout changeBackground;
    TextView closeCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        addControl();

        //Go to change background
        changeBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent changeBackground = new Intent(CustomActivity.this, ChangeBackgroundActivity.class);
                startActivity(changeBackground);
            }
        });

        //Close custom
        closeCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent closeCustom = new Intent(CustomActivity.this, MainActivity.class);
                finish();
                //startActivity(closeCustom);
            }
        });
    }

    private void addControl() {
        changeBackground = (LinearLayout) findViewById(R.id.changeBackground);

        closeCustom = (TextView) findViewById(R.id.txtCloseCustom);
    }
}