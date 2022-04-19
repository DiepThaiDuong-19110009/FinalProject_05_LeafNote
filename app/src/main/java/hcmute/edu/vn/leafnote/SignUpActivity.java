package hcmute.edu.vn.leafnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hcmute.edu.vn.leafnote.dao.UserDao;
import hcmute.edu.vn.leafnote.database.DatabaseConnection;
import hcmute.edu.vn.leafnote.entity.Users;

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

        Users user = new Users(username, password, name, email);
//        DatabaseConnection db = Room.databaseBuilder(getApplicationContext(), DatabaseConnection.class, "leafnote.db")
//                .allowMainThreadQueries()
//                .build();
//        UserDao userDao = db.userDao();
//        userDao.insert(users);
//        Toast.makeText(this, users.toString(), Toast.LENGTH_LONG).show();
        DatabaseConnection.getInstance(this).userDao().insert(user);
        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
        Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
        startActivity(intent);
        edtUsername.setText("");
        edtPassword.setText("");
        edtName.setText("");
        edtEmail.setText("");
    }

//    public void signUpSuccess(View view) {
//        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
//        Intent linkLogin = new Intent(this, LoginActivity.class);
//        startActivity(linkLogin);
//    }
}