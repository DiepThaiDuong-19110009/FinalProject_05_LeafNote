package hcmute.edu.vn.leafnote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hcmute.edu.vn.leafnote.R;
import hcmute.edu.vn.leafnote.database.DatabaseConnection;
import hcmute.edu.vn.leafnote.entity.Users;
import hcmute.edu.vn.leafnote.service.JavaMail;
import hcmute.edu.vn.leafnote.service.RandomString;

public class ForgotActivity extends AppCompatActivity {
    TextView txtBackLogin;
    EditText edtEmail;
    Button btnForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        addControl();
        forgot();
        backLogin();
    }

    private void addControl() {
        txtBackLogin = (TextView) findViewById(R.id.txtBackLogin);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        btnForgotPassword = (Button) findViewById(R.id.btnForgotPassword);
    }

    public void forgot() {
        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString().trim();// lấy thông tin email
                // kiểm tra email
                if (email.isEmpty()) {
                    Toast.makeText(ForgotActivity.this, "Vui lòng nhập email", Toast.LENGTH_LONG).show();
                    return;
                }
                Users u = DatabaseConnection.getInstance(ForgotActivity.this).userDao().FindUserByEmail(email);
                if (u == null) {
                    Toast.makeText(ForgotActivity.this, "Email chưa chính xác. Vui lòng kiểm tra", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String code = RandomString.randomPassword();// chuỗi random làm password
                    JavaMail.sendMail(email, "Mã xác nhận", "Mật khẩu mới của bạn là: " + code);// gửi mail
                    u.setPassword(code);
                    DatabaseConnection.getInstance(ForgotActivity.this).userDao().update(u);// lưu password mới
                    Toast.makeText(ForgotActivity.this, "Mật khẩu mới của bạn đã được gửi. Vui lòng kiểm tra email"
                            , Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(ForgotActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void backLogin() {
        txtBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}