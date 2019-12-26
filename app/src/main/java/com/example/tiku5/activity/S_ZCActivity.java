package com.example.tiku5.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tiku5.R;
import com.example.tiku5.bean.Yhzc;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_ZCActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.yhm)
    EditText yhm;
    @BindView(R.id.yx)
    EditText yx;
    @BindView(R.id.mm)
    EditText mm;
    @BindView(R.id.qrmm)
    EditText qrmm;
    @BindView(R.id.tj)
    Button tj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_zcactivity);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.change, R.id.tj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
              finish();
                break;
            case R.id.tj:
                String y = yhm.getText().toString();
                String y1 = yx.getText().toString();
                String m = mm.getText().toString();
                String m1 = qrmm.getText().toString();
                String shuzi="(.*[0-9].*){6,}";
                String zimu="(.*[A-Za-z].*){4,}";
                boolean b = Pattern.matches(zimu,y);
                boolean b1 = Pattern.matches(shuzi,y1);
                boolean b2 = Pattern.matches(shuzi,m);
                if (!b)
                {
                    Toast.makeText(S_ZCActivity.this,"用户名不少于4位字母！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!b1)
                {
                    Toast.makeText(S_ZCActivity.this,"邮箱不少于6位数字！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!b2)
                {
                    Toast.makeText(S_ZCActivity.this,"密码不少于6位数字！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (m.equals(m1))
                {
                    Yhzc yhzc = new Yhzc();
                    yhzc.setYhm(y);
                    yhzc.setYx(y1);
                    yhzc.setMm(m);
                    yhzc.setQrmm(m1);
                    yhzc.save();
                    Toast.makeText(S_ZCActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(S_ZCActivity.this,"密码不一致！",Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }
}
