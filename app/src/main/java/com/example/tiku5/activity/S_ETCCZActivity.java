package com.example.tiku5.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.tiku5.AppClient;
import com.example.tiku5.R;
import com.example.tiku5.bean.Czjl;
import com.example.tiku5.net.VolleyLo;
import com.example.tiku5.net.VolleyTo;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_ETCCZActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.bianhao)
    EditText bianhao;
    @BindView(R.id.chepai)
    TextView chepai;
    @BindView(R.id.shi)
    TextView shi;
    @BindView(R.id.wushi)
    TextView wushi;
    @BindView(R.id.yibai)
    TextView yibai;
    @BindView(R.id.shuru)
    EditText shuru;
    @BindView(R.id.chonghzi)
    Button chonghzi;
    private String cp,xq;
    private ProgressDialog dialog;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            dialog.dismiss();
            Toast.makeText(S_ETCCZActivity.this,"充值成功",Toast.LENGTH_SHORT).show();
            return false;
        }
    });
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_etcczactivity);
        ButterKnife.bind(this);
        inview();
        jianting();
        huoqu("1");

    }

    private void huoqu1(final String jine, final String cp) {
        SimpleDateFormat format  =new SimpleDateFormat("yyyy.MM.dd HH:mm");
        Date date =new Date(System.currentTimeMillis());
        final String t = format.format(date);
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        switch (i)
        {
            case 1:
                xq="星期日";
                break;
            case 2:
                xq="星期一";
                break;
            case 3:
                xq="星期二";
                break;
            case 4:
                xq="星期三";
                break;
            case 5:
                xq="星期四";
                break;
            case 6:
                xq="星期五";
                break;
            case 7:
                xq="星期六";
                break;
        }
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("set_balance")
                .setJsonObject("UserName","user1")
                .setJsonObject("plate",cp)
                .setJsonObject("balance",jine)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            if (jsonObject.getString("RESULT").equals("S"))
                            {
                                dialog.show();
                                handler.sendEmptyMessageDelayed(0,2000);
                                Czjl czjl =new Czjl();
                                czjl.setChepai(cp);
                                czjl.setJine(jine);
                                czjl.setChongzhiren(AppClient.getName());
                                czjl.setTime(t);
                                czjl.setXingqi(xq);
                                czjl.save();
                            }else {
                                Toast.makeText(S_ETCCZActivity.this,"充值失败",Toast.LENGTH_SHORT).show();
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
        title.setText("ETC充值");
        bianhao.setText("1");
        dialog=new ProgressDialog(this);
        dialog.setTitle("提示");
        dialog.setMessage("网络请求中。。。。");
    }

    private void huoqu(String bh) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_plate")
                .setJsonObject("UserName", "user1")
                .setJsonObject("number", bh)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            if (jsonObject.getString("RESULT").equals("S"))
                            {
                                cp = jsonObject.getString("plate");
                                chepai.setText(cp);
                            }else {
                                Toast.makeText(S_ETCCZActivity.this,"没有查询到车牌号",Toast.LENGTH_SHORT).show();
                                chepai.setText("");
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

    private void jianting() {

        shuru.addTextChangedListener(new TextWatcher() {
            private String temp;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = shuru.getText().toString();
                if (!temp.equals("")&&!(Integer.parseInt(temp)>0&&Integer.parseInt(temp)<=999))
                {
                    shuru.setText("");
                    Toast.makeText(S_ETCCZActivity.this,"只能在1-999之间输入",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        bianhao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                huoqu(bianhao.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @OnClick({R.id.change, R.id.shi, R.id.wushi, R.id.yibai, R.id.chonghzi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.shi:

                shuru.setText("10");
                break;
            case R.id.wushi:
                shuru.setText("50");
                break;
            case R.id.yibai:
                shuru.setText("100");
                break;
            case R.id.chonghzi:
                huoqu1(shuru.getText().toString(),cp);
                break;
        }
    }
}
