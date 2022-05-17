package hcmute.edu.vn.leafnote.adapter;

import android.app.Activity;
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
        TextView txtAudio;
        Button btnPlay, btnStop;
        if (view == null) {
            view = activity.getLayoutInflater().inflate(layout, null);
            txtAudio = view.findViewById(R.id.txtAudio);
            btnPlay = view.findViewById(R.id.btnPlay);
            btnStop = view.findViewById(R.id.btnStop);
            view.setTag(R.id.btnPlay, btnPlay);
            view.setTag(R.id.txtAudio, txtAudio);
            view.setTag(R.id.btnStop);
        } else {
            btnPlay = (Button) view.getTag(R.id.btnPlay);
            txtAudio = (TextView) view.getTag(R.id.txtAudio);
            btnStop = (Button) view.getTag(R.id.btnStop);
        }
        Note audio = audioList.get(i);
        txtAudio.setText(audio.getTitle());

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = MediaPlayer.create(activity.getApplicationContext(), Uri.parse(audio.getContent()));

                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    Toast.makeText(activity, "Playing Audio", Toast.LENGTH_LONG).show();
                }

                btnStop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //  MediaPlayer mediaPlayer = MediaPlayer.create(activity.getApplicationContext(), Uri.parse(audio.getContent()));
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                            Toast.makeText(activity, "Stopping Audio", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        return view;
    }
}
