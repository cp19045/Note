package com.example.memorandum;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.memorandum.Dao.UserDaolmp;
import com.example.memorandum.pojo.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText user;
    private EditText pwd,phone,name;
    private EditText aginpwd;
    private Button register;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        user = findViewById(R.id.et_regUser);

        back = findViewById(R.id.back);
        pwd = findViewById(R.id.et_regPwd);
        aginpwd = findViewById(R.id.et_regAgninPwd);
        register = findViewById(R.id.register);
        name = findViewById(R.id.et_regName);
        phone = findViewById(R.id.et_regPhone);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String users = user.getText().toString().trim();
               String password = pwd.getText().toString().trim();
               String passwordAgin = aginpwd.getText().toString().trim();
               String phones= phone.getText().toString().trim();
                String names= name.getText().toString().trim();
               if (users.isEmpty()&&phones.isEmpty()&&names.isEmpty()){
                   Toast.makeText(RegisterActivity.this,"请输入账号、昵称、手机",Toast.LENGTH_LONG).show();
               }else if(password.isEmpty()){
                   Toast.makeText(RegisterActivity.this,"请输入密码",Toast.LENGTH_LONG).show();
               }else if(passwordAgin.isEmpty()){
                   Toast.makeText(RegisterActivity.this,"请输入确认密码",Toast.LENGTH_LONG).show();
               }else if(!password.equals(passwordAgin)){
                   Toast.makeText(RegisterActivity.this,"两次密码不一致",Toast.LENGTH_LONG).show();
               }else{
                   UserDaolmp userDaolmp = new UserDaolmp(RegisterActivity.this);
                   User user2 =new User();
                   user2.setPhone(phones);
                   user2.setName(names);
                   user2.setUserId(users);
                   user2.setPwd("123456");
                   long a= userDaolmp.adduser(user2);
                   if(a>0){
                       Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                   }
               }
            }
        });
    }
}
