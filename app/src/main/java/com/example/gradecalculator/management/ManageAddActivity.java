package com.example.gradecalculator.management;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.gradecalculator.MainActivity;
import com.example.gradecalculator.MySingleton;
import com.example.gradecalculator.R;
import com.example.gradecalculator.SharedPreferenceUtil;
import com.example.gradecalculator.graduation.GraduationFragment;
import com.example.gradecalculator.home.HomeFragment;
import com.example.gradecalculator.mypage.MyPageModifyFragment;

import org.jetbrains.annotations.NotNull;

import java.nio.file.attribute.FileTime;
import java.util.HashMap;
import java.util.Map;

public class ManageAddActivity extends AppCompatActivity {
    private ImageButton btn_back;
    private EditText add_name;
    private Button btn_register;
    private String[] kinds = {"전공", "교양"};
    private String[] kindMajors = {"-", "핵심", "심화", "심화-핵심","심화-심화","복수-핵심", "복수-심화","부"};
    private String[] kindDomains = {"-","공통", "핵심1", "핵심2", "핵심3", "핵심4","일반","진로"};
    private String[] credits = {"P", "NP", "1", "2", "3"};
    private String[] grades = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D+", "D0", "D-", "F"};
    private String[] retakes = {"-", "1"};
    private String tabYear;
    private String tabSemester;
    private TextView student_grade, semester;
    private String txt_sp_kind, txt_sp_kind_major, txt_sp_kind_domain, txt_sp_kind_credit, txt_sp_kind_grade, txt_sp_kind_retake;
    private ManagementFragment frag2;



    private FragmentManager fm;
    private FragmentTransaction ft;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_add);

        context = this.getApplicationContext();

        // 프래그먼트에서 받아온 학년,학기
        Intent intent = getIntent();
        tabYear = intent.getStringExtra("tabYear");
        tabSemester = intent.getStringExtra("tabSemester");

        student_grade = findViewById(R.id.student_grade);
        semester = findViewById(R.id.semester);

        student_grade.setText(tabYear);
        semester.setText(tabSemester);

        // 과목명
        add_name = findViewById(R.id.add_name); //edit Text

        // 스피너
        Spinner spinner_kind = findViewById(R.id.spinner_kind);
        Spinner spinner_kind_major = findViewById(R.id.spinner_kind_major);
        Spinner spinner_kind_domain = findViewById(R.id.spinner_kind_domain);
        Spinner spinner_credit = findViewById(R.id.spinner_credit);
        Spinner spinner_grade = findViewById(R.id.spinner_grade);
        Spinner spinner_retake = findViewById(R.id.spinner_retake);

        ArrayAdapter<String> adapter_kind = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kinds);
        ArrayAdapter<String> adapter_kind_major = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kindMajors);
        ArrayAdapter<String> adapter_kind_domain = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kindDomains);
        ArrayAdapter<String> adapter_credit = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, credits);
        ArrayAdapter<String> adapter_grade = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, grades);
        ArrayAdapter<String> adapter_retake = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, retakes);

        adapter_kind.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_kind_major.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_kind_domain.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_credit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_grade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_retake.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_kind.setAdapter(adapter_kind);
        spinner_kind_major.setAdapter(adapter_kind_major);
        spinner_kind_domain.setAdapter(adapter_kind_domain);
        spinner_credit.setAdapter(adapter_credit);
        spinner_grade.setAdapter(adapter_grade);
        spinner_retake.setAdapter(adapter_retake);

        spinner_kind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txt_sp_kind =  spinner_kind.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txt_sp_kind =  "전공";
            }
        });

        spinner_kind_major.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txt_sp_kind_major =  spinner_kind_major.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txt_sp_kind_major = "복수";
            }
        });

        spinner_kind_domain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txt_sp_kind_domain =  spinner_kind_domain.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txt_sp_kind_domain = "0";
            }
        });

        spinner_credit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txt_sp_kind_credit =  spinner_credit.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txt_sp_kind_credit = "P";
            }
        });

        spinner_grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txt_sp_kind_grade =  spinner_grade.getSelectedItem().toString();

                if(txt_sp_kind_grade.equals("A+")){
                    txt_sp_kind_grade = "AA";
                }else if(txt_sp_kind_grade.equals("B+")){
                    txt_sp_kind_grade = "BB";
                }else if(txt_sp_kind_grade.equals("C+")){
                    txt_sp_kind_grade = "CC";
                }else if(txt_sp_kind_grade.equals("D+")){
                    txt_sp_kind_grade = "DD";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txt_sp_kind_grade = "AA";
            }
        });

        spinner_retake.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txt_sp_kind_retake =  spinner_retake.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txt_sp_kind_retake = "0";
            }
        });

        frag2 = new ManagementFragment();

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(add_name.getText().toString().equals("")){
                    Toast.makeText(context, "과목명을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    register(context, SharedPreferenceUtil.getSharedPreference(context, "userID"),tabYear, tabSemester, add_name.getText().toString(),txt_sp_kind_credit, txt_sp_kind_major, txt_sp_kind, txt_sp_kind_domain, txt_sp_kind_grade, txt_sp_kind_retake);
                }
            }
        });
    }

    public void register(Context context, String id, String year, String semester, String name, String credit, String kind_major, String kind, String kind_domain, String grade, String retake){

        if(kind_domain.equals("-")){
            kind_domain = "0";
        }else if(kind_major.equals("-")){
            kind_major = "0";
        }else if(retake.equals("-")){
            retake = "0";
        }

        String url = "http://ec2-52-79-221-73.ap-northeast-2.compute.amazonaws.com/manageRegister.php?userID="+id+"&year="+year+"&semester="+semester+"&subjectName="
                +name+"&credit="+credit+"&MajorOrLiberal="+kind+"&subjectClass="+kind_major+"&subjectArea="+kind_domain+"&grade="+grade+"&retake="+retake;

        // Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                 @Override
                public void onResponse(String response) {
                     Toast.makeText(context, "과목이 등록 되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "과목 등록을 실패하였습니다.", Toast.LENGTH_SHORT).show();
                }
        }){
            //Post method parameters
            /*@Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                //TODO: Change params and values
                params.put("userID", id); // 학번
                params.put("year", year); // 학년
                params.put("semester", semester); // 학기
                params.put("subjectName", name); // 과목명
                params.put("credit", credit); // 학점
                params.put("MajorOrLiberal", kind_major); // 전공/교양
                params.put("subjectClass", kind); // 전공 분류
                params.put("subjectArea", kind_domain); // 영역
                params.put("grade", grade); // 성적
                params.put("retake", retake); // 재수강 여부

                return params;
            }*/
        };

        // Get a RequestQueue && Add a request (in this example, called stringRequest) to your RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

}

