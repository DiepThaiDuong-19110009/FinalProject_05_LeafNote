package hcmute.edu.vn.leafnote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import hcmute.edu.vn.leafnote.R;
import hcmute.edu.vn.leafnote.database.DatabaseConnection;
import hcmute.edu.vn.leafnote.entity.Users;

public class AccountActivity extends AppCompatActivity {

    TextView txtBackSetting, txtTaiKhoan, txtHoTen, txtEmail, txtMatKhau;
    SharedPreferences pref;
    Button btnDoimatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        addControl();
        setInfo();
        changePassword();
        BackSetting();

    }

    private void addControl() {
        txtBackSetting = (TextView) findViewById(R.id.txtBackSetting);
        txtHoTen = (TextView) findViewById(R.id.txtHoTen);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtTaiKhoan = (TextView) findViewById(R.id.txtTaiKhoan);
        txtMatKhau = (TextView) findViewById(R.id.txtMatKhau);
        btnDoimatKhau = (Button) findViewById(R.id.btnDoimatKhau);
    }

    public void BackSetting() {
        txtBackSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setInfo() {
        pref = getSharedPreferences("login", MODE_PRIVATE);
        String username = pref.getString("username", "");
        Users u = DatabaseConnection.getInstance(AccountActivity.this).userDao().FindUserByUserName(username);
        txtHoTen.setText(u.getName().toString());
        txtEmail.setText(u.getEmail().toString());
        txtTaiKhoan.setText(u.getUsername().toString());
        txtMatKhau.setText(u.getPassword().toString());

    }

    private void changePassword() {
        btnDoimatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}