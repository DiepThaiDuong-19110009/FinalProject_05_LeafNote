package hcmute.edu.vn.leafnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import hcmute.edu.vn.leafnote.database.DatabaseConnection;
import hcmute.edu.vn.leafnote.entity.Note;
import hcmute.edu.vn.leafnote.entity.Users;

public class RecordActivity extends AppCompatActivity {
    private Button start, stop,  btnSaveAudio;
    private MediaRecorder myAudioRecorder;
    private MediaPlayer mediaPlayer;
    private Chronometer timer;
    TextView txtThoat;
    EditText edtTitleAudio;
    private String outputFile;
    private static final int PERMISSION_CODE = 111;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        AnhXa();
        stop.setEnabled(false);
        btnSaveAudio.setEnabled(false);
        outputFile=getOutputFile();
        startRecord();
        stopRecord();

        save();
        exitRecord();
    }

    private void AnhXa() {
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);

        btnSaveAudio = (Button) findViewById(R.id.btnSaveAudio);
        edtTitleAudio = (EditText) findViewById(R.id.edtTitleAudio);
        txtThoat = (TextView) findViewById(R.id.txtThoat);
        timer = (Chronometer) findViewById(R.id.record_timer);
    }

    private String getOutputFile(){
        File dir = new File(Environment.getExternalStorageDirectory(), "SaveAudio");

        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(dir, System.currentTimeMillis() + ".mp3");
        return file.toString();
    }

    private void startRecord() {

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    myAudioRecorder = new MediaRecorder();
                    myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    myAudioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    myAudioRecorder.setOutputFile(outputFile);
                }
                timer.setBase(SystemClock.elapsedRealtime());
                timer.start();
                try {
                    myAudioRecorder.prepare();

                } catch (IllegalStateException ise) {
                    // make something ...
                } catch (IOException ioe) {
                    // make something
                }
                myAudioRecorder.start();
                start.setEnabled(false);
                stop.setEnabled(true);
                btnSaveAudio.setEnabled(true);
            }
        });

    }

    public void stopRecord() {
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.stop();
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder = null;
                start.setEnabled(true);
                stop.setEnabled(false);
                btnSaveAudio.setEnabled(true);
            }
        });


    }


    public void save(){
        btnSaveAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=edtTitleAudio.getText().toString().trim();
                if(title.isEmpty()){
                    Toast.makeText(RecordActivity.this, "Vui lòng điền tiêu đề cho note", Toast.LENGTH_SHORT).show();
                    return;
                }
                pref = getSharedPreferences("login", MODE_PRIVATE);
                timer.stop();
                String username = pref.getString("username", "");

                Users u = DatabaseConnection.getInstance(RecordActivity.this).userDao().FindUserByUserName(username);

                Date date =new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy", Locale.US);
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss", Locale.US);

                Note note= new Note(u.getId(),3,title,outputFile,dateFormat.format(date), timeFormat.format(date), false);
                DatabaseConnection.getInstance(RecordActivity.this).noteDao().insert(note);
                Toast.makeText(RecordActivity.this, "Lưu bản record thành công", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(RecordActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void exitRecord(){
        txtThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(RecordActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean checkPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSION_CODE);
            return false;
        }
    }


}