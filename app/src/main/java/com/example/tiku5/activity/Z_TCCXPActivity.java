package com.example.tiku5.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiku5.R;
import com.example.tiku5.bean.TCC;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2019-12-26
 */
public class Z_TCCXPActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.xq_name)
    TextView xqName;
    @BindView(R.id.xq_dd)
    TextView xqDd;
    @BindView(R.id.xq_jl)
    TextView xqJl;
    @BindView(R.id.xq_fl)
    TextView xqFl;
    @BindView(R.id.xq_cw)
    TextView xqCw;
    @BindView(R.id.xq_ck)
    TextView xqCk;
    @BindView(R.id.root)
    TextView root;
    private TCC tcc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tccxq_layout);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        xqName.setText(tcc.getParking_name());
        root.setText(tcc.getRoot());
        xqDd.setText(tcc.getLocation());
        xqJl.setText(tcc.getDistance()+"米");
        xqCw.setText(tcc.getCarport()+"/600");
        xqCk.setText(tcc.getReference());
        xqFl.setText(tcc.getPrice()+"元/"+tcc.getRate_type());

    }

    private void initView() {
        change.setImageResource(R.drawable.back);
        title.setText("停车场");
        tcc = (TCC) getIntent().getSerializableExtra("tcc");
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
