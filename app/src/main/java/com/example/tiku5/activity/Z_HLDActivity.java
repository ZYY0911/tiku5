package com.example.tiku5.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.tiku5.R;
import com.example.tiku5.adapter.HLD_Adapter;
import com.example.tiku5.bean.HLD;
import com.example.tiku5.dialog.HldDialog;
import com.example.tiku5.net.VolleyLo;
import com.example.tiku5.net.VolleyTo;
import com.example.tiku5.util.ShowDialog;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2019-12-26
 */
public class Z_HLDActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.edit_query)
    Button editQuery;
    @BindView(R.id.edit_setting)
    Button editSetting;
    @BindView(R.id.my_list)
    ListView myList;
    private List<HLD> hlds;
    private HLD_Adapter adapter;
    private ProgressDialog dialog;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            dialog.dismiss();
            return false;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hld_layout);
        ButterKnife.bind(this);
        initView();
        setData();

    }

    private void setData() {
        if (hlds == null) hlds = new ArrayList<>();
        hlds.clear();
        dialog.show();
        for (int i = 0; i < 5; i++) {
            setVolley(i + 1);
        }
    }

    private void setVolley(int num) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_traffic_light")
                .setJsonObject("UserName", "user1")
                .setJsonObject("number", num)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONArray jsonArray = new JSONArray(jsonObject.optString("ROWS_DETAIL"));
                            Gson gson = new Gson();
                            HLD hld = gson.fromJson(jsonArray.getJSONObject(0).toString(), HLD.class);
                            hld.setXz(false);
                            hlds.add(hld);
                            if (hlds.size() == 5) {
                                Collections.sort(hlds, new Comparator<HLD>() {
                                    @Override
                                    public int compare(HLD o1, HLD o2) {
                                        return o1.getId() - o2.getId();
                                    }
                                });
                                handler.sendEmptyMessageDelayed(1,1500);
                                setListView();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setListView() {
        adapter = new HLD_Adapter(this, R.layout.hld_item, hlds);
        myList.setAdapter(adapter);
        adapter.setMyClick(new HLD_Adapter.MyClick() {
            @Override
            public void myClick(int position, boolean xz, int lx) {
                HLD hld = hlds.get(position);
                if (lx == 1) {
                    HldDialog dialog = new HldDialog(hld.getId() + "", Z_HLDActivity.this);
                    dialog.setData(new HldDialog.SetData() {
                        @Override
                        public void setdata() {
                            setData();
                        }
                    });
                    dialog.show(getSupportFragmentManager(), "aa");
                } else if (lx == 2) {
                    hld.setXz(xz);
                    hlds.set(position, hld);
                }
            }
        });
        setList();

    }

    private void initView() {
        title.setText("红绿灯管理");
        dialog = new ProgressDialog(this);
        dialog.setTitle("提示");
        dialog.setMessage("网络请求中.....");
    }

    private void setList() {
        switch (spinner.getSelectedItem().toString()) {
            case "路口升序":
                Collections.sort(hlds, new Comparator<HLD>() {
                    @Override
                    public int compare(HLD o1, HLD o2) {
                        return o1.getId() - o2.getId();
                    }
                });
                break;
            case "路口降序":
                Collections.sort(hlds, new Comparator<HLD>() {
                    @Override
                    public int compare(HLD o1, HLD o2) {
                        return o2.getId() - o1.getId();
                    }
                });
                break;
            case "红灯升序":
                Collections.sort(hlds, new Comparator<HLD>() {
                    @Override
                    public int compare(HLD o1, HLD o2) {
                        return o1.getRed() - o2.getRed();
                    }
                });
                break;
            case "红灯降序":
                Collections.sort(hlds, new Comparator<HLD>() {
                    @Override
                    public int compare(HLD o1, HLD o2) {
                        return o2.getRed() - o1.getRed();
                    }
                });
                break;
            case "绿灯升序":
                Collections.sort(hlds, new Comparator<HLD>() {
                    @Override
                    public int compare(HLD o1, HLD o2) {
                        return o1.getGreen() - o2.getGreen();
                    }
                });
                break;
            case "绿灯降序":
                Collections.sort(hlds, new Comparator<HLD>() {
                    @Override
                    public int compare(HLD o1, HLD o2) {
                        return o2.getGreen() - o1.getGreen();
                    }
                });
                break;
            case "黄灯升序":
                Collections.sort(hlds, new Comparator<HLD>() {
                    @Override
                    public int compare(HLD o1, HLD o2) {
                        return o1.getYellow() - o2.getYellow();
                    }
                });
                break;
            case "黄灯降序":
                Collections.sort(hlds, new Comparator<HLD>() {
                    @Override
                    public int compare(HLD o1, HLD o2) {
                        return o2.getYellow() - o1.getYellow();
                    }
                });
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.change, R.id.edit_query, R.id.edit_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.edit_query:
                setList();

                break;
            case R.id.edit_setting:
                String lk = "";
                for (int i = 0; i < hlds.size(); i++) {
                    HLD hld = hlds.get(i);
                    if (hld.isXz()) {
                        if (lk.equals("")) {
                            lk += hld.getId();
                        } else {
                            lk += "," + hld.getId();
                        }
                    }
                }
                if (lk.equals("")) {
                    ShowDialog.Show("没有选中要修改的路口", this);
                    return;
                }
                HldDialog dialog = new HldDialog(lk, this);
                dialog.setData(new HldDialog.SetData() {
                    @Override
                    public void setdata() {
                        setData();
                    }
                });
                dialog.show(getSupportFragmentManager(), "ss");
                break;
        }
    }
}
