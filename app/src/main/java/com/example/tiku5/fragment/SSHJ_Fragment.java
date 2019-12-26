package com.example.tiku5.fragment;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
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
public class SSHJ_Fragment extends Fragment {
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
        fragmentTitle.setText("PM2.5");
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
            int pm = sshj.getPm25();
            if (pm > 200) {
                circles.add(Color.RED);
                send(pm + "");
            } else {
                circles.add(Color.GREEN);
            }
            entries.add(new Entry(m, sshj.getPm25()));
            m += 3;
        }
        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setCircleColors(circles);
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
        yAxis1.setAxisMaximum(320);
    }

    private void setX() {
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(17);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
    }

    private void send(String msg) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext())
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentText("当前PM2.5:" + msg)
                .setAutoCancel(true);
        NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        isLoop = false;
    }
}
