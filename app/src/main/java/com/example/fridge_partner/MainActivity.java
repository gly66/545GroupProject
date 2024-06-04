package com.example.fridge_partner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

public class MainActivity extends Activity {
    // 调用Actvity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 关联activity.xml
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, SecMainActivity.class);
        startActivity(intent);
//         登录成功后，再次启动app自动登录
//        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
//         String username = preferences.getString("username", "");
//        String password = preferences.getString("password", "");
//        if (!username.isEmpty() && !password.isEmpty()) {
//            Intent intent = new Intent(MainActivity.this, SecMainActivity.class);
//            startActivity(intent);
//        }

        // 关联用户名、密码和登录、注册按钮
        EditText userName = (EditText) this.findViewById(R.id.username);
        EditText passWord = (EditText) this.findViewById(R.id.password);
        Button loginButton = (Button) this.findViewById(R.id.loginButton);
        TextView signUpText = (TextView) this.findViewById(R.id.signupText);
        // Button signUpButton = (Button) this.findViewById(R.id.registerButton);

        // 登录按钮监听器
        loginButton.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 获取用户名和密码
                        String strUserName = userName.getText().toString().trim();
                        String strPassWord = passWord.getText().toString().trim();
                        // 判断如果用户名为"123456"密码为"123456"则登录成功
                        if (strUserName.equals("user@gmail.com") && strPassWord.equals("123456")) {
                            Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            saveUserName();
                            Intent intent = new Intent(MainActivity.this, SecMainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        // 注册文本监听器
        signUpText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在此处执行点击链接后的操作，导航到 RegisterActivity
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveUserName() {
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", "user@gmail.com");
        editor.putString("password", "123456");
        editor.apply();
    }
}
