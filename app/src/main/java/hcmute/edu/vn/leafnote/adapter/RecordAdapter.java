package hcmute.edu.vn.leafnote.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import hcmute.edu.vn.leafnote.R;
import hcmute.edu.vn.leafnote.RecordActivity;
import hcmute.edu.vn.leafnote.ShowAllNotesActivity;
import hcmute.edu.vn.leafnote.custom.CustomRecordActivity;
import hcmute.edu.vn.leafnote.database.DatabaseConnection;
import hcmute.edu.vn.leafnote.entity.Note;

public class RecordAdapter extends BaseAdapter {

    private Activity activity;
    private int layout;
    List<Note> audioList;

    public RecordAdapter() {
    }

    public RecordAdapter(Activity activity, int layout, List<Note> audioList) {
        this.activity = activity;
        this.layout = layout;
        this.audioList = audioList;
    }


    @Override
    public int getCount() {
        return audioList.size();
    }

    @Override
    public Object getItem(int i) {
        return audioList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView txtAudio, txtDateAudio;
        Button btnPlay, btnStop, btnDeleteRecord;
        if (view == null) {
            view = activity.getLayoutInflater().inflate(layout, null);
            txtAudio = view.findViewById(R.id.txtAudio);
            btnPlay = view.findViewById(R.id.btnPlay);
            btnStop = view.findViewById(R.id.btnStop);
            txtDateAudio = view.findViewById(R.id.txtDateAudio);
            btnDeleteRecord = view.findViewById(R.id.btnDeleteRecord);
            view.setTag(R.id.btnPlay, btnPlay);
            view.setTag(R.id.txtAudio, txtAudio);
            view.setTag(R.id.btnStop, btnStop);
            view.setTag(R.id.txtDateAudio, txtDateAudio);
            view.setTag(R.id.btnDeleteRecord, btnDeleteRecord);
        } else {
            btnPlay = (Button) view.getTag(R.id.btnPlay);
            txtAudio = (TextView) view.getTag(R.id.txtAudio);
            btnStop = (Button) view.getTag(R.id.btnStop);
            txtDateAudio = (TextView) view.getTag(R.id.txtDateAudio);
            btnDeleteRecord = (Button) view.getTag(R.id.btnDeleteRecord);
        }
        Note audio = audioList.get(i);
        txtAudio.setText(audio.getTitle());
        txtDateAudio.setText(audio.getCreated_date() + " " + audio.getCreated_time());
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = MediaPlayer.create(activity.getApplicationContext(), Uri.parse(audio.getContent()));

                if (!mediaPlayer.isPlaying()) {
                    btnPlay.setEnabled(false);
                    btnStop.setEnabled(true);
                    mediaPlayer.start();
                    Toast.makeText(activity, "Phát audio", Toast.LENGTH_LONG).show();
                }

                btnStop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (mediaPlayer.isPlaying()) {
                            btnPlay.setEnabled(true);
                            btnStop.setEnabled(false);
                            mediaPlayer.stop();
                            Toast.makeText(activity, "Dừng audio", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        btnDeleteRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButtonDelete(audio);
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
                        Intent intent = new Intent(activity, CustomRecordActivity.class);
                        activity.startActivity(intent);
                        activity.finish();
                    }
                }).setNegativeButton("Không", null).show();
    }
}
