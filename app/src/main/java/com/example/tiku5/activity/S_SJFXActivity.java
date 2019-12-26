package com.example.tiku5.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.tiku5.R;
import com.example.tiku5.adapter.FragmentAdapter;
import com.example.tiku5.bean.Nowz;
import com.example.tiku5.bean.Sjfx;
import com.example.tiku5.bean.Yeswz;
import com.example.tiku5.fragment.S_Fragment_wzpm;
import com.example.tiku5.fragment.S_Fragment_ywwz;
import com.example.tiku5.net.VolleyLo;
import com.example.tiku5.net.VolleyTo;
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

public class S_SJFXActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.line)
    LinearLayout line;
    private List<Sjfx> msjfx;
    private List<Yeswz> myeswz;
    private List<Nowz> mnowz;
    private Map<String,Integer> pm;
    private List<Map.Entry<String,Integer>> listType;
    private List<Fragment> fragments;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_sjfxactivity);
        ButterKnife.bind(this);
        inview();
        huoqu();

    }

    private void select(int y) {
        for (int i=0;i<line.getChildCount();i++)
        {
            ImageView imageView = (ImageView) line.getChildAt(i);
            if (i==y)
            {
                imageView.setImageResource(R.drawable.shi);
            }else {
                imageView.setImageResource(R.drawable.kong);
            }
        }
    }

    private void settu() {
        line.removeAllViews();
        for (int i=0;i<fragments.size();i++)
        {
            ImageView imageView = new ImageView(S_SJFXActivity.this);
            if (i==0)
            {
                imageView.setImageResource(R.drawable.shi);
            }else {
                imageView.setImageResource(R.drawable.kong);
            }
            imageView.setLayoutParams(new FrameLayout.LayoutParams(50,50));
            line.addView(imageView);
        }
    }

    private void addFragment() {
        fragments.add(new S_Fragment_ywwz(myeswz,mnowz));
        fragments.add(new S_Fragment_wzpm(listType));
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),fragments));
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {


                select(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        settu();
    }

    private void wzpm() {
        for (int i=0;i<myeswz.size();i++)
        {
            String id = myeswz.get(i).getYuanyin();
            if (pm.get(id)==null)
            {
                pm.put(id,1);
            }else {
                pm.put(id,pm.get(id)+1);
            }
        }
        listType=  new ArrayList<>(pm.entrySet());
        Collections.sort(listType, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue()-o2.getValue();
            }
        });

    }

    private void ywwz() {
        for (int i=0;i<msjfx.size();i++)
        {
            Sjfx sjfx = msjfx.get(i);
            if (sjfx.getPaddr().equals(""))
            {
                mnowz.add(new Nowz(sjfx.getCarnumber(),sjfx.getPaddr()));
            }else {
                myeswz.add(new Yeswz(sjfx.getCarnumber(),sjfx.getPaddr()));
            }
        }
    }

    private void huoqu() {
        VolleyTo volleyTo  =new VolleyTo();
        volleyTo.setUrl("get_peccancy")
                .setJsonObject("UserName","user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            try {
                                Gson gson  =new Gson();
                                String arr=  jsonObject.getString("ROWS_DETAIL");
                                JSONArray jsonArray = new JSONArray(arr);
                                for (int i=0;i<jsonArray.length();i++)
                                {
                                    Sjfx sjfx = gson.fromJson(jsonArray.getString(i),Sjfx.class);
                                    msjfx.add(sjfx);
                                }
                                ywwz();
                                wzpm();
                                addFragment();
                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void inview() {
        title.setText("数据分析");
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mnowz = new ArrayList<>();
        myeswz = new ArrayList<>();
        msjfx =new ArrayList<>();
        fragments = new ArrayList<>();
        pm = new HashMap<>();
        listType = new ArrayList<>();
    }
}
