package com.example.tiku5.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tiku5.AppClient;
import com.example.tiku5.R;
import com.example.tiku5.bean.Etcye;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class etcyeadapter extends BaseAdapter {
    private List<Etcye> metcye;
    private Context context;

    public etcyeadapter(Context context, List<Etcye> metcye) {
        this.context = context;
        this.metcye = metcye;
    }

    @Override
    public int getCount() {
        return metcye.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Etcye etcye = metcye.get(i);
        View view1 = View.inflate(context, R.layout.etcye_item, null);
        ViewHolder viewHolder = new ViewHolder(view1);
        viewHolder.bianhao.setText(etcye.getNumber() + "");
        viewHolder.chezhu.setText("车主：" + etcye.getOwner());
        viewHolder.chepai.setText(etcye.getPlate());
        viewHolder.yue.setText("余额：" + etcye.getBalance() + "元");
        if (etcye.getBrand().equals("宝马")) {
            viewHolder.tubiao.setImageResource(R.drawable.baoma);
        }
        if (etcye.getBrand().equals("奔驰")) {
            viewHolder.tubiao.setImageResource(R.drawable.benchi);
        }
        if (etcye.getBrand().equals("奥迪")) {
            viewHolder.tubiao.setImageResource(R.drawable.audi);
        }
        if (etcye.getBrand().equals("中华")) {
            viewHolder.tubiao.setImageResource(R.drawable.zhonghua);
        }
        if (etcye.getBalance()<Integer.parseInt(AppClient.getYz()))
        {
            viewHolder.beijing.setBackgroundColor(Color.RED);
        }else {
            viewHolder.beijing.setBackgroundColor(Color.WHITE);
        }
        return view1;
    }


    static
    class ViewHolder {
        @BindView(R.id.bianhao)
        TextView bianhao;
        @BindView(R.id.tubiao)
        ImageView tubiao;
        @BindView(R.id.chepai)
        TextView chepai;
        @BindView(R.id.chezhu)
        TextView chezhu;
        @BindView(R.id.yue)
        TextView yue;
        @BindView(R.id.beijing)
        RelativeLayout beijing;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
