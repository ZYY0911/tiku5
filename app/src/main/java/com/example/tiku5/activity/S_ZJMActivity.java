package com.example.tiku5.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tiku5.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class S_ZJMActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.cehua)
    ListView cehua;
    @BindView(R.id.drawer)
    DrawerLayout drawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_zjmactivity);
        ButterKnife.bind(this);
        inview();
    }

    private void inview() {
        title.setText("主界面");
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
