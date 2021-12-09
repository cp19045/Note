package com.example.memorandum;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.memorandum.Dao.UserDaolmp;


public class LoginActivity extends AppCompatActivity {

    private EditText user;
    private EditText pwd;
    private Button btn_login;
    private Button btn_register;
    private Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        user = findViewById(R.id.et_user);
        pwd = findViewById(R.id.et_pwd);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        btn_update = findViewById(R.id.btn_update);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String users =user.getText().toString().trim();
                String password = pwd.getText().toString().trim();
                if(users.isEmpty()){
                    Toast.makeText(LoginActivity.this,"请输入账号",Toast.LENGTH_LONG).show();
                }else if (password.isEmpty()){
                    Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_LONG).show();
                }else{
                    UserDaolmp userDaolmp = new UserDaolmp(LoginActivity.this);
                    if(userDaolmp.FindByPhoneuser(users,password)){
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("id",users);
                        startActivityForResult(intent,1);
                        finish();
                        if(password.equals("123456")){
                            Toast.makeText(LoginActivity.this,"您的初始密码为123456请及时更改",Toast.LENGTH_LONG).show();
                        }
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                    }else
                        Toast.makeText(LoginActivity.this,"账户或密码错误或不存在",Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,UpdateActivity.class);
                startActivity(intent);
            }
        });
    }
}
