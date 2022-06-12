package hcmute.edu.vn.leafnote.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import hcmute.edu.vn.leafnote.R;
import hcmute.edu.vn.leafnote.database.DatabaseConnection;
import hcmute.edu.vn.leafnote.entity.Note;
import hcmute.edu.vn.leafnote.entity.Users;

public class PhotoActivity extends AppCompatActivity {

    private static int REQUEST_CODE = 100;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imgView;
    Button btnSave, btnPhoto;
    OutputStream outputStream;
    EditText edtViewPhoto;
    TextView txtThoatPhoto;
    List<String> mlist;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        AnhXa();
        btnSave.setEnabled(false);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(PhotoActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    saveImage();
                } else {
                    askPermission();
                }
            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                } catch (ActivityNotFoundException e) {
                    // display error state to the user
                }
            }
        });
        setExitPhoto();

    }

    private void AnhXa() {
        imgView = (ImageView) findViewById(R.id.imageViewPhoto);
        btnSave = (Button) findViewById(R.id.buttonSave);
        btnPhoto = (Button) findViewById(R.id.btnPhoto);
        edtViewPhoto = (EditText) findViewById(R.id.edtTitlePhoto);
        mlist = new ArrayList<>();
        txtThoatPhoto = (TextView) findViewById(R.id.txtThoatPhoto);
    }

    private void setExitPhoto() {
        txtThoatPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhotoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgView.setImageBitmap(imageBitmap);
            btnSave.setEnabled(true);
        }
    }

    // yêu cầu quyền chụp ảnh
    private void askPermission() {
        ActivityCompat.requestPermissions(PhotoActivity.this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveImage();
            } else {
                Toast.makeText(this, "Vui lòng cho phép ứng dụng quyền ghi âm", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveImage() {
        String title = edtViewPhoto.getText().toString().trim();// lấy title
        if (title.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tiêu đề", Toast.LENGTH_SHORT).show();
            return;
        }

        File dir = new File(Environment.getExternalStorageDirectory(), "SaveImage");// tạo thư mục

        if (!dir.exists()) {
            dir.mkdir();
        }

        BitmapDrawable drawable = (BitmapDrawable) imgView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        File file = new File(dir, System.currentTimeMillis() + ".jpg");
        try {
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        pref = getSharedPreferences("login", MODE_PRIVATE);// lấy share reference login

        String username = pref.getString("username", "");

        Users u = DatabaseConnection.getInstance(this).userDao().FindUserByUserName(username);

        Date date = new Date();// tạo date ghi chú
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy", Locale.US);
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss", Locale.US);
        // tạo note dạng photo
        Note note = new Note(u.getId(), 2, title, file.toString(), dateFormat.format(date), timeFormat.format(date), false);
        DatabaseConnection.getInstance(this).noteDao().insert(note);// lưu note xuống databse
        Toast.makeText(this, "Lưu ảnh thành công", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PhotoActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        try {
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}