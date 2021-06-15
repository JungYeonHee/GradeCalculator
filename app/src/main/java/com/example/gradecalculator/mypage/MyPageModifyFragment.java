package com.example.gradecalculator.mypage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gradecalculator.MySingleton;
import com.example.gradecalculator.R;
import com.example.gradecalculator.SharedPreferenceUtil;
import com.example.gradecalculator.sign_in.SignInActivity;
import com.example.gradecalculator.sign_up.SignUpActivity;
import com.example.gradecalculator.sign_up.SignUpRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MyPageModifyFragment extends Fragment {

    private Button btn_update;
    private EditText et_toeic;
    private Spinner sp_year, sp_semester, sp_mainMajor, sp_2ndMajorClass, sp_2ndMajor, sp_thesis;
    private Context context;
    private String schoolYear, semester, mainMajor, _2ndMajorClass, _2ndMajor, thesis;
    private TextView tv_user_id, tv_user_name;

    public MyPageModifyFragment() {
        // Required empty public constructor
    }


    public static MyPageModifyFragment newInstance(String param1, String param2) {
        MyPageModifyFragment fragment = new MyPageModifyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity().getApplicationContext();
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_page_modify, container, false);

        sp_year = view.findViewById(R.id.sp_year);
        sp_semester = view.findViewById(R.id.sp_semester);
        sp_mainMajor = view.findViewById(R.id.sp_major);
        sp_2ndMajorClass = view.findViewById(R.id.sp_kind_major);
        sp_2ndMajor = view.findViewById(R.id.sp_second_major);
        sp_thesis = view.findViewById(R.id.sp_thesis);
        btn_update = view.findViewById(R.id.btn_update_mypage);
        et_toeic = view.findViewById(R.id.et_toeic);
        tv_user_id = view.findViewById(R.id.tv_user_id);
        tv_user_name = view.findViewById(R.id.tv_user_name);

        tv_user_id.setText(SharedPreferenceUtil.getSharedPreference(context, "userID"));
        tv_user_name.setText(SharedPreferenceUtil.getSharedPreference(context, "userName"));

        // 기존 회원 정보 유지 --> input 값 바꾸기
        sp_year.setSelection(getIndex(sp_year, "4"));
        sp_semester.setSelection(getIndex(sp_semester, "1"));
        sp_mainMajor.setSelection(getIndex(sp_mainMajor, "정보시스템공학과"));
        sp_2ndMajorClass.setSelection(getIndex(sp_2ndMajorClass, "부전공"));
        sp_2ndMajor.setSelection(getIndex(sp_2ndMajor, "컴퓨터공학과"));
        sp_thesis.setSelection(getIndex(sp_thesis, "통과X"));


        // nothing selected 일 때 기존 회원 정보로 바꾸기
        sp_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                schoolYear =  sp_year.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                schoolYear =  "4";
            }
        });

        sp_semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                semester =  sp_semester.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                semester =  "1";
            }
        });

        sp_mainMajor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mainMajor =  sp_mainMajor.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mainMajor =  "정보시스템공학과";
            }
        });

        sp_2ndMajorClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                _2ndMajorClass =  sp_2ndMajorClass.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                _2ndMajorClass =  "부전공";
            }
        });

        sp_2ndMajor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                _2ndMajor =  sp_2ndMajor.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                _2ndMajor =  "컴퓨터공학과";
            }
        });

        sp_thesis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                thesis =  sp_thesis.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                thesis =  "통과X";
            }
        });

        // 수정 버튼 눌렀을 때
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_id = SharedPreferenceUtil.getSharedPreference(context, "userID");
                String toeic = et_toeic.getText().toString();

                updateMyPage(context, user_id, toeic, schoolYear, semester, mainMajor, _2ndMajorClass, _2ndMajor, thesis);
            }
        });

        return view;
    }

    // 기존 회원 정보로 spinner 아이템 선택
    public int getIndex(Spinner spinner, String item){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equals(item)){
                return i;
            }
        }
        return 0;
    }

    // 마이페이지 업데이트
    public void updateMyPage(Context context, String id, String toeic, String schoolYear, String semester, String mainMajor, String _2ndMajorClass, String _2ndMajor, String thesis){

        String split_semester = semester.substring(0,1);
        String split_year = schoolYear.substring(0,1);

        String url = "http://ec2-52-79-221-73.ap-northeast-2.compute.amazonaws.com/updateMypage.php?userID="+id+"&toeic="+toeic+"&year="+split_year+"&semester="
                +split_semester+"&mainMajor="+mainMajor+"&subMajorClass="+_2ndMajorClass+"&subMajor="+_2ndMajor+"&thesis="+thesis;

        // Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            Toast.makeText(context, "정보가 수정 되었습니다.", Toast.LENGTH_SHORT).show();

                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        MyPageDetailFragment fragment1 = new MyPageDetailFragment();
                            transaction.replace(R.id.main_frame, fragment1);
                            transaction.commit();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "정보 수정을 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
        }){

        };

         // Get a RequestQueue && Add a request (in this example, called stringRequest) to your RequestQueue.
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

}