package hcmute.edu.vn.leafnote.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hcmute.edu.vn.leafnote.R;
import hcmute.edu.vn.leafnote.ShowAllNotesActivity;
import hcmute.edu.vn.leafnote.UpdateNoteActivity;
import hcmute.edu.vn.leafnote.database.DatabaseConnection;
import hcmute.edu.vn.leafnote.entity.Note;

public class NoteAdapter extends BaseAdapter {

    private Activity activity;
    private int layout;
    List<Note> noteList;

    public NoteAdapter() {
    }

    public NoteAdapter(Activity activity, int layout, List<Note> noteList) {
        this.activity = activity;
        this.layout = layout;
        this.noteList = noteList;
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int i) {
        return noteList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        EditText edtTitle, edtContent;
        TextView txtDate;
        Button btnCapNhat, btnXoa;
        if (view == null) {
            view = activity.getLayoutInflater().inflate(layout, null);
            edtTitle = view.findViewById(R.id.edtTitleRowNote);
            edtContent = view.findViewById(R.id.edtContentRowNote);
            txtDate = view.findViewById(R.id.txtDateRowNote);
            btnXoa = view.findViewById(R.id.btnXoa);
            btnCapNhat = view.findViewById(R.id.btnCapNhat);
            view.setTag(R.id.edtTitleRowNote, edtTitle);
            view.setTag(R.id.edtContentRowNote, edtContent);
            view.setTag(R.id.txtDateRowNote, txtDate);
            view.setTag(R.id.btnXoa, btnXoa);
            view.setTag(R.id.btnCapNhat, btnCapNhat);

        } else {
            edtTitle = (EditText) view.getTag(R.id.edtTitleRowNote);
            edtContent = (EditText) view.getTag(R.id.edtContentRowNote);
            txtDate = (TextView) view.getTag(R.id.txtDateRowNote);
            btnXoa = (Button) view.getTag(R.id.btnXoa);
            btnCapNhat = (Button) view.getTag(R.id.btnCapNhat);
        }
        Note note = noteList.get(position);
        edtTitle.setText(note.getTitle());
        edtContent.setText(note.getContent());
        txtDate.setText(note.getCreated_date()+ " "+note.getCreated_time());
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, UpdateNoteActivity.class);
                intent.putExtra("note_key", note);
                activity.startActivityForResult(intent, 100);
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButtonDelete(note);
            }
        });
        return view;
    }

    private void clickButtonDelete(Note note) {

        new AlertDialog.Builder(activity)
                .setTitle("Xác nhận xóa ghi chú")
                .setMessage("Bạn có muốn xóa ghi chú")
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseConnection.getInstance(activity).noteDao().delete(note);
                        Toast.makeText(activity, "Xóa ghi chú thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity, ShowAllNotesActivity.class);
                        activity.startActivity(intent);
                        activity.finish();
                    }
                }).setNegativeButton("Không", null).show();
    }
}
