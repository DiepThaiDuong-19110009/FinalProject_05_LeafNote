package hcmute.edu.vn.leafnote.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import hcmute.edu.vn.leafnote.R;
import hcmute.edu.vn.leafnote.custom.CustomActivity;
import hcmute.edu.vn.leafnote.custom.CustomPhotoActivity;
import hcmute.edu.vn.leafnote.custom.CustomRecordActivity;
import hcmute.edu.vn.leafnote.database.DatabaseConnection;
import hcmute.edu.vn.leafnote.entity.Note;
import hcmute.edu.vn.leafnote.entity.Users;

public class MainActivity extends AppCompatActivity {
    //Open Search
    TextView search;

    //Open sub note
    TextView subNote;

    //Open Deleted
    TextView deleted;

    //Chọn ảnh
    ImageView imageView;
    int imagevalue;

    //Bắt đầu khai báo Button Bottom Sheet
    Button openBottomSheet;

    //Khai báo các thành phần khác giao diện chính
    TextView titleHeader, setDay;
    LinearLayout customHeader;

    //Xem tất cả note
    LinearLayout allNotes;

    //Mở cài đặt
    TextView setting, txtTitle, txtDate, txtTitle2, txtDate2,
            txtSoLuong, txtDanhSachGhiChu, txtDanhSachPhoto,txtDanhSachGhiAm;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean finish= getIntent().getBooleanExtra("finish",false);
        if(finish){
            Toast.makeText(this,"Đăng xuất thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            finish();
            return;
        }
        setContentView(R.layout.activity_main);

        //Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        addControl();

        setDanhSach();
        setRealTime();
        setDay();

        //Open search
        setOpenSearch();

        //Bottom Sheet
        openBottomSheet();

        //Open new note
        setOpenSubNote();

        //Open allNotes
        setOpenAllNotes();

        //Open setting
        setOpenSetting();

        setClickDanhSach();

        //Nhận ảnh
        // initialise the layout
        imageView = findViewById(R.id.backGroundHeader);
        // check if any value sent from previous activity
        Bundle bundle = getIntent().getExtras();
        // if bundle is not null then get the image value
        if (bundle != null) {
            imagevalue = bundle.getInt("image");
        }
        imageView.setImageResource(imagevalue);


        //Control Header
        customHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent custom = new Intent(MainActivity.this, CustomActivity.class);
                startActivity(custom);
            }
        });

    }

    private void setDanhSach() {
        pref = getSharedPreferences("login", MODE_PRIVATE);

        String username = pref.getString("username", "");

        Users u = DatabaseConnection.getInstance(this).userDao().FindUserByUserName(username);

        List<Note> listNote = DatabaseConnection.getInstance(this).noteDao().getLastRow(u.getId());
        if (listNote.isEmpty()) {
            txtTitle.setText("Chưa có nội dung");
            txtDate.setText("");
            txtTitle2.setText("Chưa có nội dung");
            txtDate2.setText("");
            txtSoLuong.setText("(0)");
        } else if (listNote.size() < 2) {
            txtTitle.setText(listNote.get(0).getTitle());
            txtDate.setText(listNote.get(0).getCreated_date());
            txtTitle2.setText("Chưa có nội dung");
            txtDate2.setText("");
            txtSoLuong.setText("(" + listNote.size() + ")");
        } else {
            txtTitle.setText(listNote.get(0).getTitle());
            txtDate.setText(listNote.get(0).getCreated_date());
            txtTitle2.setText(listNote.get(1).getTitle());
            txtDate2.setText(listNote.get(1).getCreated_date());
            txtSoLuong.setText("(" + listNote.size() + ")");
        }
    }

    private void setClickDanhSach() {
        txtDanhSachGhiChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allNotes = new Intent(MainActivity.this, ShowAllNotesActivity.class);
                startActivity(allNotes);
            }
        });
        txtDanhSachPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, CustomPhotoActivity.class);
                startActivity(intent);
            }
        });
        txtDanhSachGhiAm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, CustomRecordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControl() {
        //Start Header
        customHeader = (LinearLayout) findViewById(R.id.CustomHeader);
        titleHeader = (TextView) findViewById(R.id.txtTitleHeader);
        setDay = (TextView) findViewById(R.id.txtSetDay);

        //Open search
        search = (TextView) findViewById(R.id.openSearch);

        //Start Bottom Sheet
        openBottomSheet = (Button) findViewById(R.id.openBottomSheet);

        //Open sub note
        subNote = (TextView) findViewById(R.id.txtSubNewNote);

        //Open deleted


        //Open allNotes
        allNotes = (LinearLayout) findViewById(R.id.allNotes);

        //Open Setting
        setting = (TextView) findViewById(R.id.txtSetting);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtDate = (TextView) findViewById(R.id.txtDate);
        txtTitle2 = (TextView) findViewById(R.id.txtTitle2);
        txtDate2 = (TextView) findViewById(R.id.txtDate2);
        txtSoLuong = (TextView) findViewById(R.id.txtSoLuong);
        txtDanhSachGhiChu = (TextView) findViewById(R.id.txtDanhSachGhiChu);
        txtDanhSachPhoto = (TextView) findViewById(R.id.txtDanhSachPhoto);
        txtDanhSachGhiAm=(TextView) findViewById(R.id.txtDanhSachGhiAm);
    }

    //Mở cài đặt
    private void setOpenSetting() {
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setting = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(setting);
            }
        });
    }

    //Mở new note trên Phần ghi chú
    private void setOpenSubNote() {
        subNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent subNote = new Intent(MainActivity.this, NoteActivity.class);
                startActivity(subNote);
            }
        });
    }


    //Open Search
    public void setOpenSearch() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openSearch = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(openSearch);
            }
        });
    }

    //Open allNotes
    private void setOpenAllNotes() {
        allNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allNotes = new Intent(MainActivity.this, ShowAllNotesActivity.class);
                startActivity(allNotes);
            }
        });

    }

    //Cài đặt Bottom Sheet
    public void openBottomSheet() {
        //Mở bottomsheet
        openBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        MainActivity.this, R.style.BottomSheetDialogTheme
                );

                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.layout_bottom_sheet,
                                (LinearLayout) findViewById(R.id.bottomSheetContainer)
                        );


                //Sự kiện mở ghi chú mới
                bottomSheetView.findViewById(R.id.createNewNote).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent createNewNote = new Intent(MainActivity.this, NoteActivity.class);
                        startActivity(createNewNote);
                        bottomSheetDialog.dismiss();
                    }
                });

                //Sự kiện chụp ảnh
                bottomSheetView.findViewById(R.id.createCamera).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, PhotoActivity.class);
                        startActivity(intent);
                        bottomSheetDialog.dismiss();
                    }
                });

                //Sự kiện mở phát thảo
                bottomSheetView.findViewById(R.id.createDraw).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent openDraw = new Intent(MainActivity.this, DrawActivity.class);
                        startActivity(openDraw);
                        bottomSheetDialog.dismiss();
                    }
                });

                //Sự kiện mở ghi âm
                bottomSheetView.findViewById(R.id.createRecord).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent openRecord = new Intent(MainActivity.this, RecordActivity.class);
                        startActivity(openRecord);
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
    }


    //Cài đặt ngày Header
    public void setRealTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("H");
        int hour = Integer.parseInt(sdf.format(c.getTime()));
        if (hour < 10 && hour >= 0) {
            titleHeader.setText("Xin chào buổi sáng");
        } else if (hour >= 10 && hour <= 18) {
            titleHeader.setText("Chúc buổi trưa vui vẻ");
        } else if (hour > 18 && hour <= 23) {
            titleHeader.setText("Buổi tối tốt lành");
        }
    }

    public void setDay() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat day = new SimpleDateFormat("d");
        SimpleDateFormat month = new SimpleDateFormat("M");
        SimpleDateFormat year = new SimpleDateFormat("y");
        int Day = Integer.parseInt(day.format(c.getTime()));
        int Month = Integer.parseInt(month.format(c.getTime()));
        int Year = Integer.parseInt(year.format(c.getTime()));
        setDay.setText("Ngày " + Day + " tháng " + Month + " năm " + Year);
    }
}