package com.example.tiku5.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.tiku5.R;
import com.example.tiku5.bean.Cxjg;
import com.example.tiku5.net.VolleyLo;
import com.example.tiku5.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_CXJGActivity extends AppCompatActivity {

    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.zhandain)
    TextView zhandain;
    @BindView(R.id.piaojia)
    TextView piaojia;
    @BindView(R.id.line)
    LinearLayout line;
    @BindView(R.id.kaishi)
    TextView kaishi;
    @BindView(R.id.jieshu)
    TextView jieshu;
    @BindView(R.id.shijian1)
    TextView shijian1;
    @BindView(R.id.shijian2)
    TextView shijian2;
    private String index;
    private List<Cxjg> mcxjg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_cxjgactivity);
        ButterKnife.bind(this);
        index = getIntent().getStringExtra("index");
        inview();
        huoqu();

    }

    private void settu() {
        line.removeAllViews();
        for (int i = 0; i < mcxjg.size(); i++) {
            final View view1 = LayoutInflater.from(S_CXJGActivity.this).inflate(R.layout.cxjg_item, null, false);
            TextView tv1 = view1.findViewById(R.id.tv1);
            final TextView tv2 = view1.findViewById(R.id.tv2);
            if (mcxjg.get(i).equals(mcxjg.get(0))) {
                tv1.setBackgroundResource(R.drawable.ssjtbk5);
                tv2.setTextColor(Color.RED);
            } else {
                tv1.setBackgroundResource(R.drawable.ssjtbk4);
                tv2.setTextColor(Color.BLACK);
            }
            view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView tv3 = view1.findViewById(R.id.tv2);
                    String name = tv3.getText().toString();
                    for (int i = 0; i < line.getChildCount(); i++) {
                        View view2 = line.getChildAt(i);
                        TextView tv11 = view2.findViewById(R.id.tv1);
                        TextView tv22 = view2.findViewById(R.id.tv2);
                        if (tv22.getText().equals(name)) {
                            tv11.setBackgroundResource(R.drawable.ssjtbk5);
                            tv22.setTextColor(Color.RED);
                        } else {
                            tv11.setBackgroundResource(R.drawable.ssjtbk4);
                            tv22.setTextColor(Color.BLACK);
                        }
                    }
                }
            });
            tv1.setText((i + 1) + "");
            tv2.setText(mcxjg.get(i).getMessage());
            line.addView(view1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        }
    }

    private void huoqu() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_bus_data")
                .setJsonObject("UserName", "user1")
                .setJsonObject("Busid", index)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            String arr = jsonObject.getString("ROWS_DETAIL");
                            JSONArray jsonArray = new JSONArray(arr);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                shijian1.setText(jsonObject1.getString("start"));
                                shijian2.setText(jsonObject1.getString("end"));
                                String jl = jsonObject1.getString("mileage");
                                String pj = jsonObject1.getString("price");
                                String arr1 = jsonObject1.getString("site");
                                JSONArray jsonArray1 = new JSONArray(arr1);
                                for (int y = 0; y < jsonArray1.length(); y++) {
                                    mcxjg.add(new Cxjg(jsonArray1.getString(y)));
                                }
                                kaishi.setText(jsonArray1.getString(0));
                                jieshu.setText(jsonArray1.getString(jsonArray1.length() - 1));
                                zhandain.setText(jsonArray1.length() + "站/" + jl + "公里");
                                piaojia.setText("票价：最高票价" + pj + "元");
                            }
                            settu();
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
        title.setText(index + "路");
        mcxjg = new ArrayList<>();
    }

    @OnClick(R.id.change)
    public void onChangeClicked() {
      finish();
    }
}
