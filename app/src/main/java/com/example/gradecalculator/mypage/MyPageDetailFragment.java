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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gradecalculator.MainActivity;
import com.example.gradecalculator.MySingleton;
import com.example.gradecalculator.R;
import com.example.gradecalculator.SharedPreferenceUtil;
import com.example.gradecalculator.sign_in.SignInActivity;
import com.example.gradecalculator.sign_in.SignInRequest;
import com.example.gradecalculator.sign_up.SignUpActivity;
import com.example.gradecalculator.sign_up.SignUpRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MyPageDetailFragment extends Fragment {

    private Button btn_edit;
    private ImageButton btn_back;
    private TextView tv_user_id, tv_user_name, tv_year, tv_semester, tv_major, tv_kind_major, tv_second_major, tv_toeic, tv_thesis;
    private Context context;

    public MyPageDetailFragment() {
        // Required empty public constructor
    }

    public static MyPageDetailFragment newInstance() {
        MyPageDetailFragment fragment = new MyPageDetailFragment();
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
        View view = inflater.inflate(R.layout.fragment_my_page_detail, container, false);

        tv_user_id = view.findViewById(R.id.tv_user_id); // 학번
        tv_user_name = view.findViewById(R.id.tv_user_name); // 이름
        tv_year = view.findViewById(R.id.tv_year); //학년
        tv_semester = view.findViewById(R.id.tv_semester); //학기
        tv_major = view.findViewById(R.id.tv_major); // 주전공
        tv_kind_major = view.findViewById(R.id.tv_kind_major); // 전공분류
        tv_second_major = view.findViewById(R.id.tv_second_major); // 선택전공
        tv_toeic = view.findViewById(R.id.tv_toeic); // 토익
        tv_thesis = view.findViewById(R.id.tv_thesis); // 졸업논문 여부
        btn_edit = view.findViewById(R.id.btn_edit_mypage); // 정보 수정

        mypageInfo(); // 마이페이지 가져오기

        btn_back = view.findViewById(R.id.btn_back);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MyPageModifyFragment fragment1 = new MyPageModifyFragment();
                transaction.replace(R.id.main_frame, fragment1);
                transaction.commit();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MyPageFragment fragment1 = new MyPageFragment();
                transaction.replace(R.id.main_frame, fragment1);
                transaction.commit();
            }
        });

        return view;

    }

    public void mypageInfo(){
        //EditText에 현재 입력되어있는 값을 get(가져온다) 해온다.
        String userID = SharedPreferenceUtil.getSharedPreference(context, "userID");
        String password = SharedPreferenceUtil.getSharedPreference(context, "password");

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success) { //로그인에 성공한 경우
                        String userID = jsonObject.getString("userID");
                        String userName = jsonObject.getString("userName");
                        String password = jsonObject.getString("password");
                        String mainMajor = jsonObject.getString("mainMajor");
                        String entYear = jsonObject.getString("entYear");
                        String _2ndMajorClass = jsonObject.getString("_2ndMajorClass");
                        String _2ndMajor = jsonObject.getString("_2ndMajor");
                        String schoolYear = jsonObject.getString("schoolYear");
                        String semester = jsonObject.getString("semester");
                        String toeic = jsonObject.getString("toeicScore");
                        String thesis = jsonObject.getString("thesis");

                        // 사용자 데이터 SharedPreference 저장
                        SharedPreferenceUtil.setSharedPreference(context, "userID", userID);
                        SharedPreferenceUtil.setSharedPreference(context, "password", password);
                        SharedPreferenceUtil.setSharedPreference(context, "userName", userName);
                        SharedPreferenceUtil.setSharedPreference(context, "toeic", toeic);
                        SharedPreferenceUtil.setSharedPreference(context, "entYear", entYear);
                        SharedPreferenceUtil.setSharedPreference(context, "schoolYear", schoolYear);
                        SharedPreferenceUtil.setSharedPreference(context, "semester", semester);
                        SharedPreferenceUtil.setSharedPreference(context, "mainMajor", mainMajor);
                        SharedPreferenceUtil.setSharedPreference(context, "_2ndMajorClass", _2ndMajorClass);
                        SharedPreferenceUtil.setSharedPreference(context, "_2ndMajor", _2ndMajor);
                        SharedPreferenceUtil.setSharedPreference(context, "thesis", thesis);

                        mypageData(); // data update

                    }
                    else { // 마이페이지 정보 가져오기에 실패한 경우
                        Toast.makeText(context,"마이페이지 정보를 불러오지 못했습니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        SignInRequest signInRequest = new SignInRequest(userID, password, responseListener);
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(signInRequest);
    }

    public void mypageData(){
        tv_user_id.setText(SharedPreferenceUtil.getSharedPreference(context, "userID"));
        tv_user_name.setText(SharedPreferenceUtil.getSharedPreference(context, "userName"));
        tv_year.setText(SharedPreferenceUtil.getSharedPreference(context, "schoolYear"));
        tv_semester.setText(SharedPreferenceUtil.getSharedPreference(context, "semester"));
        tv_major.setText(SharedPreferenceUtil.getSharedPreference(context, "mainMajor"));
        tv_kind_major.setText(SharedPreferenceUtil.getSharedPreference(context, "_2ndMajorClass"));
        tv_second_major.setText(SharedPreferenceUtil.getSharedPreference(context, "_2ndMajor"));
        tv_toeic.setText(SharedPreferenceUtil.getSharedPreference(context, "toeic"));
        tv_thesis.setText(SharedPreferenceUtil.getSharedPreference(context, "thesis"));
    }

}