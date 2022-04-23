package hcmute.edu.vn.leafnote.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.leafnote.R;
import hcmute.edu.vn.leafnote.entity.Note;
import hcmute.edu.vn.leafnote.model.ContactRowNote;

public class CustomRowNoteAdapter extends ArrayAdapter<Note> {

    private Context context;
    private int resource;
    private List<Note> arrContactRowNote;

    public CustomRowNoteAdapter(@NonNull Context context, int resource, @NonNull List<Note> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrContactRowNote = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.row_text_note, parent, false);

        TextView txtTittle = (TextView) convertView.findViewById(R.id.txtTittleRowNote);
        TextView txtContent = (TextView) convertView.findViewById(R.id.txtContentRowNote);
        TextView txtDate = (TextView) convertView.findViewById(R.id.txtDateRowNote);

        Note contactRowNote = arrContactRowNote.get(position);

        txtTittle.setText(contactRowNote.getTitle());
        txtContent.setText(contactRowNote.getContent());
        txtDate.setText(contactRowNote.getCreated_at());

        return convertView;
    }
}
