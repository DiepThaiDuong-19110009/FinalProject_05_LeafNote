package hcmute.edu.vn.leafnote;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hcmute.edu.vn.leafnote.database.DatabaseConnection;
import hcmute.edu.vn.leafnote.entity.Note;

public class ChangeBackgroundActivity extends AppCompatActivity {
    //Check Update
    boolean check1, check2, check3, check4, check5, check6, check7, check8;

    //Thoát khỏi thay đổi nền
    TextView closeChangeBackground, updateChangeBackground;

    //Chọn ảnh có sẵn
    ImageView bgHeader1, bgHeader2, bgHeader3, bgHeader4, bgHeader5, bgHeader6, bgHeader7, bgHeader8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_background);

        addControl();

        setCloseChangeBackground();

        //Chọn ảnh có sẵn
        selectbgHeader1();
        selectbgHeader2();
        selectbgHeader3();
        selectbgHeader4();
        selectbgHeader5();
        selectbgHeader6();
        selectbgHeader7();
        selectbgHeader8();

        //Cập nhật ảnh nền
        setUpdateChangeBackground();
    }
    //Cài đặt thoát khỏi thay đổi nền
    public void setCloseChangeBackground(){
        closeChangeBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //Tạo hiệu ứng chọn ảnh có sẵn
    //Chọn ảnh 1
    public void selectbgHeader1(){
        bgHeader1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bgHeader1.setBackground(getDrawable(R.drawable.select_image));
                bgHeader2.setBackground(null);
                bgHeader3.setBackground(null);
                bgHeader4.setBackground(null);
                bgHeader5.setBackground(null);
                bgHeader6.setBackground(null);
                bgHeader7.setBackground(null);
                bgHeader8.setBackground(null);
                check1 = true;
            }
        });
    }

    //Chọn ảnh 2
    public void selectbgHeader2(){
        bgHeader2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bgHeader2.setBackground(getDrawable(R.drawable.select_image));
                bgHeader1.setBackground(null);
                bgHeader3.setBackground(null);
                bgHeader4.setBackground(null);
                bgHeader5.setBackground(null);
                bgHeader6.setBackground(null);
                bgHeader7.setBackground(null);
                bgHeader8.setBackground(null);
                check2 = true;
            }
        });
    }

    //Chọn ảnh 3
    public void selectbgHeader3(){
        bgHeader3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bgHeader3.setBackground(getDrawable(R.drawable.select_image));
                bgHeader1.setBackground(null);
                bgHeader2.setBackground(null);
                bgHeader4.setBackground(null);
                bgHeader5.setBackground(null);
                bgHeader6.setBackground(null);
                bgHeader7.setBackground(null);
                bgHeader8.setBackground(null);
                check3 = true;
            }
        });
    }

    //Chọn ảnh 4
    public void selectbgHeader4(){
        bgHeader4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bgHeader4.setBackground(getDrawable(R.drawable.select_image));
                bgHeader1.setBackground(null);
                bgHeader2.setBackground(null);
                bgHeader3.setBackground(null);
                bgHeader5.setBackground(null);
                bgHeader6.setBackground(null);
                bgHeader7.setBackground(null);
                bgHeader8.setBackground(null);
                check4 = true;
            }
        });
    }

    //Chọn ảnh 5
    public void selectbgHeader5(){
        bgHeader5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bgHeader5.setBackground(getDrawable(R.drawable.select_image));
                bgHeader1.setBackground(null);
                bgHeader2.setBackground(null);
                bgHeader3.setBackground(null);
                bgHeader4.setBackground(null);
                bgHeader6.setBackground(null);
                bgHeader7.setBackground(null);
                bgHeader8.setBackground(null);
                check5 = true;
            }
        });
    }

    //Chọn ảnh 6
    public void selectbgHeader6(){
        bgHeader6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bgHeader6.setBackground(getDrawable(R.drawable.select_image));
                bgHeader1.setBackground(null);
                bgHeader2.setBackground(null);
                bgHeader3.setBackground(null);
                bgHeader4.setBackground(null);
                bgHeader5.setBackground(null);
                bgHeader7.setBackground(null);
                bgHeader8.setBackground(null);
                check6 = true;
            }
        });
    }

    //Chọn ảnh 7
    public void selectbgHeader7(){
        bgHeader7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bgHeader7.setBackground(getDrawable(R.drawable.select_image));
                bgHeader1.setBackground(null);
                bgHeader2.setBackground(null);
                bgHeader3.setBackground(null);
                bgHeader4.setBackground(null);
                bgHeader5.setBackground(null);
                bgHeader6.setBackground(null);
                bgHeader8.setBackground(null);
                check7 = true;
            }
        });
    }

    //Chọn ảnh 8
    public void selectbgHeader8(){
        bgHeader8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bgHeader8.setBackground(getDrawable(R.drawable.select_image));
                bgHeader1.setBackground(null);
                bgHeader2.setBackground(null);
                bgHeader3.setBackground(null);
                bgHeader4.setBackground(null);
                bgHeader5.setBackground(null);
                bgHeader6.setBackground(null);
                bgHeader7.setBackground(null);
                check8 = true;
            }
        });
    }

    //Cài đặt cập nhật ảnh nền
    public void setUpdateChangeBackground(){
        updateChangeBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check1){
                    Toast.makeText(ChangeBackgroundActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    Intent sendimage = new Intent(ChangeBackgroundActivity.this, MainActivity.class);
                    sendimage.putExtra("image", R.drawable.bgheader1);
                    startActivity(sendimage);
                }else if(check2){
                    Toast.makeText(ChangeBackgroundActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    Intent sendimage = new Intent(ChangeBackgroundActivity.this, MainActivity.class);
                    sendimage.putExtra("image", R.drawable.bgheader2);
                    startActivity(sendimage);
                }else if(check3){
                    Toast.makeText(ChangeBackgroundActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    Intent sendimage = new Intent(ChangeBackgroundActivity.this, MainActivity.class);
                    sendimage.putExtra("image", R.drawable.bgheader3);
                    startActivity(sendimage);
                }else if(check4){
                    Toast.makeText(ChangeBackgroundActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    Intent sendimage = new Intent(ChangeBackgroundActivity.this, MainActivity.class);
                    sendimage.putExtra("image", R.drawable.bgheader4);
                    startActivity(sendimage);
                }else if(check5){
                    Toast.makeText(ChangeBackgroundActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    Intent sendimage = new Intent(ChangeBackgroundActivity.this, MainActivity.class);
                    sendimage.putExtra("image", R.drawable.bgheader5);
                    startActivity(sendimage);
                }else if(check6){
                    Toast.makeText(ChangeBackgroundActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    Intent sendimage = new Intent(ChangeBackgroundActivity.this, MainActivity.class);
                    sendimage.putExtra("image", R.drawable.bgheader6);
                    startActivity(sendimage);
                }else if(check7){
                    Toast.makeText(ChangeBackgroundActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    Intent sendimage = new Intent(ChangeBackgroundActivity.this, MainActivity.class);
                    sendimage.putExtra("image", R.drawable.bgheader7);
                    startActivity(sendimage);
                }else if(check8){
                    Toast.makeText(ChangeBackgroundActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    Intent sendimage = new Intent(ChangeBackgroundActivity.this, MainActivity.class);
                    sendimage.putExtra("image", R.drawable.bgheader8);
                    startActivity(sendimage);
                }
            }
        });
    }

    //Thêm biến
    private void addControl() {
        closeChangeBackground = (TextView) findViewById(R.id.txtCloseChangeBackground);

        //Chọn ảnh có sẵn
        bgHeader1 = (ImageView) findViewById(R.id.bgHeader1);
        bgHeader2 = (ImageView) findViewById(R.id.bgHeader2);
        bgHeader3 = (ImageView) findViewById(R.id.bgHeader3);
        bgHeader4 = (ImageView) findViewById(R.id.bgHeader4);
        bgHeader5 = (ImageView) findViewById(R.id.bgHeader5);
        bgHeader6 = (ImageView) findViewById(R.id.bgHeader6);
        bgHeader7 = (ImageView) findViewById(R.id.bgHeader7);
        bgHeader8 = (ImageView) findViewById(R.id.bgHeader8);

        //Cập nhật ảnh nền
        updateChangeBackground = (TextView) findViewById(R.id.txtUpdateChangeBackground);
    }

}