package com.example.tiku5.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tiku5.R;
import com.example.tiku5.adapter.czjladapter;
import com.example.tiku5.adapter.czjladapter1;
import com.example.tiku5.bean.Czjl;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_ETCCZJLActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.heji)
    TextView heji;
    @BindView(R.id.listView)
    ListView listView;
    private List<Czjl> czjls;
    private czjladapter mczjladapter;
    private czjladapter1 mczjladapter1;
    private int he=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_etcczjlactivity);
        ButterKnife.bind(this);
        inview();
        setData();


    }

    private void setadapter1() {
        mczjladapter1 = new czjladapter1(this);
        listView.setAdapter(mczjladapter1);
    }

    private void setadapter() {
        mczjladapter = new czjladapter(this,czjls);
        listView.setAdapter(mczjladapter);
    }

    private void setData() {
        czjls = LitePal.findAll(Czjl.class);

        if (czjls.size() > 0)
        {
            for (int i=0;i<czjls.size();i++)
            {
                Czjl czjl  =czjls.get(i);
                he+=Integer.parseInt(czjl.getJine());
                heji.setText("充值合计："+he+"元");
            }

            Collections.sort(czjls, new Comparator<Czjl>() {
                @Override
                public int compare(Czjl o1, Czjl o2) {
                    try {
                        String time1 = o1.getTime();
                        String time2 = o2.getTime();
                        SimpleDateFormat format  =new SimpleDateFormat("yyyy.MM.dd HH:mm");
                        Date d1 = format.parse(time1);
                        Date d2 =format.parse(time2);
                        if (d1.getTime()<d2.getTime())
                        {
                            return 1;
                        }else if (d1.getTime()==d2.getTime())
                        {
                            return 0;
                        }else {
                            return -1;
                        }
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    return 0;
                }
            });

            setadapter();
        }else {
            listView.setDivider(null);
            heji.setText("充值合计："+he+"元");
            setadapter1();
        }

    }

    private void inview() {
        title.setText("ETC充值记录");
    }

    @OnClick(R.id.change)
    public void onChangeClicked() {
        finish();
    }
}
