package com.example.tiku5.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tiku5.R;
import com.example.tiku5.bean.TCCLS;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2019-12-26
 */
public class TCCLSAdapter extends ArrayAdapter<TCCLS> {
    private int layout;

    public TCCLSAdapter(Context context, int resource, List<TCCLS> objects) {
        super(context, resource, objects);
        layout = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TCCLS tccls = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        /**
         * {
         *             "id": 2,
         *             "number": 3,
         *             "plate": "³A10003",
         *             "entrance": "2014-5-15 05:30:07",
         *             "exit": "2014-6-15 05:30:07",
         *             "price": 3
         *         },
         */
        holder.lsXh.setText(tccls.getId() + "");
        holder.lsBh.setText(tccls.getNumber() + "");
        holder.lsCp.setText(tccls.getPlate());
        holder.lsRc.setText(tccls.getEntrance());
        holder.lsCc.setText(tccls.getExit());
        holder.lsJe.setText(tccls.getPrice() + "");
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.ls_xh)
        TextView lsXh;
        @BindView(R.id.ls_bh)
        TextView lsBh;
        @BindView(R.id.ls_cp)
        TextView lsCp;
        @BindView(R.id.ls_rc)
        TextView lsRc;
        @BindView(R.id.ls_cc)
        TextView lsCc;
        @BindView(R.id.ls_je)
        TextView lsJe;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
