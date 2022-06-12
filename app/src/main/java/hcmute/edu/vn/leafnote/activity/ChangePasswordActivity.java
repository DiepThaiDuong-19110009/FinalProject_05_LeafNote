package hcmute.edu.vn.leafnote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hcmute.edu.vn.leafnote.R;
import hcmute.edu.vn.leafnote.database.DatabaseConnection;
import hcmute.edu.vn.leafnote.entity.Users;

public class ChangePasswordActivity extends AppCompatActivity {

    TextView txtBackSetting;
    EditText edtOldPassword, edtNewPassword, edtNewPasswordAgain;
    Button btnChangePassword;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        addControl();
        setChangePassword();
        BackSetting();
    }

    private void addControl() {
        txtBackSetting = (TextView) findViewById(R.id.txtChangePasswordBackSetting);
        edtOldPassword = (EditText) findViewById(R.id.edtOldPassword);
        edtNewPassword = (EditText) findViewById(R.id.edtNewPassword);
        edtNewPasswordAgain = (EditText) findViewById(R.id.edtNewPasswordAgain);
        btnChangePassword = (Button) findViewById(R.id.btnChangePassword);
    }

    private void setChangePassword() {
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldpass = edtOldPassword.getText().toString().trim();// lấy mật khẩu cũ
                String newpass = edtNewPassword.getText().toString().trim();// lấy mật khẩu mới
                String repass = edtNewPasswordAgain.getText().toString().trim();// xác nhận mật khẩu mới
                if (oldpass.isEmpty() || newpass.isEmpty() || repass.isEmpty()) {
                    Toast.makeText(ChangePasswordActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                pref = getSharedPreferences("login", MODE_PRIVATE);// lấy share preference login
                String username = pref.getString("username", "");
                Users u = DatabaseConnection.getInstance(ChangePasswordActivity.this).userDao().FindUserByUserName(username);
                // check điều kiện đổi mật khẩu
                if (!u.getPassword().equals(oldpass)) {
                    Toast.makeText(ChangePasswordActivity.this, "Mật khẩu cũ chưa đúng", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!newpass.equals(repass)) {
                    Toast.makeText(ChangePasswordActivity.this, "Xác nhận mật khẩu chưa đúng", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    u.setPassword(newpass);
                    DatabaseConnection.getInstance(ChangePasswordActivity.this).userDao().update(u);
                    // lưu mật khẩu xuống database (bảng users)
                    Toast.makeText(ChangePasswordActivity.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
                    // chuyển về trang chủ
                    startActivity(intent);
                }
            }
        });

    }

    public void BackSetting() {
        txtBackSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}