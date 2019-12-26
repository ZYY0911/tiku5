package com.example.tiku5.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tiku5.AppClient;
import com.example.tiku5.R;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_WLSZActivity extends AppCompatActivity {
    @BindView(R.id.bc)
    Button bc;
    @BindView(R.id.qx)
    Button qx;
    @BindView(R.id.fwq)
    EditText fwq;
    @BindView(R.id.gkh)
    EditText gkh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_wlszactivity);
        ButterKnife.bind(this);
        jiantingh();
    }


    private void jiantingh() {
        gkh.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str = gkh.getText().toString();
                if (!str.equals("")&&str.length()>5)
                {
                    gkh.setText(str.substring(0,5));
                    gkh.setSelection(5);
                }

                if (!str.equals("")&&!(Integer.parseInt(str)>=0&&Integer.parseInt(str)<=65535))
                {
                    gkh.setText("");
                    Toast.makeText(S_WLSZActivity.this,"只能在0-65535之间输入",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    @OnClick({R.id.bc, R.id.qx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bc:
                String f = fwq.getText().toString();
                String d = gkh.getText().toString();
                String ip = "^([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\." +
                        "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\." +
                        "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\." +
                        "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])$";
                boolean b = Pattern.matches(ip,f);
                if (b)
                {

                    if (d.equals(""))
                    {
                        Toast.makeText(S_WLSZActivity.this,"端口号为空！",Toast.LENGTH_SHORT).show();
                    }else {
                        AppClient.setIp(f);
                        AppClient.setDk(d);
                        Toast.makeText(S_WLSZActivity.this,"设置成功！",Toast.LENGTH_SHORT).show();
                    }


                }else {
                    Toast.makeText(S_WLSZActivity.this,"服务器格式错误！",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.qx:
                finish();
                break;
        }
    }
}
