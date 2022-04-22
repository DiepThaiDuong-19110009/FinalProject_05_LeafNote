package hcmute.edu.vn.leafnote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import hcmute.edu.vn.leafnote.adapter.CustomRowNoteAdapter;
import hcmute.edu.vn.leafnote.model.ContactRowNote;

public class ShowAllNotesActivity extends AppCompatActivity {

    private ListView lvShowAllNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_notes);

        lvShowAllNote = (ListView) findViewById(R.id.lvShowAllNote);

        ArrayList<ContactRowNote> arrayList = new ArrayList<>();

        ContactRowNote contactRowNote1 = new ContactRowNote("Bài giảng", "Hôm nay làm bài tập", "22/04/2022");
        ContactRowNote contactRowNote2 = new ContactRowNote("Học Android", "ListView", "22/04/2022");

        arrayList.add(contactRowNote1);
        arrayList.add(contactRowNote2);
        arrayList.add(contactRowNote2);
        arrayList.add(contactRowNote2);
        arrayList.add(contactRowNote2);
        arrayList.add(contactRowNote2);

        CustomRowNoteAdapter customRowNoteAdapter =new CustomRowNoteAdapter(this, R.layout.row_text_note, arrayList);

        lvShowAllNote.setAdapter(customRowNoteAdapter);
    }
}