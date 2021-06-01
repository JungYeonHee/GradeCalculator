package com.example.gradecalculator.home;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.gradecalculator.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
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

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private LineChart lineChart;
    private PieChart pieChart;


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

    // TODO: Rename parameter arguments, choose names that match

    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
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

        setupPieChart();
        loadPieChartData();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
    }

    public void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(18);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterTextSize(12);
        //pieChart.setHoleRadius(40);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("성적분포");
        pieChart.setDrawSliceText(false);

        Legend l = pieChart.getLegend();
        //l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        //l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        //l.setOrientation(Legend.LegendOrientation.VERTICAL);

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("A");
        labels.add("B");
        labels.add("C");
        labels.add("D");
        labels.add("F");

        l.setDrawInside(false);
        l.setEnabled(true);
    }

    private void loadPieChartData() {
        ArrayList<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(20f, "A"));
        entries.add(new PieEntry(20f, "B"));
        entries.add(new PieEntry(20f, "C"));
        entries.add(new PieEntry(20f, "D"));
        entries.add(new PieEntry(20f, "F"));

        // add a lot of colors
        ArrayList<Integer> colors = new ArrayList<>();
        for (int color : ColorTemplate.VORDIPLOM_COLORS) { colors.add(color); }
        for (int color : ColorTemplate.JOYFUL_COLORS) { colors.add(color); }
        for (int color : ColorTemplate.COLORFUL_COLORS) { colors.add(color); }
        for (int color : ColorTemplate.LIBERTY_COLORS) { colors.add(color); }
        for (int color : ColorTemplate.PASTEL_COLORS) { colors.add(color); }
        colors.add(ColorTemplate.getHoloBlue());


        int[] zoneColors = {
                Color.rgb(164, 164, 164),
                Color.rgb(54, 155, 227),
                Color.rgb(42, 160, 84),
                Color.rgb(242, 171, 21),
                Color.rgb(217, 41, 61)
        };

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
    }
}