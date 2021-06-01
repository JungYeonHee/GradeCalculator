package com.example.gradecalculator.management;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.gradecalculator.MainActivity;
import com.example.gradecalculator.MySingleton;
import com.example.gradecalculator.R;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.HashMap;


public class ManagementFragment extends Fragment {

    TabLayout tabLayout;

    ManageRvAdapter manageRvAdapter; // recycle view adapter
    RecyclerView recylce_Recylcerview; // recycle view

    ArrayList<ManageClass> totalManageClasses1_1;
    ArrayList<ManageClass> totalManageClasses1_2;
    ArrayList<ManageClass> totalManageClasses2_1;
    ArrayList<ManageClass> totalManageClasses2_2;
    ArrayList<ManageClass> totalManageClasses3_1;
    ArrayList<ManageClass> totalManageClasses3_2;
    ArrayList<ManageClass> totalManageClasses4_1;
    ArrayList<ManageClass> totalManageClasses4_2;

    Button btn_add;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    Context context;

    TextView allAverage, majorAverage;

    public static ManagementFragment newInstance() {
        ManagementFragment fragment = new ManagementFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_management, container, false);

        context = getActivity().getApplicationContext();

        allAverage = view.findViewById(R.id.txt_user_all_average);
        majorAverage = view.findViewById(R.id.txt_user_major_average);



        totalManageClasses1_1 = new ArrayList<>();
        totalManageClasses1_1 = selectClass(context, "20181046", "1", "1", totalManageClasses1_1);

        totalManageClasses1_2 = new ArrayList<>();
        totalManageClasses1_2 = selectClass(context, "20181046", "1", "2", totalManageClasses1_2);

        totalManageClasses2_1 = new ArrayList<>();
        totalManageClasses2_1 = selectClass(context, "20181046", "2", "1", totalManageClasses2_1);

        totalManageClasses2_2 = new ArrayList<>();
        totalManageClasses2_2 = selectClass(context, "20181046", "2", "2", totalManageClasses2_2);

        totalManageClasses3_1 = new ArrayList<>();
        totalManageClasses3_1 = selectClass(context, "20181046", "3", "1", totalManageClasses3_1);

        totalManageClasses3_2 = new ArrayList<>();
        totalManageClasses3_2 = selectClass(context, "20181046", "3", "2", totalManageClasses3_2);

        totalManageClasses4_1 = new ArrayList<>();
        totalManageClasses4_1 = selectClass(context, "20181046", "4", "1", totalManageClasses4_1);

        totalManageClasses4_2 = new ArrayList<>();
        totalManageClasses4_2 = selectClass(context, "20181046", "4", "2", totalManageClasses4_2);

        recylce_Recylcerview = view.findViewById(R.id.rv_manage);
        recylce_Recylcerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        manageRvAdapter = new ManageRvAdapter(totalManageClasses1_1); // default
        recylce_Recylcerview.setAdapter(manageRvAdapter);
        recylce_Recylcerview.setNestedScrollingEnabled(false);


        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        editor.putString("tabYear", "1");
        editor.putString("tabSemester", "1");
        editor.commit();

        gpa(context,"20181046", "1", "1");
        gpaMajor(context,"20181046", "1", "1");

        tabLayout = view.findViewById(R.id.tab_layout_management);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        editor.putString("tabYear", "1");
                        editor.putString("tabSemester", "1");
                        editor.commit();

                        gpa(context,"20181046", "1", "1");
                        gpaMajor(context,"20181046", "1", "1");

                        recylce_Recylcerview.setAdapter(new ManageRvAdapter(totalManageClasses1_1));
                        break;
                    case 1:
                        editor.putString("tabYear", "1");
                        editor.putString("tabSemester", "2");
                        editor.commit();

                        gpa(context,"20181046", "1", "2");
                        gpaMajor(context,"20181046", "1", "2");

                        recylce_Recylcerview.setAdapter(new ManageRvAdapter(totalManageClasses1_2));
                        break;
                    case 2:
                        editor.putString("tabYear", "2");
                        editor.putString("tabSemester", "1");
                        editor.commit();

                        gpa(context,"20181046", "2", "1");
                        gpaMajor(context,"20181046", "2", "1");

                        recylce_Recylcerview.setAdapter(new ManageRvAdapter(totalManageClasses2_1));
                        break;
                    case 3:
                        editor.putString("tabYear", "2");
                        editor.putString("tabSemester", "2");
                        editor.commit();

