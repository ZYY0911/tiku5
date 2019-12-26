package com.example.tiku5.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tiku5.R;
import com.example.tiku5.bean.Wdyj;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class wdyjadapter extends BaseAdapter {
    private List<Wdyj> mwdyj;
    private Context context;

    public wdyjadapter(Context context, List<Wdyj> mwdyj) {
        this.context = context;
        this.mwdyj = mwdyj;
    }

    @Override
    public int getCount() {
        return mwdyj.size();
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
        Wdyj wdyj = mwdyj.get(i);
        View view1 = View.inflate(context, R.layout.wdyj_item, null);
        ViewHolder viewHolder  =new ViewHolder(view1);
        viewHolder.bt.setText(wdyj.getBt());
        viewHolder.sj.setText(wdyj.getSj());
        viewHolder.yj.setText(wdyj.getYj());
        return view1;
    }

    static
    class ViewHolder {
        @BindView(R.id.bt)
        TextView bt;
        @BindView(R.id.yj)
        TextView yj;
        @BindView(R.id.sj)
        TextView sj;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
