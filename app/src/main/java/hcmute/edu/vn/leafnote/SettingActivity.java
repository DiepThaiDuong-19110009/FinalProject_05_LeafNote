package hcmute.edu.vn.leafnote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    TextView txtAccount, txtBackHome, txtChangePassword,txtLogout;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        addControl();

        backHome();
        setAccount();
        changePassword();
        logout();
    }

    private void addControl() {
        txtAccount = (TextView) findViewById(R.id.txtAccount);
        txtBackHome = (TextView) findViewById(R.id.txtBackHome);
        txtChangePassword = (TextView) findViewById(R.id.txtChangePassword);
        txtLogout=(TextView) findViewById(R.id.txtLogout);
    }

    public void backHome(){
        txtBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void setAccount(){
        txtAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent account = new Intent(SettingActivity.this, AccountActivity.class);
                startActivity(account);
            }
        });
    }

    public void changePassword(){
        txtChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }
    private void logout(){
        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pref = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.apply();
                Intent intent =new Intent(SettingActivity.this,MainActivity.class);
                intent.putExtra("finish",true);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}