                        gpa(context,"20181046", "2", "2");
                        gpaMajor(context,"20181046", "2", "2");

                        recylce_Recylcerview.setAdapter(new ManageRvAdapter(totalManageClasses2_2));
                        break;
                    case 4:
                        editor.putString("tabYear", "3");
                        editor.putString("tabSemester", "1");
                        editor.commit();

                        gpa(context,"20181046", "3", "1");
                        gpaMajor(context,"20181046", "3", "1");

                        recylce_Recylcerview.setAdapter(new ManageRvAdapter(totalManageClasses3_1));
                        break;
                    case 5:
                        editor.putString("tabYear", "3");
                        editor.putString("tabSemester", "2");
                        editor.commit();

                        gpa(context,"20181046", "3", "2");
                        gpaMajor(context,"20181046", "3", "2");

                        recylce_Recylcerview.setAdapter(new ManageRvAdapter(totalManageClasses3_2));
                        break;
                    case 6:
                        editor.putString("tabYear", "4");
                        editor.putString("tabSemester", "1");
                        editor.commit();

                        gpa(context,"20181046", "4", "1");
                        gpaMajor(context,"20181046", "4", "1");

                        recylce_Recylcerview.setAdapter(new ManageRvAdapter(totalManageClasses4_1));
                        break;
                    case 7:
                        editor.putString("tabYear", "4");
                        editor.putString("tabSemester", "2");
                        editor.commit();

                        gpa(context,"20181046", "4", "2");
                        gpaMajor(context,"20181046", "4", "2");

                        recylce_Recylcerview.setAdapter(new ManageRvAdapter(totalManageClasses4_2));
                        break;
                }
            }

                @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btn_add = view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ManageAddActivity.class);
                intent.putExtra("tabYear", sharedPref.getString("tabYear","1"));
                intent.putExtra("tabSemester", sharedPref.getString("tabSemester","1"));
                startActivity(intent);
            }
        });

        return view;
    }

    public ArrayList<ManageClass> selectClass(Context context, String id, String year, String semester, ArrayList<ManageClass> mngClass){
        HashMap<String, ArrayList> selectMap = new HashMap<String, ArrayList>();
        ArrayList<String> selectList = new ArrayList<String>();

        String url = "http://ec2-52-79-221-73.ap-northeast-2.compute.amazonaws.com/manageRetrieve.php?userID="+id+"&year="+year+"&semester="+semester;

        // Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String strResp = response;

                        try {
                            JSONArray jsonArray = new JSONArray(strResp);
                            for(int i=0; i<jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String name = jsonObject.getString("subjectName");
                                String kind = jsonObject.getString("MajorOrLiberal");
                                String kind_major = jsonObject.getString("subjectClass");
                                String kind_domain = jsonObject.getString("subjectArea");
                                String credit = jsonObject.getString("credit");
                                String grade = jsonObject.getString("grade");
                                String retake = jsonObject.getString("retake");

                                if(grade.equals("A ")){
                                    grade = "A+";
                                }else if(grade.equals("B ")){
                                    grade = "B+";
                                }else if(grade.equals("C ")){
                                    grade = "C+";
                                }else if(grade.equals("D ")){
                                    grade = "D+";
                                }

                                if(kind_domain.equals("0")){
                                    kind_domain = "-";
                                }

                                if(kind_major.equals("0")){
                                    kind_major = "-";
                                }

                                if(retake.equals("0")){
                                    retake = "-";
                                }else if(retake.equals("1")){
                                    retake = "0";
                                }


                                Log.d("abcd",grade);
                                mngClass.add(new ManageClass(name, kind, kind_major, kind_domain, credit, grade, retake));
                                manageRvAdapter.notifyDataSetChanged();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "과목 등록을 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
        }){
        };

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);

        return mngClass;
    }


    public void gpa(Context context, String id, String year, String semester){

        String url = "http://ec2-52-79-221-73.ap-northeast-2.compute.amazonaws.com/gpa.php?userID="+id+"&year="+year+"&semester="+semester;

        // Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            allAverage.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
            }
        }){};
        // Get a RequestQueue && Add a request (in this example, called stringRequest) to your RequestQueue.
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void gpaMajor(Context context, String id, String year, String semester){

        String url = "http://ec2-52-79-221-73.ap-northeast-2.compute.amazonaws.com/gpaMajor.php?userID="+id+"&year="+year+"&semester="+semester;

        // Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            majorAverage.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
            }
        }){};
        // Get a RequestQueue && Add a request (in this example, called stringRequest) to your RequestQueue.
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
}