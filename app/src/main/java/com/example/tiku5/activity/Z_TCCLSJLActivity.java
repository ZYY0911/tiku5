package com.example.tiku5.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.tiku5.R;
import com.example.tiku5.adapter.TCCLSAdapter;
import com.example.tiku5.bean.TCC;
import com.example.tiku5.bean.TCCLS;
import com.example.tiku5.net.VolleyLo;
import com.example.tiku5.net.VolleyTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2019-12-26
 */
public class Z_TCCLSJLActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.tcls_rc)
    TextView tclsRc;
    @BindView(R.id.tcls_cc)
    TextView tclsCc;
    @BindView(R.id.tcls_cx)
    Button tclsCx;
    @BindView(R.id.ls_list)
    ListView lsList;
    private TimePickerView timePickerView;
    private List<TCCLS> tccls, tccls2;
    private TCCLSAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tcls_layout);
        ButterKnife.bind(this);
        initView();
        setVolley();
    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_park_record")
                .setJsonObject("UserName", "user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONArray jsonArray = new JSONArray(jsonObject.optString("ROWS_DETAIL"));
                            Gson gson = new Gson();
                            tccls = gson.fromJson(jsonArray.toString(), new TypeToken<List<TCCLS>>() {
                            }.getType());
                            setListView();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setListView() {
        adapter = new TCCLSAdapter(this, R.layout.ls_item, tccls);
        lsList.setAdapter(adapter);
    }

    private void initView() {
        title.setText("停车历史记录");
        change.setImageResource(R.drawable.back);
    }

    private void setTimePickerView(final TextView textView) {
        timePickerView = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                textView.setText(format.format(date));
            }
        }).setType(new boolean[]{true, true, true, true, true, true}).isDialog(true).build();
        timePickerView.show();
    }

    @OnClick({R.id.change, R.id.tcls_rc, R.id.tcls_cc, R.id.tcls_cx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.tcls_rc:
                setTimePickerView(tclsRc);
                break;
            case R.id.tcls_cc:
                setTimePickerView(tclsCc);
                break;
            case R.id.tcls_cx:
                if (tccls2 == null) tccls2 = new ArrayList<>();
                tccls2.clear();
                Date date = null;
                Date date1 = null;
                Date date2 = null;
                Date date3 = null;
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    date = format.parse(tclsRc.getText().toString());
                    date1 = format.parse(tclsCc.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < tccls.size(); i++) {
                    TCCLS tccl = tccls.get(i);
                    try {
                        date2 = format.parse(tccl.getEntrance());
                        date3 = format.parse(tccl.getExit());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (date2.compareTo(date)>=0){
                        if (date3.compareTo(date1)<=0){
                            tccls2.add(tccl);
                        }
                    }
                }
                adapter = new TCCLSAdapter(this, R.layout.ls_item, tccls2);
                lsList.setAdapter(adapter);
                break;
        }
    }
}
