package com.example.tiku5.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiku5.R;
import com.example.tiku5.adapter.CY_Adapter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2019-12-26
 */
public class Z_CYActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.barChart)
    BarChart barChart;
    @BindView(R.id.tv_month)
    TextView tvMonth;
    @BindView(R.id.expandList)
    ExpandableListView expandList;
    private List<BarEntry> entries = new ArrayList<>();
    private List<Integer> colors = new ArrayList<>();
    private List<String> xValues = new ArrayList<>();
    private Map<String, List<String>> map;
    private CY_Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cyc_layout);
        ButterKnife.bind(this);
        initView();
        setLineChart();
        initClick();
        initData(0);
    }

    private void initData(int index) {
        if (map == null) map = new HashMap<>();
        map.clear();
        List<String> strings1 = new ArrayList<>();
        List<String> strings2 = new ArrayList<>();
        List<String> strings3 = new ArrayList<>();
        switch (index) {
            case 0:
                strings1.add("原因:未按规定停车 地点:大学东路 时间:2019-08-15 12:02:30 消费:100");
                strings1.add("原因:超速行驶 地点:联想路 时间:2019-08-28 11:02:30 消费:60");
                strings2.add("原因:缴费 地点:医院路 时间:2019-08-01 18:52:40 消费:100");
                strings2.add("原因:缴费 地点:联想路 时间:2019-08-08 20:40:01 消费:100");
                strings3.add("原因:停车 地点:大学东路 时间:2019-08-30 22:02:00 消费:100");
                break;
            case 1:
                strings1.add("原因:闯红灯 地点:幸福路 时间:2019-09-15 12:02:30 消费:100");
                strings1.add("原因:超速行驶 地点:联想路 时间:2019-09-28 11:02:30 消费:100");
                strings2.add("原因:缴费 地点:医院路 时间:2019-09-01 18:52:40 消费:100");
                strings2.add("原因:缴费 地点:联想路 时间:2019-09-08 20:40:01 消费:100");
                strings2.add("原因:缴费 地点:学院路 时间:2019-09-18 22:02:00 消费:100");
                strings3.add("原因:停车 地点:大学东路 时间:2019-09-30 09:12:05 消费:100");
                strings3.add("原因:停车 地点:医院路 时间:2019-09-30 07:15:20 消费:100");
                strings3.add("原因:停车 地点:环城高速 时间:2019-09-30 04:05:02 消费:100");
                break;
            case 2:
                strings1.add("原因:未按规定停车 地点:医院路 时间:2019-10-15 12:02:30 消费:100");
                strings1.add("原因:超速行驶 地点:联想路 时间:2019-10-28 11:02:30 消费:100");
                strings1.add("原因:闯红灯 地点:幸福路 时间:2019-10-15 12:02:30 消费:100");
                strings2.add("原因:缴费 地点:医院路 时间:2019-10-01 18:52:40 消费:100");
                strings2.add("原因:缴费 地点:联想路 时间:2019-10-08 20:40:01 消费:100");
                strings2.add("原因:缴费 地点:学院路 时间:2019-10-18 22:02:00 消费:100");
                strings3.add("原因:停车 地点:大学东路 时间:2019-10-30 09:12:05 消费:100");
                strings3.add("原因:停车 地点:医院路 时间:2019-10-30 07:15:20 消费:100");
                strings3.add("原因:停车 地点:环城高速 时间:2019-10-30 04:05:02 消费:100");
                break;
            case 3:
                strings1.add("原因:超速行驶 地点:联想路 时间:2019-10-28 11:02:30 消费:100");
                strings1.add("原因:未按规定停车 地点:幸福路 时间:2019-10-15 12:02:30 消费:100");
                strings2.add("原因:缴费 地点:联想路 时间:2019-10-08 20:40:01 消费:100");
                strings2.add("原因:缴费 地点:医院路 时间:2019-10-01 18:52:40 消费:100");
                strings3.add("原因:停车 地点:大学东路 时间:2019-10-30 22:02:00 消费:100");
                strings1.add("原因:停车 地点:医院路 时间:2019-10-15 12:02:30 消费:100");
                strings1.add("原因:停车 地点:幸福路 时间:2019-10-15 12:02:30 消费:100");
                break;
            case 4:
                strings1.add("原因:未按规定停车 地点:大学东路 时间:2019-08-15 消费:100");
                strings2.add("原因:缴费 地点:医院路 时间:2019-08-01 消费:100");
                strings3.add("原因:停车 地点:大学东路 时间:2019-08-30 消费:100");
                break;
        }
        map.put("车辆违章", strings1);
        map.put("ETC消费", strings2);
        map.put("停车消费", strings3);
        adapter = new CY_Adapter(map);
        tvMonth.setText(xValues.get(index));
        expandList.setAdapter(adapter);
        int groupCount = expandList.getCount();
        for (int i=0; i<groupCount; i++) {
            expandList.expandGroup(i);
        }
    }

    private void initClick() {
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                initData((int) e.getX());
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void setLineChart() {
        entries.add(new BarEntry(0, 560));
        colors.add(Color.GREEN);
        entries.add(new BarEntry(1, 800));
        colors.add(Color.RED);
        entries.add(new BarEntry(2, 900));
        colors.add(Color.RED);
        entries.add(new BarEntry(3, 700));
        colors.add(Color.GREEN);
        entries.add(new BarEntry(4, 300));
        colors.add(Color.GREEN);
        xValues.add("八月");
        xValues.add("九月");
        xValues.add("十月");
        xValues.add("十一月");
        xValues.add("十二月");
        BarDataSet dataSet = new BarDataSet(entries, "");
        dataSet.setColors(colors);
        BarData data = new BarData(dataSet);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
        xAxis.setGranularity(1f);
        xAxis.setLabelRotationAngle(-50);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(20);
        xAxis.setDrawGridLines(false);
        YAxis yAxis = barChart.getAxisRight();
        yAxis.setStartAtZero(true);
        yAxis.setEnabled(false);
        YAxis yAxis1 = barChart.getAxisLeft();
        yAxis1.setStartAtZero(true);
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setData(data);
    }

    private void initView() {
        title.setText("创意题");
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}

