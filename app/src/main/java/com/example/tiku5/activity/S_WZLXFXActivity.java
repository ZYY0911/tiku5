package com.example.tiku5.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.tiku5.R;
import com.example.tiku5.bean.Sjfx;
import com.example.tiku5.bean.Yeswz;
import com.example.tiku5.net.VolleyLo;
import com.example.tiku5.net.VolleyTo;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class S_WZLXFXActivity extends AppCompatActivity {

    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.t1)
    TextView t1;
    @BindView(R.id.t2)
    TextView t2;
    @BindView(R.id.t3)
    TextView t3;
    @BindView(R.id.t4)
    TextView t4;
    @BindView(R.id.t5)
    TextView t5;
    @BindView(R.id.radarChart)
    RadarChart radarChart;

    private Map<String, Integer> pm;
    private List<Map.Entry<String, Integer>> listType;
    private List<Yeswz> myeswz;
    private List<Sjfx> msjfx;
    private String[] s1, s2, s3, s4, s5;
    private RadarData data;
    private RadarDataSet dataSet;
    private List<RadarEntry> radarEntries;
    private List<IRadarDataSet> iRadarDataSets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_wzlxfxactivity);
        ButterKnife.bind(this);
        inview();
        huoqu();

    }

    private void zhi() {
        s1 = listType.get(0).toString().split("=");
        s2 = listType.get(1).toString().split("=");
        s3 = listType.get(2).toString().split("=");
        s4 = listType.get(3).toString().split("=");
        s5 = listType.get(4).toString().split("=");

        t1.setText(s1[0]);
        t2.setText(s2[0]);
        t3.setText(s3[0]);
        t4.setText(s4[0]);
        t5.setText(s5[0]);
    }

    private void settu() {
        radarEntries = new ArrayList<>();
        radarEntries.add(new RadarEntry(Integer.parseInt(s1[1])));
        radarEntries.add(new RadarEntry(Integer.parseInt(s2[1])));
        radarEntries.add(new RadarEntry(Integer.parseInt(s3[1])));
        radarEntries.add(new RadarEntry(Integer.parseInt(s4[1])));
        radarEntries.add(new RadarEntry(Integer.parseInt(s5[1])));

        dataSet = new RadarDataSet(radarEntries,"");
        dataSet.setColors(Color.parseColor("#A9CBED"));
        dataSet.setFillColor(Color.parseColor("#A9CBED"));
        dataSet.setDrawFilled(true);
        dataSet.setFillAlpha(180);
        dataSet.setLineWidth(2f);
        dataSet.setDrawValues(false);

        XAxis xAxis = radarChart.getXAxis();
        xAxis.setAxisMinimum(0);
        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = radarChart.getYAxis();
        yAxis.setStartAtZero(true);
        yAxis.setStartAtZero(true);
        yAxis.setTextSize(15f);
        iRadarDataSets = new ArrayList<>();
        iRadarDataSets.add(dataSet);
        iRadarDataSets.add(tu());

        data = new RadarData(iRadarDataSets);
        radarChart.setData(data);
        radarChart.setDescription(null);
        radarChart.setTouchEnabled(false);
        radarChart.setRotationEnabled(false);

        radarChart.invalidate();
    }
    private RadarDataSet tu()
    {
        List<RadarEntry> yValue =new ArrayList<>();
        int[] dra=new int[]{
                R.drawable.shape_1,
                R.drawable.shape_2,
                R.drawable.shape_3,
                R.drawable.shape_4,
                R.drawable.shape_5,
        };
        for (int i=0;i<5;i++)
        {
            RadarEntry radarEntry = new RadarEntry(146f);
            radarEntry.setIcon(getResources().getDrawable(dra[i]));
            yValue.add(radarEntry);
        }
        RadarDataSet radarDataSet  =new RadarDataSet(yValue,"");
        radarDataSet.setDrawValues(false);
        radarDataSet.setColors(Color.TRANSPARENT);
        return radarDataSet;
    }

    private void huoqu() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_peccancy")
                .setJsonObject("UserName", "user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            try {
                                Gson gson = new Gson();
                                String arr = jsonObject.getString("ROWS_DETAIL");
                                JSONArray jsonArray = new JSONArray(arr);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    Sjfx sjfx = gson.fromJson(jsonArray.getString(i), Sjfx.class);
                                    msjfx.add(sjfx);
                                }
                                ywwz();
                                wzpm();
                                zhi();
                                settu();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void wzpm() {
        for (int i = 0; i < myeswz.size(); i++) {
            String id = myeswz.get(i).getYuanyin();
            if (pm.get(id) == null) {
                pm.put(id, 1);
            } else {
                pm.put(id, pm.get(id) + 1);
            }
        }
        listType = new ArrayList<>(pm.entrySet());
        Collections.sort(listType, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        Log.i("----------------", "wzpm: "+listType);
    }

    private void ywwz() {
        for (int i = 0; i < msjfx.size(); i++) {
            Sjfx sjfx = msjfx.get(i);
            if (!sjfx.getPaddr().equals("")) {
                myeswz.add(new Yeswz(sjfx.getCarnumber(), sjfx.getPaddr()));
            }
        }
    }

    private void inview() {
        title.setText("违章类型分析");
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        myeswz = new ArrayList<>();
        msjfx = new ArrayList<>();
        pm = new HashMap<>();
        listType = new ArrayList<>();
        s1 = new String[2];
        s2 = new String[2];
        s3 = new String[2];
        s4 = new String[2];
        s5 = new String[2];
    }
}
