package com.example.tiku5.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.tiku5.R;
import com.example.tiku5.adapter.etcyeadapter;
import com.example.tiku5.bean.Etcye;
import com.example.tiku5.net.VolleyLo;
import com.example.tiku5.net.VolleyTo;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class S_ETCYEActivity extends AppCompatActivity {


    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.listView)
    ListView listView;
    private List<Etcye> metcye;
    private etcyeadapter  metcyeadapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_etcyeactivity);
        ButterKnife.bind(this);
        inview();
        huoqu();

    }

    private void setadapter() {
        metcyeadapter = new etcyeadapter(this,metcye);
        listView.setAdapter(metcyeadapter);
    }

    private void huoqu() {
        VolleyTo volleyTo  =new VolleyTo();
        volleyTo.setUrl("get_vehicle")
                .setJsonObject("UserName","user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            Gson gson = new Gson();
                            String arr =jsonObject.getString("ROWS_DETAIL");
                            JSONArray jsonArray = new JSONArray(arr);
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                Etcye etcye = gson.fromJson(jsonArray.getString(i),Etcye.class);
                                metcye.add(etcye);
                            }
                            setadapter();
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
        title.setText("ETC余额");
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        metcye = new ArrayList<>();
    }
}
