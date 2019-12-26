package com.example.tiku5.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.tiku5.AppClient;
import com.example.tiku5.R;
import com.example.tiku5.adapter.ssjtadapter;
import com.example.tiku5.bean.Cxjg;
import com.example.tiku5.bean.Ssjt;
import com.example.tiku5.net.VolleyLo;
import com.example.tiku5.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class S_SSJTActivity extends AppCompatActivity {

    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.shuru)
    EditText shuru;
    @BindView(R.id.sosuo)
    TextView sosuo;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.qk)
    TextView qk;
    private AppClient mApp;
    private List<Ssjt> mssjt;
    private ssjtadapter mssjtadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_ssjtactivity);
        ButterKnife.bind(this);
        inview();
        setdianji();

    }

    private void setadapter() {
        mssjtadapter = new ssjtadapter(this,mssjt);
        listView.setAdapter(mssjtadapter);
    }

    private void setdianji() {
        qk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mssjt.size()>0)
                {
                    mssjt.clear();
                    mssjtadapter.notifyDataSetChanged();
                }
            }
        });
        sosuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String h = shuru.getText().toString();
                if (h.equals(""))
                {
                    Toast.makeText(S_SSJTActivity.this,"内容不能为空！",Toast.LENGTH_SHORT).show();
                }else {
                    if (!(Integer.parseInt(h)>0&&Integer.parseInt(h)<=10))
                    {
                        shuru.setText("");
                        Toast.makeText(S_SSJTActivity.this,"内容输入有误！",Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent = new Intent(S_SSJTActivity.this,S_CXJGActivity.class);
                        intent.putExtra("index",h);
                        startActivity(intent);
                        shuru.setText("");
                        huoqu(h);
                    }
                }
            }
        });
    }

    private void huoqu(final String index) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_bus_data")
                .setJsonObject("UserName", "user1")
                .setJsonObject("Busid", index)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        for (int i=mssjt.size();i>0;i--)
                        {
                            Ssjt ssjt = mssjt.get(i-1);
                            if (ssjt.getLuhao().equals(index+"路"))
                            {
                                mssjt.remove(i-1);
                            }
                        }
                        try {
                            String arr = jsonObject.getString("ROWS_DETAIL");
                            JSONArray jsonArray = new JSONArray(arr);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String arr1 = jsonObject1.getString("site");
                                JSONArray jsonArray1 = new JSONArray(arr1);
                                mssjt.add(0,new Ssjt(index+"路","("+jsonArray1.getString(0)+"-"+jsonArray1.getString(jsonArray1.length()-1)+")"));

                            }
                            setadapter();
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
        title.setText("实时交通");
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title1.setText("地图");
        mApp =(AppClient) getApplication();
        mssjt = mApp.getMssjt();
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(S_SSJTActivity.this,S_CXJGActivity.class));
            }
        });
    }
}
