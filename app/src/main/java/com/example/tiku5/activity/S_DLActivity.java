package com.example.tiku5.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.tiku5.AppClient;
import com.example.tiku5.R;
import com.example.tiku5.bean.Yhzc;
import com.example.tiku5.net.VolleyLo;
import com.example.tiku5.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_DLActivity extends AppCompatActivity {
    @BindView(R.id.yuyan)
    TextView yuyan;
    @BindView(R.id.yhm)
    EditText yhm;
    @BindView(R.id.mm)
    EditText mm;
    @BindView(R.id.zc)
    TextView zc;
    @BindView(R.id.zhmm)
    TextView zhmm;
    @BindView(R.id.dl)
    Button dl;
    @BindView(R.id.shezhi)
    ImageView shezhi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_dlactivity);
        ButterKnife.bind(this);
        inview();

    }

    private void huoqu1(final String id) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_roots")
                .setJsonObject("UserName","user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            String arr= jsonObject.getString("ROWS_DETAIL");
                            JSONArray jsonArray = new JSONArray(arr);
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                if (jsonObject1.getString("id").equals(id))
                                {
                                    AppClient.setName(jsonObject1.getString("name"));
                                }
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

    private void getyu(String en) {
        Configuration configuration  =getResources().getConfiguration();
        if (en.equals("en"))
        {
            configuration.locale= Locale.US;
        }else {
            configuration.locale= Locale.CHINA;
        }
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
        Intent intent  =new Intent(this,S_DLActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void setyuyan() {
        final Dialog dialog  =new Dialog(S_DLActivity.this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.yuyan_dialog);
        RadioButton y = dialog .findViewById(R.id.yw);
        RadioButton z = dialog.findViewById(R.id.zw);
        y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getyu("en");
                finish();
                dialog.dismiss();
            }
        });
        z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getyu("zh");
                finish();
                dialog.dismiss();
            }
        });
        WindowManager.LayoutParams layoutParams  =dialog.getWindow().getAttributes();
        layoutParams.height=400;
        layoutParams.width=500;
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();
    }

    private void inview() {
        if (!AppClient.getUserName1().equals("")) {
            startActivity(new Intent(S_DLActivity.this, Z_FirstActivity.class));
        }
    }

    private void huoqu(final String y, final String m) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_login")
                .setJsonObject("UserName", "user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            String arr = jsonObject.getString("ROWS_DETAIL");
                            JSONArray jsonArray = new JSONArray(arr);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                if (jsonObject1.getString("username").equals(y) && jsonObject1.getString("password").equals(m)) {
                                    Toast.makeText(S_DLActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(S_DLActivity.this, Z_FirstActivity.class));
                                    AppClient.setPassWord(m);
                                    AppClient.setUserName1(y);
                                    AppClient.setSex(y);
                                    huoqu1(jsonObject1.getString("id"));
                                    finish();
                                    return;
                                }
                            }
                            List<Yhzc> yhzcs = LitePal.findAll(Yhzc.class);
                            for (int i = 0; i < yhzcs.size(); i++) {
                                Yhzc yhzc = yhzcs.get(i);
                                if (yhzc.getYhm().equals(y) && yhzc.getMm().equals(m)) {
                                    Toast.makeText(S_DLActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(S_DLActivity.this, Z_FirstActivity.class));
                                    AppClient.setPassWord(m);
                                    AppClient.setUserName1(y);
                                    AppClient.setSex(y);
                                    AppClient.setName(y);
                                    finish();
                                    return;
                                }
                            }

                            Toast.makeText(S_DLActivity.this, "用户不合法", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(S_DLActivity.this, "用户不合法", Toast.LENGTH_SHORT).show();
                    }
                }).start();
    }

    @OnClick(R.id.dl)
    public void onDlClicked() {
        String y = yhm.getText().toString();
        String m = mm.getText().toString();
        huoqu(y, m);
    }

    @OnClick({R.id.zc, R.id.zhmm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zc:
                startActivity(new Intent(S_DLActivity.this, S_ZCActivity.class));
                break;
            case R.id.zhmm:
                startActivity(new Intent(S_DLActivity.this, S_ZHMMActivity.class));
                break;
        }
    }

    @OnClick(R.id.shezhi)
    public void onShezhiClicked() {

        AppClient.setUserName1("");
        setyuyan();
    }
}
