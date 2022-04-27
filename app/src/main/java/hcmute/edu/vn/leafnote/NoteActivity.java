package hcmute.edu.vn.leafnote;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

import hcmute.edu.vn.leafnote.database.DatabaseConnection;
import hcmute.edu.vn.leafnote.entity.Note;
import hcmute.edu.vn.leafnote.entity.Users;

public class NoteActivity extends AppCompatActivity {

    TextView saveNote;
    EditText edtTitle, edtContent;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        addControl();
        setSaveNote();
    }

    private void addControl() {

        saveNote = (TextView) findViewById(R.id.txtSaveNote);
        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtContent = (EditText) findViewById(R.id.edtContent);
    }

    public void setSaveNote() {
        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote();
            }
        });
    }

    private void addNote() {
        String title = edtTitle.getText().toString().trim();
        String content = edtContent.getText().toString().trim();

        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "Vui lòng nhập tiêu đề", Toast.LENGTH_SHORT).show();
            return;
        }
        pref = getSharedPreferences("login", MODE_PRIVATE);

        String username = pref.getString("username", "");

        Users u = DatabaseConnection.getInstance(this).userDao().FindUserByUserName(username);

        //Date date =new Date();
        Note note = new Note(u.getId(), title, content, "", "", false);
        DatabaseConnection.getInstance(this).noteDao().insert(note);
        Toast.makeText(this, "Ghi chú thành công", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(NoteActivity.this, MainActivity.class);
        startActivity(intent);
        edtTitle.setText("");
        edtContent.setText("");

    }
}