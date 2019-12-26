package com.example.tiku5.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tiku5.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class S_Fragment_wzpm extends Fragment {
    @BindView(R.id.barChart)
    HorizontalBarChart barChart;
    private Unbinder mUnbinder;
    private List<Map.Entry<String,Integer>> listType;
    private int he;
    private BarDataSet dataSet;
    private BarData data;
    private List<BarEntry> mY;
    private List<String> mX;
    private List<Integer> color;
    private String[] s1,s2,s3,s4,s5,s6,s7,s8,s9,s10;
    public S_Fragment_wzpm( List<Map.Entry<String,Integer>> listType)
    {
        this.listType=listType;
        for (int i=0;i<listType.size();i++)
        {
            String[] a = listType.get(i).toString().split("=");
            he+=Integer.parseInt(a[1]);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_wzpm, null);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        s1 = listType.get(0).toString().split("=");
        s2 = listType.get(1).toString().split("=");
        s3 = listType.get(2).toString().split("=");
        s4 = listType.get(3).toString().split("=");
        s5 = listType.get(4).toString().split("=");
        s6 = listType.get(5).toString().split("=");
        s7 = listType.get(6).toString().split("=");
        s8 = listType.get(7).toString().split("=");
        s9 = listType.get(8).toString().split("=");
        s10 = listType.get(9).toString().split("=");
        settu();
    }

    private void settu() {
        mX = new ArrayList<>();
        mY = new ArrayList<>();
        addData();
        setColor();
        dataSet = new BarDataSet(mY,"");
        dataSet.setColors(color);
        data = new BarData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        barChart.setData(data);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(mX));
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(10);

        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisLeft().setStartAtZero(true);
        barChart.getAxisRight().setStartAtZero(true);
        barChart.getAxisRight().setValueFormatter(new PercentFormatter());
        barChart.setDescription(null);
        barChart.setTouchEnabled(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.getLegend().setEnabled(false);
        barChart.invalidate();
    }

    private void setColor() {

        color = new ArrayList<>();
        color.add(Color.parseColor("#9CB6D9"));

        color.add(Color.parseColor("#786F86"));
        color.add(Color.parseColor("#D68D51"));
        color.add(Color.parseColor("#B6CD86"));
        color.add(Color.parseColor("#FDC705"));
        color.add(Color.parseColor("#604B81"));
        color.add(Color.parseColor("#ED7007"));
        color.add(Color.parseColor("#799938"));
        color.add(Color.parseColor("#5083C0"));
        color.add(Color.parseColor("#C30405"));
    }

    private void addData() {
        mX.add(s1[0]); mX.add(s2[0]); mX.add(s3[0]); mX.add(s4[0]); mX.add(s5[0]);
        mX.add(s6[0]); mX.add(s7[0]); mX.add(s8[0]); mX.add(s9[0]); mX.add(s10[0]);
        mY.add(new BarEntry(0,((float)Integer.parseInt(s1[1])/(float)he)*100));
        mY.add(new BarEntry(1,((float)Integer.parseInt(s2[1])/(float)he)*100));
        mY.add(new BarEntry(2,((float)Integer.parseInt(s3[1])/(float)he)*100));
        mY.add(new BarEntry(3,((float)Integer.parseInt(s4[1])/(float)he)*100));
        mY.add(new BarEntry(4,((float)Integer.parseInt(s5[1])/(float)he)*100));
        mY.add(new BarEntry(5,((float)Integer.parseInt(s6[1])/(float)he)*100));
        mY.add(new BarEntry(6,((float)Integer.parseInt(s7[1])/(float)he)*100));
        mY.add(new BarEntry(7,((float)Integer.parseInt(s8[1])/(float)he)*100));
        mY.add(new BarEntry(8,((float)Integer.parseInt(s9[1])/(float)he)*100));
        mY.add(new BarEntry(9,((float)Integer.parseInt(s10[1])/(float)he)*100));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
