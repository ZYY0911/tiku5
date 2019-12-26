package com.example.tiku5.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tiku5.AppClient;
import com.example.tiku5.R;
import com.example.tiku5.bean.SSHJ;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2019-12-26
 */
public class SSHJ_Fragment3 extends Fragment {
    @BindView(R.id.fragment_title)
    TextView fragmentTitle;
    @BindView(R.id.lineChart)
    LineChart lineChart;
    Unbinder unbinder;
    private boolean isLoop = true;
    private AppClient appClient;
    private List<SSHJ> sshjs;
    private List<Entry> entries;
    private List<Integer> circles;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            setData();
            return false;
        }
    });


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sshj_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appClient = (AppClient) getActivity().getApplication();
        fragmentTitle.setText("光照强度");
        entries = new ArrayList<>();
        circles = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (isLoop);
            }
        }).start();
    }

    private void setData() {
        sshjs = appClient.getSshjs();
        circles.clear();
        entries.clear();
        if (sshjs.size() == 0) {
            return;
        }
        int m = 3;
        for (int i = 0; i < sshjs.size(); i++) {
            SSHJ sshj = sshjs.get(i);
            entries.add(new Entry(m, sshj.getIllumination()));
            m += 3;
        }
        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setCircleColor(Color.GREEN);
        dataSet.setColor(Color.GRAY);
        dataSet.setDrawCircleHole(false);
        dataSet.setCircleRadius(7);
        dataSet.setLineWidth(5);
        LineData data = new LineData(dataSet);
        setX();
        setY();
        lineChart.setData(data);
        lineChart.getDescription().setEnabled(false);
        lineChart.getLegend().setEnabled(false);
        lineChart.setTouchEnabled(false);
        lineChart.invalidate();
    }

    private void setY() {
        YAxis yAxis = lineChart.getAxisRight();
        yAxis.setAxisMinimum(0);
        yAxis.setEnabled(false);
        YAxis yAxis1 = lineChart.getAxisLeft();
        yAxis1.setAxisMinimum(0);
        yAxis1.setAxisMaximum(5200);
    }

    private void setX() {
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(17);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        isLoop = false;
    }
}
