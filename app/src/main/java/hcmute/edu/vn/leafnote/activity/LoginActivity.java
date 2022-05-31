package hcmute.edu.vn.leafnote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hcmute.edu.vn.leafnote.R;
import hcmute.edu.vn.leafnote.database.DatabaseConnection;
import hcmute.edu.vn.leafnote.entity.Users;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnDangNhap;
    TextView txtForgotPassword;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        pref = getSharedPreferences("login",MODE_PRIVATE);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,ForgotActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa(){
        edtUsername = (EditText) findViewById(R.id.edtLoginUsername);
        edtPassword = (EditText) findViewById(R.id.edtLoginPassword);
        btnDangNhap = (Button) findViewById(R.id.btnLogin);
        txtForgotPassword=(TextView) findViewById(R.id.txtForgotPassword);
    }

    private void Login() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Vui lòng điền đủ thông tin đăng nhập", Toast.LENGTH_SHORT).show();
            return;
        }
        Users user = DatabaseConnection.getInstance(this).userDao().Login(username, password);
        if (user == null) {
            Toast.makeText(this, "Username hoặc Password chưa đúng", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.commit();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void loginGoogle(View view) {
        Toast.makeText(this, "Đăng nhập bằng Google", Toast.LENGTH_SHORT).show();
    }

    public void loginFacebook(View view) {
        Toast.makeText(this, "Đăng nhập bằng Facebook", Toast.LENGTH_SHORT).show();
    }

    public void linkSignUp(View view) {
        Intent linkSignUp = new Intent(this, SignUpActivity.class);
        startActivity(linkSignUp);
    }


}