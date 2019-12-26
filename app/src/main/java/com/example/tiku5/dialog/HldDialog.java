package com.example.tiku5.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.example.tiku5.R;
import com.example.tiku5.net.VolleyLo;
import com.example.tiku5.net.VolleyTo;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/10/30 0030.
 */

@SuppressLint("ValidFragment")
public class HldDialog extends DialogFragment {
    @BindView(R.id.dia_hod)
    EditText diaHod;
    @BindView(R.id.dia_hud)
    EditText diaHud;
    @BindView(R.id.dia_lvd)
    EditText diaLvd;
    @BindView(R.id.dia_ok)
    Button diaOk;
    @BindView(R.id.dia_closs)
    Button diaCloss;
    Unbinder unbinder;
    private VolleyTo volleyTo;
    private String Lk;
    private Context context;
    private ProgressDialog dialog;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                showAlertDialog("设置成功");
                HldDialog.this.dismiss();
                data.setdata();
            }
            return false;
        }
    });

    public interface SetData {
        void setdata();
    }

    private SetData data;

    public void setData(SetData data) {
        this.data = data;
    }

    public HldDialog(String lk, Context context) {
        Lk = lk;
        this.context = context;
    }


    private void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(message);
        builder.setPositiveButton("确定", null);
        builder.show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hld_dialog, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
                String ho = diaHod.getText().toString().trim();
                String hu = diaHud.getText().toString().trim();
                String lv = diaLvd.getText().toString().trim();
                if (ho.equals("") || hu.equals("") || lv.equals("")) {
                    showAlertDialog("红绿灯数值不能为空");
                    return;
                }
                dialog = new ProgressDialog(context);
                dialog.setTitle("提示");
                dialog.setMessage("网络请求中。。。");
                dialog.show();
                final String[] Strs = Lk.split(",");
                for (int i = 0; i < Strs.length; i++) {
                    volleyTo = new VolleyTo();
                    final int finalI = i;
                    volleyTo.setUrl("set_traffic_light")
                            .setJsonObject("UserName", "user1")
                            .setJsonObject("number", Strs[i])
                            .setJsonObject("red", ho)
                            .setJsonObject("yellow", hu)
                            .setJsonObject("green", lv)
                            .setVolleyLo(new VolleyLo() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    if (jsonObject.has("RESULT")) {
                                        try {
                                            String result = jsonObject.getString("RESULT");
                                            if (result.equals("S")) {
                                                if (finalI == Strs.length - 1) {
                                                    handler.sendEmptyMessageDelayed(0x001, 1000);
                                                }
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                @Override
                                public void onErrorResponse(VolleyError volleyError) {

                                }
                            }).start();
                }
                break;
            case R.id.dia_closs:
                HldDialog.this.dismiss();
                break;
        }
    }
}
