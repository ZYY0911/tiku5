package com.example.tiku5.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.tiku5.R;
import com.example.tiku5.bean.TCC;
import com.example.tiku5.net.VolleyLo;
import com.example.tiku5.net.VolleyTo;
import com.example.tiku5.util.ShowDialog;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2019-12-26
 */
@SuppressLint("ValidFragment")
public class TCCDialog extends DialogFragment {
    @BindView(R.id.dia_xs)
    TextView diaXs;
    @BindView(R.id.dia_cs)
    TextView diaCs;
    @BindView(R.id.dia_sp)
    Spinner diaSp;
    @BindView(R.id.dia_je)
    EditText diaJe;
    @BindView(R.id.dia_ok)
    Button diaOk;
    @BindView(R.id.dia_closs)
    Button diaCloss;
    Unbinder unbinder;
    private TCC tcc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.tcc_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public interface MyClick{
        void clcik();
    }
    private MyClick myClick;

    public void setMyClick(MyClick myClick) {
        this.myClick = myClick;
    }

    public TCCDialog(TCC tcc) {
        this.tcc = tcc;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (tcc.getRate_type().equals("小时")) {
            diaXs.setText(tcc.getPrice() + "元/" + tcc.getRate_type());
            diaSp.setSelection(0);
        } else {
            diaXs.setText(tcc.getPrice() + "元/" + tcc.getRate_type());
            diaSp.setSelection(1);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.dia_ok, R.id.dia_closs})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dia_ok:
                String lx = diaSp.getSelectedItem().toString().trim();
                String je = diaJe.getText().toString().trim();
                if (lx.equals("次数")) {
                    lx = "次";
                }
                if (je.equals("")) {
                    ShowDialog.Show("金额不能为空", getContext());
                    return;
                }
                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("set_park_rate")
                        //"UserName":"user1","type":"小时","price":"5"}
                        .setJsonObject("UserName","user1")
                        .setJsonObject("type",lx)
                        .setJsonObject("price",je)
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S")){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setTitle("提示");
                                    builder.setMessage("设置成功");
                                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            getDialog().dismiss();
                                        }
                                    });
                                    builder.show();
                                    myClick.clcik();
                                }else {
                                    ShowDialog.Show("设置失败",getContext());
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }).start();
                break;
            case R.id.dia_closs:
                getDialog().dismiss();
                break;
        }
    }
}
