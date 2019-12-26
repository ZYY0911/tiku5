package com.example.tiku5.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.tiku5.R;
import com.example.tiku5.adapter.TCC_Adapter;
import com.example.tiku5.bean.TCC;
import com.example.tiku5.dialog.TCCDialog;
import com.example.tiku5.net.VolleyLo;
import com.example.tiku5.net.VolleyTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2019-12-26
 */
public class Z_TCCActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.image_setting)
    ImageView imageSetting;
    @BindView(R.id.image_infos)
    ImageView imageInfos;
    @BindView(R.id.tc_list)
    ListView tcList;
    private List<TCC> tccs;
    private TCC_Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tcc_layout);
        ButterKnife.bind(this);
        initView();
        setVolley();
    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_park_data")
                .setJsonObject("UserName", "user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONArray jsonArray = new JSONArray(jsonObject.optString("ROWS_DETAIL"));
                            Gson gson = new Gson();
                            tccs = gson.fromJson(jsonArray.toString(), new TypeToken<List<TCC>>() {
                            }.getType());
                            setList();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setList() {
        adapter = new TCC_Adapter(this, R.layout.tcc_item, tccs);
        tcList.setAdapter(adapter);
        tcList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Z_TCCActivity.this, Z_TCCXPActivity.class);
                intent.putExtra("tcc", tccs.get(position));
                startActivity(intent);
            }
        });

    }

    private void initView() {
        title.setText("停车场");
    }


    @OnClick({R.id.change, R.id.image_setting, R.id.image_infos})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.image_setting:
                TCCDialog dialog = new TCCDialog(tccs.get(0));
                dialog.setMyClick(new TCCDialog.MyClick() {
                    @Override
                    public void clcik() {
                        tccs.clear();
                        setVolley();
                    }
                });
                dialog.show(getSupportFragmentManager(),"aaa");
                break;
            case R.id.image_infos:
                startActivity(new Intent(this,Z_TCCLSJLActivity.class));
                break;
        }
    }
}
