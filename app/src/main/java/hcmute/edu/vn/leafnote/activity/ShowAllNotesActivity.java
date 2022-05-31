package hcmute.edu.vn.leafnote.activity;

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

import hcmute.edu.vn.leafnote.R;
import hcmute.edu.vn.leafnote.adapter.NoteAdapter;
import hcmute.edu.vn.leafnote.database.DatabaseConnection;
import hcmute.edu.vn.leafnote.entity.Note;
import hcmute.edu.vn.leafnote.entity.Users;

public class ShowAllNotesActivity extends AppCompatActivity {

    private ListView lvShowAllNote;
    TextView txtDone;
    EditText edtTimKiem;
    Button btnTim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_notes);
        AnhXa();
        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);

        String username = pref.getString("username", "");

        Users u = DatabaseConnection.getInstance(this).userDao().FindUserByUserName(username);

        List<Note> noteList = DatabaseConnection.getInstance(this).noteDao().getAllText(u.getId());
        NoteAdapter adapter = new NoteAdapter(ShowAllNotesActivity.this, R.layout.custom_note, noteList);
        lvShowAllNote.setAdapter(adapter);

        txtDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowAllNotesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = edtTimKiem.getText().toString().trim();
                if (TextUtils.isEmpty(search)) {
                    Toast.makeText(ShowAllNotesActivity.this, "Vui lòng nhập từ khóa tìm kiếm", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.equals(search, "all")) {
                    lvShowAllNote.setAdapter(adapter);
                } else {
                    List<Note> listSearch = DatabaseConnection.getInstance(ShowAllNotesActivity.this).noteDao().searchNote(search,u.getId());
                    NoteAdapter adapter = new NoteAdapter(ShowAllNotesActivity.this, R.layout.custom_note, listSearch);
                    lvShowAllNote.setAdapter(adapter);
                }
            }
        });
    }

    private void AnhXa() {
        txtDone = (TextView) findViewById(R.id.txtDone);
        edtTimKiem = (EditText) findViewById(R.id.edtTimKiem);
        btnTim = (Button) findViewById(R.id.btnTim);
        lvShowAllNote = (ListView) findViewById(R.id.lvShowAllNote);
    }
}