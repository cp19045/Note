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

public class UpdateActivity  extends AppCompatActivity {
    private EditText user;
    private EditText pwd;
    private EditText aginpwd;
    private Button update;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        init();
    }

    private void init() {
        user = findViewById(R.id.et_regUser);
        back = findViewById(R.id.back);
        pwd = findViewById(R.id.et_regPwd);
        aginpwd = findViewById(R.id.et_regAgninPwd);
        update = findViewById(R.id.update);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String users = user.getText().toString().trim();
                String password = pwd.getText().toString().trim();
                String passwordAgin = aginpwd.getText().toString().trim();
                if (users.isEmpty()){
                    Toast.makeText(UpdateActivity.this,"请输入账号",Toast.LENGTH_LONG).show();
                }else if(password.isEmpty()){
                    Toast.makeText(UpdateActivity.this,"请输入密码",Toast.LENGTH_LONG).show();
                }else if(passwordAgin.isEmpty()){
                    Toast.makeText(UpdateActivity.this,"请输入确认密码",Toast.LENGTH_LONG).show();
                }else if(!password.equals(passwordAgin)){
                    Toast.makeText(UpdateActivity.this,"两次密码不一致",Toast.LENGTH_LONG).show();
                }else{
                    UserDaolmp userDaolmp = new UserDaolmp(UpdateActivity.this);
                    User user2 =new User();
                    user2.setPwd(password);
                    long a= userDaolmp.updateuser(user2,users);
                    if(a>0){
                        Toast.makeText(UpdateActivity.this,"修改成功",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
