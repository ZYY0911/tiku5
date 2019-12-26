package com.example.tiku5.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tiku5.AppClient;
import com.example.tiku5.R;
import com.example.tiku5.adapter.wdyjadapter;
import com.example.tiku5.bean.Wdyj;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class S_WDYJActivity extends AppCompatActivity {

    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.listview)
    ListView listview;
    private AppClient mApp;
    private List<Wdyj> mwdyj;
    private wdyjadapter mwdyjadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_wdyjactivity);
        ButterKnife.bind(this);
        inview();
        setapter();
    }

    private void setapter() {
        mwdyjadapter = new wdyjadapter(this,mwdyj);
        listview.setAdapter(mwdyjadapter);
    }

    private void inview() {
        title.setText("我的意见");
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mApp =(AppClient) getApplication();
        mwdyj = mApp.getMwdyj();
    }
}
