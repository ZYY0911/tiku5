package com.example.tiku5.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.VolleyError;
import com.example.tiku5.R;
import com.example.tiku5.net.VolleyLo;
import com.example.tiku5.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_GSETCActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.im)
    ImageView im;
    @BindView(R.id.viewFiller)
    ViewFlipper viewFiller;
    @BindView(R.id.etccz)
    LinearLayout etccz;
    @BindView(R.id.etcye)
    LinearLayout etcye;
    @BindView(R.id.czjl)
    LinearLayout czjl;
    private String t;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_gsetcactivity);
        ButterKnife.bind(this);
        inview();
        donghua();
        huoqu();
    }

    private void donghua() {
        im.setBackgroundResource(R.drawable.laba);
        AnimationDrawable animationDrawable = (AnimationDrawable) im.getBackground();
        animationDrawable.start();
    }

    private void yidong() {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.CENTER));
        textView.setText(t);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.LEFT);
        textView.setTextSize(30);
        viewFiller.addView(textView);
    }

    private void huoqu() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_news")
                .setJsonObject("UserName", "user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            String arr = jsonObject.getString("ROWS_DETAIL");
                            JSONArray jsonArray = new JSONArray(arr);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                t = jsonObject1.getString("title");
                                yidong();
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

    private void inview() {
        title.setText("高速ETC");
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        viewFiller.startFlipping();
    }

    @OnClick({R.id.etccz, R.id.etcye, R.id.czjl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.etccz:
                startActivity(new Intent(S_GSETCActivity.this,S_ETCCZActivity.class));
                break;
            case R.id.etcye:
                startActivity(new Intent(S_GSETCActivity.this,S_ETCYEActivity.class));
                break;
            case R.id.czjl:
                startActivity(new Intent(S_GSETCActivity.this,S_ETCCZJLActivity.class));
                break;
        }
    }
}
