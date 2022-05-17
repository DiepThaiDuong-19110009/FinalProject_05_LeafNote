package hcmute.edu.vn.leafnote.custom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hcmute.edu.vn.leafnote.MainActivity;
import hcmute.edu.vn.leafnote.R;
import hcmute.edu.vn.leafnote.adapter.RecordAdapter;
import hcmute.edu.vn.leafnote.database.DatabaseConnection;
import hcmute.edu.vn.leafnote.entity.Note;
import hcmute.edu.vn.leafnote.entity.Users;

public class CustomRecordActivity extends AppCompatActivity {
    ListView listViewAudio;
    RecordAdapter adapter;
    EditText edtTimKiemAudio;
    TextView txtDone;
    Button btnTimKiemAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_record);

        AnhXa();
        displayAudio();
        searchNoteAudio();
        setDone();
    }

    private void AnhXa() {
        listViewAudio = (ListView) findViewById(R.id.listViewAudio);
        edtTimKiemAudio = (EditText) findViewById(R.id.edtTimKiemAudio);
        txtDone = (TextView) findViewById(R.id.txtDoneAudio);
        btnTimKiemAudio = (Button) findViewById(R.id.btnTimKiemAudio);
    }

    private void displayAudio() {
        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);

        String username = pref.getString("username", "");

        Users u = DatabaseConnection.getInstance(this).userDao().FindUserByUserName(username);

        List<Note> audioList = DatabaseConnection.getInstance(this).noteDao().getAllAudio(u.getId());
        adapter = new RecordAdapter(this, R.layout.custom_record, audioList);
        listViewAudio.setAdapter(adapter);
    }

    private void searchNoteAudio() {
        btnTimKiemAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = edtTimKiemAudio.getText().toString().trim();
                if (TextUtils.isEmpty(search)) {
                    Toast.makeText(CustomRecordActivity.this, "Vui lòng nhập từ khóa tìm kiếm", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.equals(search, "all")) {
                    displayAudio();
                } else {
                    SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);

                    String username = pref.getString("username", "");

                    Users u = DatabaseConnection.getInstance(CustomRecordActivity.this).userDao().FindUserByUserName(username);

                    List<Note> audioList = DatabaseConnection.getInstance(CustomRecordActivity.this).noteDao().searchNoteAudio(search, u.getId());
                    adapter = new RecordAdapter(CustomRecordActivity.this, R.layout.custom_record, audioList);
                    listViewAudio.setAdapter(adapter);
                }
            }
        });
    }

    private void setDone() {
        txtDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomRecordActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}