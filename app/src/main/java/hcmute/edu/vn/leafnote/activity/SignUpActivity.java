package hcmute.edu.vn.leafnote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hcmute.edu.vn.leafnote.R;
import hcmute.edu.vn.leafnote.database.DatabaseConnection;
import hcmute.edu.vn.leafnote.entity.Users;
import hcmute.edu.vn.leafnote.service.JavaMail;

public class SignUpActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword, edtName, edtEmail, edtConfirm;
    Button btnDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        AnhXa();
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddUser();

            }
        });
    }

    private void AnhXa() {
        edtUsername = (EditText) findViewById(R.id.edtSignupUserName);
        edtPassword = (EditText) findViewById(R.id.edtSignUpPassword);
        edtName = (EditText) findViewById(R.id.edtSignupName);
        edtEmail = (EditText) findViewById(R.id.edtSignupEmail);
        edtConfirm = (EditText) findViewById(R.id.edtConfirmSignUpPassword);
        btnDangKy = (Button) findViewById(R.id.btnSignUp);
    }

    public void signupGoogle(View view) {
    }

    public void signupFacebook(View view) {
    }

    public void linkLogin(View view) {
        Intent linkLogin = new Intent(this, LoginActivity.class);
        startActivity(linkLogin);
    }

    private void AddUser() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String confirm = edtConfirm.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(confirm)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!TextUtils.equals(password,confirm)){
            Toast.makeText(this, "Xác nhận mật khẩu chưa đúng", Toast.LENGTH_SHORT).show();
            return;
        }
        Users u=DatabaseConnection.getInstance(this).userDao().FindUserByUserName(username);
        if(u!=null){
            Toast.makeText(this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
            return;
        }
        Users a=DatabaseConnection.getInstance(this).userDao().FindUserByEmail(email);
        if(a!=null){
            Toast.makeText(this, "Email đã tồn tại", Toast.LENGTH_SHORT).show();
            return;
        }
        Users user = new Users(username, password, name, email);
//

        DatabaseConnection.getInstance(this).userDao().insert(user);
        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
        Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
        startActivity(intent);
        JavaMail.sendMail(email,"Đăng ký tài khoản thành công",
                "Bạn vừa đăng ký thành công tài khoản trên leafapp.\nChân thành cảm ơn bạn đã quan tâm và sử dụng app. Chúc bạn ngày mới vui vẻ.\nTrân trọng!");
        edtUsername.setText("");
        edtPassword.setText("");
        edtName.setText("");
        edtEmail.setText("");
        edtConfirm.setText("");
    }

}