package com.example.tiku5.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tiku5.R;
import com.example.tiku5.bean.Ssjt;

import java.util.List;

public class ssjtadapter extends BaseAdapter {
    private List<Ssjt> mssjt;
    private Context context;
    public ssjtadapter(Context context,List<Ssjt> mssjt)
    {
        this.context=context;
        this.mssjt=mssjt;
    }
    @Override
    public int getCount() {
        return mssjt.size();
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
        Ssjt  ssjt = mssjt.get(i);
        View view1 = View.inflate(context, R.layout.ssjt_item,null);
        TextView textView = view1.findViewById(R.id.luxian);
        textView.setText(ssjt.getLuhao()+" "+ssjt.getLuxain());
        return view1;
    }
}
