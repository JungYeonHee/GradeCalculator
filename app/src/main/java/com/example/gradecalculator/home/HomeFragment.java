package com.example.gradecalculator.home;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.gradecalculator.MySingleton;
import com.example.gradecalculator.R;
import com.example.gradecalculator.SharedPreferenceUtil;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private LineChart lineChart;
    private LineChart lineChart2;
    private PieChart pieChart;
    Context context;


 //   private ArrayList<ILineDataSet> DataSets = new ArrayList<>();
    //private LineDataSet lineDataSet;
   // private ArrayList<Entry> totalGrade = new ArrayList<>();
    /* 전공 그래프
       private ArrayList<Entry> majorGrade = new ArrayList<>();*/
   // private LineData data = new LineData(DataSets);

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // setContentView(R.layout.activity_main);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        lineChart = view.findViewById(R.id.linechart);
        lineChart2 = view.findViewById(R.id.linechart2);
        pieChart = view.findViewById(R.id.pie_chart);
        context = getActivity().getApplicationContext();




        //라인차트
        setupLineChartData(context, SharedPreferenceUtil.getSharedPreference(context, "userID"));
       // System.out.println("전체 성공");
        setupLineChartDataMajor(context, SharedPreferenceUtil.getSharedPreference(context, "userID"));
      //  System.out.println("전공 성공");
        // 파이차트
        setupPieChartData(context, SharedPreferenceUtil.getSharedPreference(context, "userID")); // 파이 차트 데이터

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
    }

    public void loadLineChartData(float[][] array){
        int count  = 0;
        ArrayList<Entry> entries = new ArrayList<>();
        for(int i = 0; i < array.length; i++) {
            for (int j = 0; j < 2; j++) {
                entries.add(new Entry(count, array[i][j]));
                count++;

            }
        }
        setupLineChart(entries);
    }


    public void loadLineChartDataMajor(float[][] array2){
        int count  = 0;
        ArrayList<Entry> entries2 = new ArrayList<>();
        for(int i = 0; i < array2.length; i++) {
            for (int j = 0; j < 2; j++) {
                entries2.add(new Entry(count, array2[i][j]));
                count++;

            }
        }
        setupLineChartMajor(entries2);
    }

    public void setupLineChart(ArrayList<Entry> entry1){
        LineData chartData = new LineData();
        LineDataSet set1 = new LineDataSet(entry1, "전체");
        chartData.addDataSet(set1);

        lineChart.setData(chartData);
        lineChart.invalidate();
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);


    }

    public void setupLineChartMajor(ArrayList<Entry> entry2){
        LineData chartData2 = new LineData();
        LineDataSet set2 = new LineDataSet(entry2, "전공");
        chartData2.addDataSet(set2);

        lineChart2.setData(chartData2);
        lineChart2.invalidate();
        set2.setColor(Color.RED);
        set2.setCircleColor(Color.RED);

        XAxis xAxis = lineChart2.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        YAxis yRAxis = lineChart2.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);


    }


    public void setupPieChart(int sum) {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(18);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterTextSize(12);
        pieChart.getDescription().setEnabled(false);

        if(sum != 0){
            pieChart.setCenterText("성적분포");
        }else{
            pieChart.setCenterText("등록된 성적이 없습니다.");
        }

        pieChart.setDrawSliceText(false);

        Legend l = pieChart.getLegend();

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("A");
        labels.add("B");
        labels.add("C");
        labels.add("D");
        labels.add("F");

        l.setDrawInside(false);
        l.setEnabled(true);
    }


    private int loadPieChartData(ArrayList<Integer> list) {


        ArrayList<PieEntry> entries = new ArrayList<>();

        String[] grade = {"A", "B", "C", "D", "F"};
        int sum = 0;

        for(int i = 0; i < list.size(); i++){
            if(list.get(i) != 0){
                entries.add(new PieEntry(Float.parseFloat(Integer.toString(list.get(i))), grade[i]));
                sum++;
            }
        }

        setupPieChart(sum);

        // add a lot of colors
        ArrayList<Integer> colors = new ArrayList<>();
        for (int color : ColorTemplate.VORDIPLOM_COLORS) { colors.add(color); }
        for (int color : ColorTemplate.JOYFUL_COLORS) { colors.add(color); }
        for (int color : ColorTemplate.COLORFUL_COLORS) { colors.add(color); }
        for (int color : ColorTemplate.LIBERTY_COLORS) { colors.add(color); }
        for (int color : ColorTemplate.PASTEL_COLORS) { colors.add(color); }
        colors.add(ColorTemplate.getHoloBlue());

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(2f);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);
        data.setValueTypeface(Typeface.DEFAULT_BOLD);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);

        return sum;
    }

    public ArrayList<Integer> setupPieChartData(Context context, String id){
        final int[] cnt_a = {0};
        final int[] cnt_b = {0};
        final int[] cnt_c = {0};
        final int[] cnt_d = {0};
        final int[] cnt_f = {0};

        ArrayList<Integer> selectList = new ArrayList<Integer>();

        String url = "http://ec2-52-79-221-73.ap-northeast-2.compute.amazonaws.com/gradeRatio.php?userID="+id;

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
                                String grade = jsonObject.getString("grade");
                                String totalGrade = jsonObject.getString("total");

                                if(grade.equals("A ") || grade.equals("A-") || grade.equals("A0")){
                                    cnt_a[0] += Integer.parseInt(totalGrade);
                                    //Log.d("za",  Integer.toString(cnt_a[0]));
                                }else if(grade.equals("B ") || grade.equals("B-") || grade.equals("B0")){
                                    cnt_b[0] += Integer.parseInt(totalGrade);
                                }else if(grade.equals("C ") || grade.equals("C-") || grade.equals("C0")){
                                    cnt_c[0] += Integer.parseInt(totalGrade);
                                }else if(grade.equals("D ") || grade.equals("D-") || grade.equals("D0")){
                                    cnt_d[0] += Integer.parseInt(totalGrade);
                                }else{
                                    cnt_f[0] += Integer.parseInt(totalGrade);
                                }
                            }
                            selectList.add(cnt_a[0]);
                            selectList.add(cnt_b[0]);
                            selectList.add(cnt_c[0]);
                            selectList.add(cnt_d[0]);
                            selectList.add(cnt_f[0]);

                            // 파이차트 데이터 불러오기
                            loadPieChartData(selectList);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "등급 비율 조회 실패", Toast.LENGTH_SHORT).show();
            }
        }){
        };
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);

        return selectList;
    }
    public void setupLineChartData(Context context, String id){

        ArrayList<Float> selectList = new ArrayList<Float>();
        float [][] array = new float[4][2];

        String url = "http://ec2-52-79-221-73.ap-northeast-2.compute.amazonaws.com/graph.php?userID="+id;

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
                                String year = jsonObject.getString("year");
                                String semester = jsonObject.getString("semester");
                                String totalAvg = jsonObject.getString("totalAvg");
                                array[Integer.parseInt(year)-1][Integer.parseInt(semester)-1] = Float.parseFloat(totalAvg);


                            }

                            // 라인차트 데이터 불러오기
                            loadLineChartData(array);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "등급 비율 조회 실패", Toast.LENGTH_SHORT).show();
            }
        }){
        };
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }

    public void setupLineChartDataMajor(Context context, String id){

       // ArrayList<Float> selectList = new ArrayList<Float>();
        float [][] array2 = new float[4][2];

        String url = "http://ec2-52-79-221-73.ap-northeast-2.compute.amazonaws.com/graph2.php?userID="+id;

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
                                String year = jsonObject.getString("year");
                                String semester = jsonObject.getString("semester");
                                String majorAvg = jsonObject.getString("majorAvg");
                                array2[Integer.parseInt(year)-1][Integer.parseInt(semester)-1] = Float.parseFloat(majorAvg);


                            }
                            System.out.println("함수호출 ok?");

                            // 라인차트 데이터 불러오기
                            loadLineChartDataMajor(array2);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "등급 비율 조회 실패", Toast.LENGTH_SHORT).show();
            }
        }){
        };
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }
}