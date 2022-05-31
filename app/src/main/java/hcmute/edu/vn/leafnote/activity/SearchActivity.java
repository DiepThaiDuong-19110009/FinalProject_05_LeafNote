package hcmute.edu.vn.leafnote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import hcmute.edu.vn.leafnote.R;

public class SearchActivity extends AppCompatActivity {

    TextView backHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        addControl();

        setBackHome();
    }

    private void addControl() {
        backHome = (TextView) findViewById(R.id.txtBackHome);
    }

    public void setBackHome() {
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent backHome = new Intent(SearchActivity.this, MainActivity.class);
                //startActivity(backHome);
                finish();
            }
        });
    }
}