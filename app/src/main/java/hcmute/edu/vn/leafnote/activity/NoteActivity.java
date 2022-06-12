package hcmute.edu.vn.leafnote.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import hcmute.edu.vn.leafnote.R;
import hcmute.edu.vn.leafnote.database.DatabaseConnection;
import hcmute.edu.vn.leafnote.entity.Note;
import hcmute.edu.vn.leafnote.entity.Users;

public class NoteActivity extends AppCompatActivity {

    TextView saveNote,cancel;
    EditText edtTitle, edtContent;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        addControl();
        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControl() {

        saveNote = (TextView) findViewById(R.id.txtSaveNote);
        cancel=(TextView)findViewById(R.id.txtCancel);
        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtContent = (EditText) findViewById(R.id.edtContent);
    }

    private void addNote() {
        String title = edtTitle.getText().toString().trim(); // lấy title
        String content = edtContent.getText().toString().trim();// lấy content

        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "Vui lòng nhập tiêu đề", Toast.LENGTH_SHORT).show();
            return;
        }
        pref = getSharedPreferences("login", MODE_PRIVATE); // lấy share reference

        String username = pref.getString("username", "");

        Users u = DatabaseConnection.getInstance(this).userDao().FindUserByUserName(username);

        Date date =new Date(); // tạo date ghi chú
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy", Locale.US);
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss", Locale.US);
        // tạo ghi chú
        Note note = new Note(u.getId(),1, title, content, dateFormat.format(date), timeFormat.format(date), false);
        DatabaseConnection.getInstance(this).noteDao().insert(note);// lưu ghi chú xuống database
        Toast.makeText(this, "Ghi chú thành công", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(NoteActivity.this, MainActivity.class);
        startActivity(intent);
        edtTitle.setText("");
        edtContent.setText("");

    }
}