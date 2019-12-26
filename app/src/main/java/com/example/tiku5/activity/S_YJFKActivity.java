package com.example.tiku5.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiku5.AppClient;
import com.example.tiku5.R;
import com.example.tiku5.bean.Wdyj;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_YJFKActivity extends AppCompatActivity {

    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.bt)
    EditText bt;
    @BindView(R.id.yj)
    EditText yj;
    @BindView(R.id.sj)
    EditText sj;
    @BindView(R.id.tijiao)
    Button tijiao;
    private AppClient mApp;
    private List<Wdyj> mwdyj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_yjfkactivity);
        ButterKnife.bind(this);
        inview();
        jiantingh();
    }

    private void jiantingh() {
        sj.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str = sj.getText().toString();
                if (!str.equals("")&&str.length()>11)
                {
                    sj.setText(str.substring(0,11));
                    sj.setSelection(11);
                    Toast.makeText(S_YJFKActivity.this,"手机号只能是11位！",Toast.LENGTH_SHORT).show();
                }
                if (!str.equals("")&&!(Integer.parseInt(str.substring(0,1))==1))
                {
                    sj.setText("");
                    Toast.makeText(S_YJFKActivity.this,"首位只能是1！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void inview() {
        title.setText("意见反馈");
        title1.setText("我的意见");
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(S_YJFKActivity.this,S_WDYJActivity.class));
            }
        });
        mApp = (AppClient) getApplication();
        mwdyj = mApp.getMwdyj();
    }

    @OnClick(R.id.tijiao)
    public void onTijiaoClicked() {
        String b = bt.getText().toString();
        String y = yj.getText().toString();
        String s = sj.getText().toString();
        if (b.equals("")||b.equals("")||b.equals(""))
        {
            Toast.makeText(S_YJFKActivity.this,"内容不能为空！",Toast.LENGTH_SHORT).show();
        }else {
            if (s.length()!=11)
            {
                Toast.makeText(S_YJFKActivity.this,"手机号只能是11位！",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(S_YJFKActivity.this,"提交成功！",Toast.LENGTH_SHORT).show();
                mwdyj.add(new Wdyj(b,y,s));
            }
        }
    }
}
