package com.example.fridge_partner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class RegisterActivity extends Activity {
    // 调用Actvity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //关联activity_register.xml
        setContentView(R.layout.activity_register);
        // 关联用户名、密码、确认密码、邮箱和注册、返回登录按钮
        EditText userName = (EditText) this.findViewById(R.id.username);
        EditText passWord = (EditText) this.findViewById(R.id.password);
        EditText passWordAgain = (EditText) this.findViewById(R.id.repassword);
        Button signUpButton = (Button) this.findViewById(R.id.RegisterButton);
        Button backLoginButton = (Button) this.findViewById(R.id.loginButton);

        // 立即注册按钮监听器
        signUpButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String strUserName = userName.getText().toString().trim();
                        String strPassWord = passWord.getText().toString().trim();
                        String strPassWordAgain = passWordAgain.getText().toString().trim();
                        // String strPhoneNumber = email.getText().toString().trim();
                        //注册格式粗检
                        if (!strUserName.contains("@")) {
                            Toast.makeText(RegisterActivity.this, "Incorrect Email format", Toast.LENGTH_SHORT).show();
                        } else if (strPassWord.length() > 16) {
                            Toast.makeText(RegisterActivity.this, "The password length must be less than 16!", Toast.LENGTH_SHORT).show();
                        } else if (strPassWord.length() < 6) {
                            Toast.makeText(RegisterActivity.this, "The password length must be greater than 6!", Toast.LENGTH_SHORT).show();
                        } else if (!strPassWord.equals(strPassWordAgain)) {
                            Toast.makeText(RegisterActivity.this, "The new password entries do not match!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration was successful!", Toast.LENGTH_SHORT).show();
                            // 跳转到登录界面
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }
        );
        // 返回登录按钮监听器
        backLoginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 跳转到登录界面
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }
}
