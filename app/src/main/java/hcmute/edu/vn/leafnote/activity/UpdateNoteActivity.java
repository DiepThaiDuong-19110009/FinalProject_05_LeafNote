package hcmute.edu.vn.leafnote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hcmute.edu.vn.leafnote.R;
import hcmute.edu.vn.leafnote.database.DatabaseConnection;
import hcmute.edu.vn.leafnote.entity.Note;

public class UpdateNoteActivity extends AppCompatActivity {
    TextView saveNote;
    EditText edtTitle, edtContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        addControl();
        Intent intent=getIntent();
        Note note= (Note) intent.getSerializableExtra("note_key");
        edtTitle.setText(note.getTitle());
        edtContent.setText(note.getContent());
        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                note.setTitle(edtTitle.getText().toString().trim());
                note.setContent(edtContent.getText().toString().trim());
                DatabaseConnection.getInstance(UpdateNoteActivity.this).noteDao().update(note);
                Toast.makeText(UpdateNoteActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(UpdateNoteActivity.this,ShowAllNotesActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControl() {

        saveNote = (TextView) findViewById(R.id.txtSave);
        edtTitle = (EditText) findViewById(R.id.edtUpdateTitle);
        edtContent = (EditText) findViewById(R.id.edtUpdateContent);
    }
    public void setSaveNote() {
        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}