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
import com.example.gradecalculator.MySingleton;
import com.example.gradecalculator.R;
import com.example.gradecalculator.SharedPreferenceUtil;
import com.example.gradecalculator.sign_in.SignInActivity;
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

        tv_user_id.setText(SharedPreferenceUtil.getSharedPreference(context, "userID"));
        tv_user_name.setText(SharedPreferenceUtil.getSharedPreference(context, "userName"));
        tv_year.setText(SharedPreferenceUtil.getSharedPreference(context, "schoolYear"));
        tv_semester.setText(SharedPreferenceUtil.getSharedPreference(context, "semester"));
        tv_major.setText(SharedPreferenceUtil.getSharedPreference(context, "mainMajor"));
        tv_kind_major.setText(SharedPreferenceUtil.getSharedPreference(context, "_2ndMajorClass"));
        tv_second_major.setText(SharedPreferenceUtil.getSharedPreference(context, "_2ndMajor"));
        tv_toeic.setText(SharedPreferenceUtil.getSharedPreference(context, "toeic"));
        tv_thesis.setText(SharedPreferenceUtil.getSharedPreference(context, "thesis"));

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

}