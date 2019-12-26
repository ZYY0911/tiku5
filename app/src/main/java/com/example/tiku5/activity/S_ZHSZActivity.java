package com.example.tiku5.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.tiku5.AppClient;
import com.example.tiku5.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class S_ZHSZActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.tishi)
    TextView tishi;
    @BindView(R.id.shuru)
    EditText shuru;
    @BindView(R.id.shezhi)
    Button shezhi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_zhszactivity);
        ButterKnife.bind(this);
        inview();
        if (AppClient.getYz().equals("0"))
        {
            tishi.setText("1-4号车没有设置告警阈值！");
        }else {
            tishi.setText("1-4号车账户余额告警阈值为"+AppClient.getYz()+"元");
        }
        setdianji();
    }

    private void setdianji() {
        shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q  =shuru.getText().toString();
                tishi.setText("4号车账户余额告警阈值为"+q+"元");
                AppClient.setYz(q);
            }
        });
    }

    private void inview() {
        title.setText("个人中心");
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
