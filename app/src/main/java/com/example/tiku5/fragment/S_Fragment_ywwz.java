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
import com.example.tiku5.bean.Nowz;
import com.example.tiku5.bean.Yeswz;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class S_Fragment_ywwz extends Fragment {
    @BindView(R.id.pieChart)
    PieChart pieChart;
    private Unbinder mUnbinder;
    private List<Yeswz> myeswz;
    private List<Nowz> mnowz;
    private float he,yes,no;
    private PieData data;
    private PieDataSet dataSet;
    private List<PieEntry> pieEntries;
    private List<Integer> color;
    public S_Fragment_ywwz(List<Yeswz> myeswz,List<Nowz> mnowz)
    {
        this.myeswz=myeswz;
        this.mnowz=mnowz;
        he=(float) mnowz.size()+(float) myeswz.size();
        yes =(float) myeswz.size()/he;
        no = (float) mnowz.size()/he;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_ywwz, null);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        settu();
    }

    private void settu() {
        pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(yes,"有违章"));
        pieEntries.add(new PieEntry(no,"无违章"));
        setColor();
        dataSet = new PieDataSet(pieEntries,"");
        dataSet.setValueLineColor(Color.parseColor("#AA4644"));
        dataSet.setValueLinePart1OffsetPercentage(80f);
        dataSet.setValueLinePart2Length(1.0f);
        dataSet.setValueLinePart1Length(1.0f);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setColors(color);
        data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        Legend legend = pieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setTextSize(25);
        legend.setFormSize(25);
        pieChart.setData(data);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setTouchEnabled(false);
        pieChart.setRotationEnabled(false);
        pieChart.setDescription(null);
        pieChart.setUsePercentValues(true);
        pieChart.invalidate();
    }

    private void setColor() {
        color = new ArrayList<>();
        color.add(Color.parseColor("#4573A7"));
        color.add(Color.parseColor("#AA4644"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
