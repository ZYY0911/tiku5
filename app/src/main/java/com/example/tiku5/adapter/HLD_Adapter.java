package com.example.tiku5.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.tiku5.R;
import com.example.tiku5.bean.HLD;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2019-12-26
 */
public class HLD_Adapter extends ArrayAdapter<HLD> {
    private int layout;

    public interface MyClick {
        void myClick(int position, boolean xz, int lx);
    }


    private MyClick myClick;

    public void setMyClick(MyClick myClick) {
        this.myClick = myClick;
    }

    public HLD_Adapter(Context context, int resource, List<HLD> objects) {
        super(context, resource, objects);
        layout = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final HLD hld = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.hldLk.setText(hld.getId() + "");
        holder.hldHod.setText(hld.getRed() + "");
        holder.hldHud.setText(hld.getYellow() + "");
        holder.hldLvd.setText(hld.getGreen() + "");
        holder.hldOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClick.myClick(position, false, 1);
            }
        });
        holder.hldCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClick.myClick(position, holder.hldCb.isChecked(), 2);
            }
        });
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.hld_lk)
        TextView hldLk;
        @BindView(R.id.hld_hod)
        TextView hldHod;
        @BindView(R.id.hld_hud)
        TextView hldHud;
        @BindView(R.id.hld_lvd)
        TextView hldLvd;
        @BindView(R.id.hld_cb)
        CheckBox hldCb;
        @BindView(R.id.hld_ok)
        Button hldOk;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
