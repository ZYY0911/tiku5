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

public class czjladapter extends BaseAdapter {
    private List<Czjl> mczjl;
    private Context context;

    public czjladapter(Context context, List<Czjl> mczjl) {
        this.context = context;
        this.mczjl = mczjl;
    }

    @Override
    public int getCount() {
        return mczjl.size();
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
        Czjl czjl = mczjl.get(i);
        View view1 = View.inflate(context, R.layout.etcczjl_item, null);
        ViewHolder viewHolder =new ViewHolder(view1);
        viewHolder.shijian1.setText(czjl.getTime().substring(0,10));
        viewHolder.shijian2.setText(czjl.getTime());
        viewHolder.xingqi.setText(czjl.getXingqi());
        viewHolder.chepaihao.setText("车牌号："+czjl.getChepai());
        viewHolder.chongzhiren.setText("充值人："+czjl.getChongzhiren());
        viewHolder.chongzhi.setText("充值："+czjl.getJine());
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
