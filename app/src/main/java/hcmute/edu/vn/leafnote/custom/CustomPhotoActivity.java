package hcmute.edu.vn.leafnote.custom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hcmute.edu.vn.leafnote.activity.MainActivity;
import hcmute.edu.vn.leafnote.adapter.PhotoAdapter;
import hcmute.edu.vn.leafnote.R;
import hcmute.edu.vn.leafnote.database.DatabaseConnection;
import hcmute.edu.vn.leafnote.entity.Note;
import hcmute.edu.vn.leafnote.entity.Users;

public class CustomPhotoActivity extends AppCompatActivity {
    ListView listView;
    PhotoAdapter adapter;
    EditText edtTimKiemPhoto;
    TextView txtDonePhoto;
    Button btnTim;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_photo);

        AnhXa();

        displayPhoto();
        searchNotePhoto();
        setDone();
    }

    private void displayPhoto() {
        pref = getSharedPreferences("login", MODE_PRIVATE);

        String username = pref.getString("username", "");

        Users u = DatabaseConnection.getInstance(this).userDao().FindUserByUserName(username);// lấy user hiện tại
        // lấy danh sách note dạng photo của user hiện tại
        List<Note> photolist = DatabaseConnection.getInstance(this).noteDao().getAllPhto(u.getId());
        adapter = new PhotoAdapter(this, R.layout.custom_photo, photolist);
        listView.setAdapter(adapter);
    }

    private void setDone() {
        txtDonePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomPhotoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void searchNotePhoto() {
        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = edtTimKiemPhoto.getText().toString().trim();
                if (TextUtils.isEmpty(search)) {
                    Toast.makeText(CustomPhotoActivity.this, "Vui lòng nhập từ khóa tìm kiếm", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.equals(search, "all")) {
                    displayPhoto();
                } else {
                    pref = getSharedPreferences("login", MODE_PRIVATE);// lấy share reference

                    String username = pref.getString("username", "");

                    Users u = DatabaseConnection.getInstance(CustomPhotoActivity.this).userDao().FindUserByUserName(username);// lấy user hiện tại
                    // lấy danh sách note dạng photo của user hiện tại theo từ khóa tìm kiếm
                    List<Note> photolist = DatabaseConnection.getInstance(CustomPhotoActivity.this).noteDao().searchNotePhoto(search, u.getId());
                    adapter = new PhotoAdapter(CustomPhotoActivity.this, R.layout.custom_photo, photolist);
                    listView.setAdapter(adapter);
                }
            }
        });
    }

    private void AnhXa() {
        listView = (ListView) findViewById(R.id.listViewImage);
        edtTimKiemPhoto = (EditText) findViewById(R.id.edtTimKiemPhoto);
        txtDonePhoto = (TextView) findViewById(R.id.txtDonePhoto);
        btnTim = (Button) findViewById(R.id.btnTimPhoto);
    }
}