package com.example.gradecalculator.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.gradecalculator.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private LineChart lineChart;

    private ArrayList<ILineDataSet> DataSets = new ArrayList<>();
    private LineDataSet lineDataSet;
    private ArrayList<Entry> totalGrade = new ArrayList<>();
/* 전공 그래프
   private ArrayList<Entry> majorGrade = new ArrayList<>();*/
    private LineData data = new LineData(DataSets);

    public void sendInput(float value) {

        data = lineChart.getData();
        ILineDataSet xSet = data.getDataSetByIndex(0);

        data.addEntry(new Entry(xSet.getEntryCount(), value),0);

        //lineDataSet5.notifyDataSetChanged();
        //lineDataSets.add(lineDataSet5);
        data.notifyDataChanged();
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
        lineChart.moveViewToX(data.getEntryCount());
    }

    // TODO: Rename parameter arguments, choose names that match

    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        View view = inflater.inflate(R.layout.activity_main,container,false);
        lineChart = (LineChart) getView().findViewById(R.id.linechart);
      //x축
        ArrayList<String> xLabel = new ArrayList<>();
        xLabel.add("1-1"); xLabel.add("1-2");
        xLabel.add("2-1"); xLabel.add("2-2");
        xLabel.add("3-1"); xLabel.add("3-2");
        xLabel.add("4-1"); xLabel.add("4-2");

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(10f);
        xAxis.setDrawGridLines(true);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(){
           public String getFormattedValue(float value, AxisBase axis){
               return xLabel.get((int)value);
           }
        });


        return inflater.inflate(R.layout.fragment_home, container, false);


    }


}