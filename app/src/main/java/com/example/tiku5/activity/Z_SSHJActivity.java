package com.example.tiku5.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.tiku5.AppClient;
import com.example.tiku5.R;
import com.example.tiku5.bean.SSHJ;
import com.example.tiku5.fragment.SSHJ_Fragment;
import com.example.tiku5.fragment.SSHJ_Fragment2;
import com.example.tiku5.fragment.SSHJ_Fragment3;
import com.example.tiku5.net.VolleyLo;
import com.example.tiku5.net.VolleyTo;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2019-12-26
 */
public class Z_SSHJActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.layout)
    LinearLayout layout;
    private List<SSHJ> sshjs;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sshj_layout);
        ButterKnife.bind(this);
        initView();
        setVolley();
        setViewPager();
    }

    private void setViewPager() {
        if (fragments == null) fragments = new ArrayList<>();
        fragments.clear();
        fragments.add(new SSHJ_Fragment());
        fragments.add(new SSHJ_Fragment2());
        fragments.add(new SSHJ_Fragment3());
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                for (int j = 0; j < layout.getChildCount(); j++) {
                    ImageView imageView = (ImageView) layout.getChildAt(j);
                    if (j == i) {
                        imageView.setImageResource(R.drawable.page_indicator_focused);
                    } else {
                        imageView.setImageResource(R.drawable.page_indicator_unfocused);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        layout.removeAllViews();
        for (int i = 0; i < fragments.size(); i++) {
            ImageView imageView = new ImageView(this);
            if (i == 0) {
                imageView.setImageResource(R.drawable.page_indicator_focused);
            } else {
                imageView.setImageResource(R.drawable.page_indicator_unfocused);
            }
            imageView.setLayoutParams(new ViewGroup.LayoutParams(50, 50));
            layout.addView(imageView);
        }
    }

    private void initView() {
        title.setText("实时环境");
        AppClient appClient = (AppClient) getApplication();
        sshjs = appClient.getSshjs();
    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_all_sense")
                .setJsonObject("UserName", "user1")
                .setLoop(true)
                .setTime(3000)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Gson gson = new Gson();
                        sshjs.add(gson.fromJson(jsonObject.toString(), SSHJ.class));
                        if (sshjs.size() == 6) {
                            sshjs.remove(0);
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
