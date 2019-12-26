package com.example.tiku5.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.tiku5.R;

import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Create by 张瀛煜 on 2019-12-26
 */
public class CY_Adapter extends BaseExpandableListAdapter {
    private Map<String, List<String>> map;
    private String[] arr = {"车辆违章", "ETC消费", "停车消费"};

    public CY_Adapter(Map<String, List<String>> map) {
        this.map = map;
    }

    @Override
    public int getGroupCount() {
        return map.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return map.get(arr[groupPosition]).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ct_fu, parent, false);
        TextView tv_infos = view.findViewById(R.id.tv_infos);
        tv_infos.setText(arr[groupPosition]);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        List<String> strings = map.get(arr[groupPosition]);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ct_fu, parent, false);
        TextView tv_infos = view.findViewById(R.id.tv_infos);
        tv_infos.setTextSize(20);
        tv_infos.setText(strings.get(childPosition));
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
