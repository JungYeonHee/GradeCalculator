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
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
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
    private PieChart pieChart;
    Context context;


    private ArrayList<ILineDataSet> DataSets = new ArrayList<>();
    private LineDataSet lineDataSet;
    private ArrayList<Entry> totalGrade = new ArrayList<>();
    /* 전공 그래프
       private ArrayList<Entry> majorGrade = new ArrayList<>();*/
    private LineData data = new LineData(DataSets);

    public void sendInput(float value) {

        data = lineChart.getData();
        ILineDataSet xSet = data.getDataSetByIndex(0);

        data.addEntry(new Entry(xSet.getEntryCount(), value), 0);

        //lineDataSet5.notifyDataSetChanged();
        //lineDataSets.add(lineDataSet5);
        data.notifyDataChanged();
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
        lineChart.moveViewToX(data.getEntryCount());
    }

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
        pieChart = view.findViewById(R.id.pie_chart);
        context = getActivity().getApplicationContext();

        //x축
        ArrayList<String> xLabel = new ArrayList<>();
        xLabel.add("1-1");
        xLabel.add("1-2");
        xLabel.add("2-1");
        xLabel.add("2-2");
        xLabel.add("3-1");
        xLabel.add("3-2");
        xLabel.add("4-1");
        xLabel.add("4-2");

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(10f);
        xAxis.setDrawGridLines(true);
        xAxis.setValueFormatter(new IndexAxisValueFormatter() {
            public String getFormattedValue(float value, AxisBase axis) {
                return xLabel.get((int) value);
            }
        });


        // 파이차트
        setupPieChartData(context, SharedPreferenceUtil.getSharedPreference(context, "userID")); // 파이 차트 데이터

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
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
}