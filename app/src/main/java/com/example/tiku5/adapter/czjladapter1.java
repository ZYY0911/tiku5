package com.example.tiku5.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tiku5.R;
import com.example.tiku5.bean.Czjl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class czjladapter1 extends BaseAdapter {

    private Context context;

    public czjladapter1(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return 1;
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

        View view1 = View.inflate(context, R.layout.etcczjl_item1, null);
        return view1;
    }

    static
    class ViewHolder {
        @BindView(R.id.shijian1)
        TextView shijian1;
        @BindView(R.id.xingqi)
        TextView xingqi;
        @BindView(R.id.chongzhiren)
        TextView chongzhiren;
        @BindView(R.id.chepaihao)
        TextView chepaihao;
        @BindView(R.id.chongzhi)
        TextView chongzhi;
        @BindView(R.id.shijian2)
        TextView shijian2;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
