package com.example.gradecalculator.sign_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import com.example.gradecalculator.R;
import com.example.gradecalculator.sign_in.SignInActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    private EditText et_id, et_name, et_pass, et_entYear, et_toeic;
    private Button btn_signUp, IDcheck;
    private Spinner sp_schoolYear, sp_semester, sp_mainMajor, sp_2ndMajorClass, sp_2ndMajor, sp_thesis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        et_id = findViewById(R.id.et_id);
        et_name = findViewById(R.id.et_name);
        et_pass = findViewById(R.id.et_pass);
        et_entYear = findViewById(R.id.et_entYear);
        et_toeic = findViewById(R.id.et_toeic);
//        final TextInputLayout inputLayout = findViewById(R.id.pscheck);
//        EditText editText = inputLayout.getEditText();


        btn_signUp = findViewById(R.id.btn_signUp);

        sp_schoolYear = findViewById(R.id.sp_schoolYear);
        sp_semester = findViewById(R.id.sp_semester);
        sp_mainMajor = findViewById(R.id.sp_mainMajor);
        sp_2ndMajorClass = findViewById(R.id.sp_2ndMajorClass);
        sp_2ndMajor = findViewById(R.id.sp_2ndMajor);
        sp_thesis = findViewById(R.id.sp_thesis);

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EditText에 현재 입력되어있는 값을 get(가져온다) 해온다.
                //int userID = Integer.parseInt(et_id.getText().toString()); //int 형 타입
                String userID = et_id.getText().toString();                  //String 형 타입

                String userName = et_name.getText().toString();
                String password = et_pass.getText().toString();
                //int entYear = Integer.parseInt(et_entYear.getText().toString());
                String entYear = et_entYear.getText().toString();

                //int toeic = Integer.parseInt(et_toeic.getText().toString());
                String toeic = et_toeic.getText().toString();

                //int schoolYear = Integer.parseInt(sp_schoolYear.getSelectedItem().toString());
                String schoolYear = sp_schoolYear.getSelectedItem().toString();

                //int semester = Integer.parseInt(sp_semester.getSelectedItem().toString());
                String semester = sp_semester.getSelectedItem().toString();

                String mainMajor = sp_mainMajor.getSelectedItem().toString();
                String _2ndMajorClass = sp_2ndMajorClass.getSelectedItem().toString();
                String _2ndMajor = sp_2ndMajor.getSelectedItem().toString();
                String thesis = sp_thesis.getSelectedItem().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { //회원 등록에 성공한 경우
                                Toast.makeText(getApplicationContext(),"회원등록에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                                startActivity(intent);
                            }
                            else { //회원 등록에 실패한 경우
                                Toast.makeText(getApplicationContext(),"회원등록에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                //서버로 Volley를 이용해서 요청을 함.
                SignUpRequest registerRequest = new SignUpRequest(userID, userName, password, entYear, toeic, schoolYear, semester, mainMajor, _2ndMajorClass, _2ndMajor, thesis, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
                queue.add(registerRequest);

            }
        });
    }


}