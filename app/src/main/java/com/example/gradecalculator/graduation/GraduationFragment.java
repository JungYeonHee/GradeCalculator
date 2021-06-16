package com.example.gradecalculator.graduation;

import android.content.Context;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GraduationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class GraduationFragment extends Fragment {

    Context context;
    TextView myMainMajorCore, mainMajorCore,
            myMainMajorAdv, mainMajorAdv,
            myMinMajor, minMajor,
            myAdvMajorCore, advMajorCore,
            myAdvMajorAdv, advMajorAdv,
            myDblMajorCore, dblMajorCore,
            myDblMajorAdv, dblMajorAdv,
            myTotalMajor, totalMajor,
            myCommonLiberal, commonLiberal,
            myCoreLiberal, coreLiberal,
            myCareerLiberal, careerLiberal,
            myGeneralLiberal,
            myTotalLiberal, totalLiberal,
            myTotal, total,
            myToeic, toeic,
            myThesis, thesis;
    RequestQueue queue;
    RequestQueue queue2;
    RequestQueue queue3;
    RequestQueue queue4;
    RequestQueue queue5;
    RequestQueue queue_2;
    RequestQueue queue_3;
    RequestQueue queue_4;
    RequestQueue queue_5;


// TODO: Rename parameter arguments, choose names that match


    public GraduationFragment() {
// Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static GraduationFragment newInstance() {
        GraduationFragment fragment = new GraduationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            context = getActivity().getApplicationContext();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        View view = inflater.inflate(R.layout.fragment_graduation, container, false);
        myMainMajorCore = view.findViewById(R.id.myMainMajorCore);
        mainMajorCore = view.findViewById(R.id.mainMajorCore);
        myMainMajorAdv = view.findViewById(R.id.myMainMajorAdv);
        mainMajorAdv = view.findViewById(R.id.mainMajorAdv);
        myMinMajor = view.findViewById(R.id.myMinMajor);
        minMajor = view.findViewById(R.id.minMajor);
        myAdvMajorCore = view.findViewById(R.id.myAdvMajorCore);
        advMajorCore = view.findViewById(R.id.advMajorCore);
        myAdvMajorAdv = view.findViewById(R.id.myAdvMajorAdv);
        advMajorAdv = view.findViewById(R.id.advMajorAdv);
        myDblMajorCore = view.findViewById(R.id.myDblMajorCore);
        dblMajorCore = view.findViewById(R.id.dblMajorCore);
        myDblMajorAdv = view.findViewById(R.id.myDblMajorAdv);
        dblMajorAdv = view.findViewById(R.id.dblMajorAdv);
        myTotalMajor = view.findViewById(R.id.myTotalMajor);
        totalMajor = view.findViewById(R.id.totalMajor);
        myCommonLiberal = view.findViewById(R.id.myCommonLiberal);
        commonLiberal = view.findViewById(R.id.commonLiberal);
        myCoreLiberal = view.findViewById(R.id.myCoreLiberal);
        coreLiberal = view.findViewById(R.id.coreLiberal);
        myCareerLiberal = view.findViewById(R.id.myCareerLiberal);
        careerLiberal = view.findViewById(R.id.careerLiberal);
        myGeneralLiberal = view.findViewById(R.id.myGeneralLiberal);
        myTotalLiberal = view.findViewById(R.id.myTotalLiberal);
        totalLiberal = view.findViewById(R.id.totalLiberal);
        myTotal = view.findViewById(R.id.myTotal);
        total = view.findViewById(R.id.total);
        myToeic = view.findViewById(R.id.myToeic);
        toeic = view.findViewById(R.id.toeic);
        myThesis = view.findViewById(R.id.myThesis);
        thesis = view.findViewById(R.id.thesis);

        queue = Volley.newRequestQueue(context);
        queue2 = Volley.newRequestQueue(context);
        queue3 = Volley.newRequestQueue(context);
        queue4 = Volley.newRequestQueue(context);
        queue5 = Volley.newRequestQueue(context);
        queue_2 = Volley.newRequestQueue(context);
        queue_3 = Volley.newRequestQueue(context);
        queue_4 = Volley.newRequestQueue(context);
        queue_5 = Volley.newRequestQueue(context);

        String id = SharedPreferenceUtil.getSharedPreference(context, "userID");
        String _2ndMajorClass = SharedPreferenceUtil.getSharedPreference(context,"_2ndMajorClass");

        String URL = "http://ec2-52-79-221-73.ap-northeast-2.compute.amazonaws.com/graduationReq.php?userID="+id;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            boolean success = jsonObject.getBoolean("success");
                                String str_toeic = jsonObject.getString("toeicPassScore");
                                String str_thesis = jsonObject.getString("thesisPass");
                                String str_mainMajorCore = jsonObject.getString("mainMajorCoreCredit");
                                String str_mainMajorAdv = jsonObject.getString("mainMajorAdvCredit");
                                String str_advMajorCore = jsonObject.getString("advMajorCoreCredit");
                                String str_advMajorAdv = jsonObject.getString("advMajorAdvCredit");
                                String str_minMajor = jsonObject.getString("minMajorCredit");
                                String str_dblMajorCore = jsonObject.getString("dblMajorCoreCredit");
                                String str_dblMajorAdv = jsonObject.getString("dblMajorAdvCredit");

                                String str_commonLiberal = jsonObject.getString("CommonLiberalCredit");
                                String str_coreLiberal = jsonObject.getString("CoreLiberalCredit");
                                String str_careerLiberal = jsonObject.getString("careerLiberalCredit");
                                String str_total = jsonObject.getString("TotalCredit");

                                int int_totalLiberal;
                                if (str_careerLiberal.equals("null")){
                                    int_totalLiberal = Integer.parseInt(str_commonLiberal) + Integer.parseInt(str_coreLiberal); }
                                else {
                                    int_totalLiberal = Integer.parseInt(str_commonLiberal) + Integer.parseInt(str_coreLiberal) + Integer.parseInt(str_careerLiberal); }

                                String str_totalLiberal = Integer.toString(int_totalLiberal);

                                int int_totalMajor;
                                if(_2ndMajorClass.equals("부전공") )
                                    int_totalMajor = Integer.parseInt(str_mainMajorCore) + Integer.parseInt(str_mainMajorAdv) + Integer.parseInt(str_minMajor);
                                else if(_2ndMajorClass.equals("복수전공"))
                                    int_totalMajor = Integer.parseInt(str_mainMajorCore) + Integer.parseInt(str_mainMajorAdv) + Integer.parseInt(str_dblMajorCore) + Integer.parseInt(str_dblMajorAdv);
                                else
                                    int_totalMajor = Integer.parseInt(str_mainMajorCore) + Integer.parseInt(str_mainMajorAdv) + Integer.parseInt(str_advMajorCore) + Integer.parseInt(str_advMajorAdv);
                                String str_totalMajor = Integer.toString(int_totalMajor);

                                mainMajorCore.setText(str_mainMajorCore);
                                mainMajorAdv.setText(str_mainMajorAdv);
                                minMajor.setText(str_minMajor);
                                advMajorCore.setText(str_advMajorCore);
                                advMajorAdv.setText(str_advMajorAdv);
                                dblMajorCore.setText(str_dblMajorCore);
                                dblMajorAdv.setText(str_dblMajorAdv);
                                commonLiberal.setText(str_commonLiberal);
                                coreLiberal.setText(str_coreLiberal);
                                careerLiberal.setText(str_careerLiberal);
                                total.setText(str_total);
                                toeic.setText(str_toeic);
                                thesis.setText(str_thesis);
                                totalLiberal.setText(str_totalLiberal);
                                totalMajor.setText(str_totalMajor);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });

        //주전공-핵심전공 수강학점 구하기
        String MajorOrLiberal = "전공";
        String subjectClass = "핵심";

        String URL2 = "http://ec2-52-79-221-73.ap-northeast-2.compute.amazonaws.com/myGraduation.php?userID="+id+"&MajorOrLiberal="+MajorOrLiberal+"&subjectClass="+subjectClass;

        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, URL2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String str_sumCredit = jsonObject.getString("sumCredit");
                            myMainMajorCore.setText(str_sumCredit);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (NumberFormatException ex){
                            ex.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });


        //주전공-심화전공 수강학점 구하기
        String subjectClass3 = "심화";

        String URL3 = "http://ec2-52-79-221-73.ap-northeast-2.compute.amazonaws.com/myGraduation.php?userID="+id+"&MajorOrLiberal="+MajorOrLiberal+"&subjectClass="+subjectClass3;

        StringRequest stringRequest3 = new StringRequest(Request.Method.GET, URL3,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String str_sumCredit = jsonObject.getString("sumCredit");
                            myMainMajorAdv.setText(str_sumCredit);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (NumberFormatException ex){
                            ex.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        //부, 복수전공-핵심, 심화전공-핵심 수강학점 구하기
        String subMajorClass = SharedPreferenceUtil.getSharedPreference(context, "_2ndMajorClass");
        String subjectClass4;
        if (subMajorClass.equals("부전공")){
            subjectClass4 = "부";}
        else if (subMajorClass.equals("복수전공") ){
            subjectClass4 = "복수-핵심";
        }
        else{   //심화전공
            subjectClass4 = "심화-핵심";
        }

        String URL4 = "http://ec2-52-79-221-73.ap-northeast-2.compute.amazonaws.com/myGraduation.php?userID="+id+"&MajorOrLiberal="+MajorOrLiberal+"&subjectClass="+subjectClass4;

        StringRequest stringRequest4 = new StringRequest(Request.Method.GET, URL4,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String str_sumCredit = jsonObject.getString("sumCredit");
                            if (subMajorClass.equals("부전공") ){
                                myMinMajor.setText(str_sumCredit);
                                int int_myMainMajorCore = Integer.parseInt(myMainMajorCore.getText().toString());
                                int int_myMainMajorAdv = Integer.parseInt(myMainMajorAdv.getText().toString());
                                int int_myTotalMajor;
                                if (subMajorClass.equals("부전공")){
                                    int int_myMinMajor = Integer.parseInt(myMinMajor.getText().toString());
                                    int_myTotalMajor = int_myMainMajorCore + int_myMainMajorAdv + int_myMinMajor;
                                    String str_myTotalMajor = Integer.toString(int_myTotalMajor);
                                    myTotalMajor.setText(str_myTotalMajor);
                                }}
                            else if (subMajorClass.equals("복수전공") ){
                                myDblMajorCore.setText(str_sumCredit);}
                            else{   //심화전공
                                myAdvMajorCore.setText(str_sumCredit);}
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (NumberFormatException ex){
                            ex.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });


        //복수-심화, 심화-심화, 전공총점 수강 학점 구하기
        String subjectClass5 = "null";
        if (subMajorClass.equals("복수전공")){ //복수전공
            subjectClass5 = "복수-심화";
        }
        if (subMajorClass.equals("심화전공")){ //심화전공
            subjectClass5 = "심화-심화";
        }

        String URL5 = "http://ec2-52-79-221-73.ap-northeast-2.compute.amazonaws.com/myGraduation.php?userID="+id+"&MajorOrLiberal="+MajorOrLiberal+"&subjectClass="+subjectClass5;
        StringRequest stringRequest5 = new StringRequest(Request.Method.GET, URL5,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String str_sumCredit = jsonObject.getString("sumCredit");

                            int int_myMainMajorCore = Integer.parseInt(myMainMajorCore.getText().toString());
                            int int_myMainMajorAdv = Integer.parseInt(myMainMajorAdv.getText().toString());
                            int int_myTotalMajor;
                            if (subMajorClass.equals("복수전공")){ //복수전공
                                myDblMajorAdv.setText(str_sumCredit);
                                int int_myDblMajorCore = Integer.parseInt(myDblMajorCore.getText().toString());
                                int int_myDblMajorAdv = Integer.parseInt(myDblMajorAdv.getText().toString());
                                int_myTotalMajor = int_myMainMajorCore + int_myMainMajorAdv + int_myDblMajorCore + int_myDblMajorAdv;
                                String str_myTotalMajor = Integer.toString(int_myTotalMajor);
                                myTotalMajor.setText(str_myTotalMajor);
                            }
                            if (subMajorClass.equals("심화전공")){     //심화전공
                                myAdvMajorAdv.setText(str_sumCredit);
                                int int_myAdvMajorCore = Integer.parseInt(myAdvMajorCore.getText().toString());
                                int int_myAdvMajorAdv = Integer.parseInt(myAdvMajorAdv.getText().toString());
                                int_myTotalMajor = int_myMainMajorCore + int_myMainMajorAdv + int_myAdvMajorCore + int_myAdvMajorAdv;
                                String str_myTotalMajor = Integer.toString(int_myTotalMajor);
                                myTotalMajor.setText(str_myTotalMajor);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (NumberFormatException ex){
                            ex.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        //공통교양 수강학점 구하기
        String MajorOrLiberal2 = "교양";
        String subjectArea_2 = "공통";

        String URL_2 = "http://ec2-52-79-221-73.ap-northeast-2.compute.amazonaws.com/myGraduation2.php?userID="+id+"&MajorOrLiberal="+MajorOrLiberal2+"&subjectArea="+subjectArea_2;

        StringRequest stringRequest_2 = new StringRequest(Request.Method.GET, URL_2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String str_sumCredit = jsonObject.getString("sumCredit");
                            myCommonLiberal.setText(str_sumCredit);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (NumberFormatException ex){
                            ex.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        //핵심교양 수강학점 구하기
        String URL_3 = "http://ec2-52-79-221-73.ap-northeast-2.compute.amazonaws.com/myGraduation3.php?userID="+id+"&MajorOrLiberal="+MajorOrLiberal2;

        StringRequest stringRequest_3 = new StringRequest(Request.Method.GET, URL_3,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String str_sumCredit = jsonObject.getString("sumCredit");
                            myCoreLiberal.setText(str_sumCredit);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (NumberFormatException ex){
                            ex.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        //진로소양 수강학점 구하기
        String subjectArea_4 = "진로";

        String URL_4 = "http://ec2-52-79-221-73.ap-northeast-2.compute.amazonaws.com/myGraduation2.php?userID="+id+"&MajorOrLiberal="+MajorOrLiberal2+"&subjectArea="+subjectArea_4;

        StringRequest stringRequest_4 = new StringRequest(Request.Method.GET, URL_4,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String str_sumCredit = jsonObject.getString("sumCredit");
                            myCareerLiberal.setText(str_sumCredit);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (NumberFormatException ex){
                            ex.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        //일반교양, 교양총점, 졸업총점 수강학점 구하기
        String subjectArea_5 = "일반";

        String URL_5 = "http://ec2-52-79-221-73.ap-northeast-2.compute.amazonaws.com/myGraduation2.php?userID="+id+"&MajorOrLiberal="+MajorOrLiberal2+"&subjectArea="+subjectArea_5;
        StringRequest stringRequest_5 = new StringRequest(Request.Method.GET, URL_5,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String str_sumCredit = jsonObject.getString("sumCredit");
                            myGeneralLiberal.setText(str_sumCredit);

                            int int_myCommonLiberal = Integer.parseInt(myCommonLiberal.getText().toString());
                            int int_myCoreLiberal = Integer.parseInt(myCoreLiberal.getText().toString());
                            int int_myGeneralLiberal = Integer.parseInt(myGeneralLiberal.getText().toString());

                            int int_myTotalLiberal;
                            if (careerLiberal.getText().toString().equals("null")){
                                int_myTotalLiberal = int_myCommonLiberal + int_myCoreLiberal + int_myGeneralLiberal;
                                String str_myTotalLiberal = Integer.toString(int_myTotalLiberal);
                                myTotalLiberal.setText(str_myTotalLiberal);
                                }
                            else{
                                int_myTotalLiberal = int_myCommonLiberal + int_myCoreLiberal + int_myGeneralLiberal + Integer.parseInt(myCareerLiberal.getText().toString());
                                String str_myTotalLiberal = Integer.toString(int_myTotalLiberal);
                                myTotalLiberal.setText(str_myTotalLiberal);
                            }

                            int int_myTotal = int_myTotalLiberal + Integer.parseInt(myTotalMajor.getText().toString());
                            String str_myTotal = Integer.toString(int_myTotal);
                            myTotal.setText(str_myTotal);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (NumberFormatException ex){
                            ex.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });


        String toeic = SharedPreferenceUtil.getSharedPreference(context,"toeic");
        String thesis = SharedPreferenceUtil.getSharedPreference(context,"thesis");
        myToeic.setText(toeic);
        myThesis.setText(thesis);


        queue.add(stringRequest);
        queue2.add(stringRequest2);
        queue3.add(stringRequest3);
        queue4.add(stringRequest4);
        queue4.add(stringRequest5);
        queue_2.add(stringRequest_2);
        queue_3.add(stringRequest_3);
        queue_4.add(stringRequest_4);
        queue_5.add(stringRequest_5);
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
        MySingleton.getInstance(context).addToRequestQueue(stringRequest2);
        MySingleton.getInstance(context).addToRequestQueue(stringRequest3);
        MySingleton.getInstance(context).addToRequestQueue(stringRequest4);
        MySingleton.getInstance(context).addToRequestQueue(stringRequest5);
        MySingleton.getInstance(context).addToRequestQueue(stringRequest_2);
        MySingleton.getInstance(context).addToRequestQueue(stringRequest_3);
        MySingleton.getInstance(context).addToRequestQueue(stringRequest_4);
        MySingleton.getInstance(context).addToRequestQueue(stringRequest_5);
        return view;
    }
}