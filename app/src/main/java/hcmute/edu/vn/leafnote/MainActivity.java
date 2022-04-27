package hcmute.edu.vn.leafnote;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import hcmute.edu.vn.leafnote.database.DatabaseConnection;
import hcmute.edu.vn.leafnote.entity.Note;

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
    TextView setting, txtTitle, txtDate,txtTitle2,txtDate2, txtSoLuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        addControl();
        List<Note> listNote= DatabaseConnection.getInstance(this).noteDao().getLastRow();
        if(listNote.isEmpty())
        {
            txtTitle.setText("Chưa có nội dung");
            txtDate.setText("");
            txtTitle2.setText("Chưa có nội dung");
            txtDate2.setText("");
            txtSoLuong.setText("Chưa có ghi chú");
        }
        else if(listNote.size()<2){
            txtTitle.setText(listNote.get(0).getTitle());
            txtDate.setText(listNote.get(0).getCreated_at());
            txtTitle2.setText("Chưa có nội dung");
            txtDate2.setText("");
            txtSoLuong.setText("("+listNote.size()+")");
        }
        else
        {
            txtTitle.setText(listNote.get(0).getTitle());
            txtDate.setText(listNote.get(0).getCreated_at());
            txtTitle2.setText(listNote.get(1).getTitle());
            txtDate2.setText(listNote.get(1).getCreated_at());
            txtSoLuong.setText("("+listNote.size()+")");
        }
        setRealTime();
        setDay();

        //Open search
        setOpenSearch();

        //Bottom Sheet
        openBottomSheet();

        //Open new note
        setOpenSubNote();

        //Open deleted
        setOpenDeleted();

        //Open allNotes
        setOpenAllNotes();

        //Open setting
        setOpenSetting();


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
        deleted = (TextView) findViewById(R.id.openDeleted);

        //Open allNotes
        allNotes = (LinearLayout) findViewById(R.id.allNotes);

        //Open Setting
        setting = (TextView) findViewById(R.id.txtSetting);
        txtTitle=(TextView) findViewById(R.id.txtTitle);
        txtDate=(TextView) findViewById(R.id.txtDate);
        txtTitle2=(TextView) findViewById(R.id.txtTitle2);
        txtDate2=(TextView) findViewById(R.id.txtDate2);
        txtSoLuong=(TextView)findViewById(R.id.txtSoLuong);
    }

    //Mở cài đặt
    private void setOpenSetting(){
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

    //Open thùng rác
    private void setOpenDeleted() {
        deleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openDeleted = new Intent(MainActivity.this, DeleteActivity.class);
                startActivity(openDeleted);
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
                                (LinearLayout)findViewById(R.id.bottomSheetContainer)
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
                        Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivity(openCamera);
                        bottomSheetDialog.dismiss();
                    }
                });

                //Sự kiện quay phim
                bottomSheetView.findViewById(R.id.createVideo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent openVideo = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                        startActivity(openVideo);
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

                //Sự kiện mở đính kèm
                bottomSheetView.findViewById(R.id.createAttach).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent openFolder = new Intent(Intent.ACTION_PICK);
                        startActivity(openFolder);
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