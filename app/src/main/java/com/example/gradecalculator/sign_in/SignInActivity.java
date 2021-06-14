package com.example.gradecalculator.sign_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.gradecalculator.MainActivity;
import com.example.gradecalculator.R;
import com.example.gradecalculator.SharedPreferenceUtil;
import com.example.gradecalculator.home.HomeFragment;
import com.example.gradecalculator.sign_up.SignUpActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity {
    private EditText et_id, et_pass;
    private Button btn_signin, btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_signin = findViewById(R.id.btn_signin);
        btn_signup = findViewById(R.id.btn_signup);

        btn_signup.setOnClickListener(new View.OnClickListener() { //회원가입 버튼을 클릭 시 수행
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText에 현재 입력되어있는 값을 get(가져온다) 해온다.
                String userID = et_id.getText().toString();
                String password = et_pass.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { //로그인에 성공한 경우
                                String userID = jsonObject.getString("userID");
                                String password = jsonObject.getString("password");
                                String userName = jsonObject.getString("userName");
                                String toeic = jsonObject.getString("toeic");
                                String schoolYear = jsonObject.getString("schoolYear");
                                String semester = jsonObject.getString("semester");
                                String mainMajor = jsonObject.getString("mainMajor");
                                String _2ndMajorClass = jsonObject.getString("_2ndMajorClass");
                                String _2ndMajor = jsonObject.getString("_2ndMajor");
                                String thesis = jsonObject.getString("thesis");

                                // 사용자 데이터 SharedPreference 저장
                                SharedPreferenceUtil.setSharedPreference(SignInActivity.this, "userID", userID);
                                SharedPreferenceUtil.setSharedPreference(SignInActivity.this, "password", password);
                                SharedPreferenceUtil.setSharedPreference(SignInActivity.this, "userName", userName);
                                SharedPreferenceUtil.setSharedPreference(SignInActivity.this, "toeic", toeic);
                                SharedPreferenceUtil.setSharedPreference(SignInActivity.this, "schoolYear", schoolYear);
                                SharedPreferenceUtil.setSharedPreference(SignInActivity.this, "semester", semester);
                                SharedPreferenceUtil.setSharedPreference(SignInActivity.this, "mainMajor", mainMajor);
                                SharedPreferenceUtil.setSharedPreference(SignInActivity.this, "_2ndMajorClass", _2ndMajorClass);
                                SharedPreferenceUtil.setSharedPreference(SignInActivity.this, "_2ndMajor", _2ndMajor);
                                SharedPreferenceUtil.setSharedPreference(SignInActivity.this, "thesis", thesis);

                                Toast.makeText(getApplicationContext(),"로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                intent.putExtra("userID", userID);
                                intent.putExtra("password", password);
                                startActivity(intent);
                            }
                            else { //로그인에 실패한 경우
                                Toast.makeText(getApplicationContext(),"로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                SignInRequest signInRequest = new SignInRequest(userID, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignInActivity.this);
                queue.add(signInRequest);
            }
        });
    }
}