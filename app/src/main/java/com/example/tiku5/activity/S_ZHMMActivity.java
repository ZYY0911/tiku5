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

import org.litepal.LitePal;

import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_ZHMMActivity extends AppCompatActivity {


    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.yhm)
    EditText yhm;
    @BindView(R.id.yx)
    EditText yx;
    @BindView(R.id.zh)
    Button zh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_zhmmactivity);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.change, R.id.zh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
              finish();
                break;
            case R.id.zh:
                String y = yhm.getText().toString();
                String y1 = yx.getText().toString();
                String shuzi = "(.*[0-9].*){6,}";
                String zimu = "(.*[A-Za-z].*){4,}";
                boolean b = Pattern.matches(zimu,y);
                boolean b1 = Pattern.matches(shuzi,y1);
                if (!b) {
                    Toast.makeText(S_ZHMMActivity.this, "用户名不少于4位字母！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!b1) {
                    Toast.makeText(S_ZHMMActivity.this, "邮箱不少于6位数字！", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Yhzc> yhzcs = LitePal.findAll(Yhzc.class);
                for (int i=0;i<yhzcs.size();i++)
                {
                    Yhzc yhzc = yhzcs.get(i);
                    if (yhzc.getYhm().equals(y)&&yhzc.getYx().equals(y1))
                    {
                        Toast.makeText(S_ZHMMActivity.this, "找回密码为："+yhzc.getMm(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                Toast.makeText(S_ZHMMActivity.this, "没有用户信息！", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
