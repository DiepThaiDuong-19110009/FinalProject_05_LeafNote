package hcmute.edu.vn.leafnote;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.airbnb.lottie.LottieAnimationView;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RecordActivity extends AppCompatActivity {

    private static final int REQUEST_AUDIO_PERMISSION_CODE = 101;
    MediaRecorder myAudioRecorder;
    MediaPlayer mediaPlayer;
    ImageView imgRecord;
    ImageView imgPlay;
    TextView txtTime;
    TextView txtRecordingPath;
    ImageView imgSimpleBg;
    boolean isRecording = false;
    boolean isPlaying = false;
    int seconds = 0;
    String path = null;
    LottieAnimationView lavPlaying;
    int dummySeconds = 0;
    int playableSeconds = 0;
    Handler handler;

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        imgRecord = findViewById(R.id.ib_record);
        imgPlay = findViewById(R.id.ib_play);
        txtTime = findViewById(R.id.tv_time);
        txtRecordingPath = findViewById(R.id.tv_recording_path);
        imgSimpleBg = findViewById(R.id.iv_simple_bg);
        lavPlaying = findViewById(R.id.lav_playing);
        //mediaPlayer = new MediaPlayer();

    }
    private void startRecord(MediaRecorder myRecorder, String outputFile) {
        myRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        myRecorder.setOutputFile(outputFile);
        try {
            myRecorder.prepare();

        } catch (IllegalStateException ise) {
            // make something ...
        } catch (IOException ioe) {
            // make something
        }
        myRecorder.start();
        //imgPlay.setEnabled(false);
        //stop.setEnabled(true);
        Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG)
                .show();
    }
    public void stopRecord(MediaRecorder myRecorder){
        myRecorder.stop();
        myRecorder.release();
        myRecorder = null;
        //start.setEnabled(true);
        //stop.setEnabled(false);
        //play.setEnabled(true);
        Toast.makeText(getApplicationContext(), "Audio Recorder successfully",
                Toast.LENGTH_LONG).show();
    }

    private void runTimer() {
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(), "%02d:%02d", minutes, secs);
                txtTime.setText(time);

                if (isRecording || (isPlaying && playableSeconds != -1)) {
                    seconds++;
                    playableSeconds--;

                    if(playableSeconds == -1 && isPlaying){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        isPlaying=false;
                        mediaPlayer = null;
                        mediaPlayer = new MediaPlayer();
                        playableSeconds = dummySeconds;
                        seconds = 0;
                        handler.removeCallbacksAndMessages(null);
                        imgSimpleBg.setVisibility(View.VISIBLE);
                        imgSimpleBg.setVisibility(View.GONE);
                        imgPlay.setImageDrawable(ContextCompat.getDrawable(RecordActivity.this, R.drawable.recording_play));
                        return;
                    }
                }

                handler.postDelayed(this, 1000);
            }
        });
    }

    private void requestRecordingPermission() {
        ActivityCompat.requestPermissions(RecordActivity.this,
                new String[]{Manifest.permission.RECORD_AUDIO},
                REQUEST_AUDIO_PERMISSION_CODE);
    }

    public boolean checkRecordingPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
            requestRecordingPermission();
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_AUDIO_PERMISSION_CODE) {
            if (grantResults.length > 1) {
                boolean permissonToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (permissonToRecord) {
                    Toast.makeText(getApplicationContext(), "Permission given", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private String getRecordingFilePath() {
      return this.getExternalFilesDir("/").getAbsolutePath()+"/recording.3gp";
    }
}