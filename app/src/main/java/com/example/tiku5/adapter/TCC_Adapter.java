package com.example.tiku5.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tiku5.R;
import com.example.tiku5.bean.TCC;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2019-12-26
 */
public class TCC_Adapter extends ArrayAdapter<TCC> {
    private int layout;

    public TCC_Adapter(Context context, int resource, List<TCC> objects) {
        super(context, resource, objects);
        layout = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TCC tcc = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.tccId.setText(tcc.getId()+"");
        holder.tccJl.setText(tcc.getDistance()+"米|"+tcc.getLocation());
        holder.tccKw.setText("空车位"+tcc.getCarport()+"个|停车费"+tcc.getPrice()+"元/"+tcc.getRate_type());
        holder.tccName.setText(tcc.getParking_name());
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.tcc_id)
        TextView tccId;
        @BindView(R.id.tcc_name)
        TextView tccName;
        @BindView(R.id.tcc_kw)
        TextView tccKw;
        @BindView(R.id.tcc_jl)
        TextView tccJl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
