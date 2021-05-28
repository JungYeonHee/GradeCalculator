package com.example.gradecalculator.mypage;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gradecalculator.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class GradeChart extends AppCompatActivity {

    private LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chart = findViewById(R.id.linechart);

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < 7; i++) {

            float val = (float) (Math.random() * 10);
            values.add(new Entry(i, val));
        }
        //전체 평점
        LineDataSet total;
                total = new LineDataSet(values, "Total");

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(total); // add the data sets

        // create a data object with the data sets
        LineData data = new LineData(dataSets);

        // black lines and points
        total.setColor(Color.BLACK);
        total.setCircleColor(Color.BLACK);
        ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("1-1"); xAxisLabel.add("1-2");
        xAxisLabel.add("2-1"); xAxisLabel.add("2-2");
        xAxisLabel.add("3-1"); xAxisLabel.add("3-2");
        xAxisLabel.add("4-1"); xAxisLabel.add("4-2");


        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xAxisLabel.get((int) value);

            }
        });

        // set data
        chart.setData(data);
    }

}