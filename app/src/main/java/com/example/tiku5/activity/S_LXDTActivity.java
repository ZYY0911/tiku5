package com.example.tiku5.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.example.tiku5.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_LXDTActivity extends AppCompatActivity {
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.map_back)
    ImageButton Backmap;
    @BindView(R.id.location)
    ImageButton location;
    @BindView(R.id.change_layer)
    ImageButton Layerchange;
    @BindView(R.id.marker)
    ImageButton marker;
    @BindView(R.id.line_distance)
    ImageButton Distanceline;
    @BindView(R.id.info)
    TextView info;
    private AMap aMap;
    private PopupWindow popupWindow;
    private  int xOffset,yOffset;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_lxdtactivity);
        ButterKnife.bind(this);
        initializeWithState(savedInstanceState);

    }

    private void initPopupData() {
        View listLayer = View.inflate(this,R.layout.layout_list,null);
        TextView naviBtn = listLayer.findViewById(R.id.layer_navi);
        TextView nightBtn = listLayer.findViewById(R.id.layer_night);
        TextView normalBtn = listLayer.findViewById(R.id.layer_normal);
        TextView sateelliteBtn = listLayer.findViewById(R.id.layer_satellite);
        TextView tarafficBtn = listLayer.findViewById(R.id.layer_traffic);
        naviBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aMap.setMapType(AMap.MAP_TYPE_NAVI);
                info.setText("已切换到导航视图");
                aMap.setTrafficEnabled(false);
                if (popupWindow!=null&&popupWindow.isShowing()) popupWindow.dismiss();
            }
        });
        nightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aMap.setMapType(AMap.MAP_TYPE_NIGHT);
                info.setText("已切换到夜景视图");
                aMap.setTrafficEnabled(false);
                if (popupWindow!=null&&popupWindow.isShowing()) popupWindow.dismiss();
            }
        });
        normalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                info.setText("已切换到白昼视图");
                aMap.setTrafficEnabled(false);
                if (popupWindow!=null&&popupWindow.isShowing()) popupWindow.dismiss();
            }
        });
        sateelliteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
                info.setText("已切换到卫星视图");
                aMap.setTrafficEnabled(false);
                if (popupWindow!=null&&popupWindow.isShowing()) popupWindow.dismiss();
            }
        });
        tarafficBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aMap.setTrafficEnabled(true);
                info.setText("已切换到交通视图");
                if (popupWindow!=null&&popupWindow.isShowing()) popupWindow.dismiss();
            }
        });

         popupWindow  =new PopupWindow(listLayer, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.AlpahAnimation);
        listLayer.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);
        Layerchange.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);
        xOffset  =-listLayer.getMeasuredWidth();
        yOffset=-Layerchange.getMeasuredHeight();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mapView.onSaveInstanceState(outState);
    }
    private void initializeWithState(Bundle savedInstanceState)
    {
        mapView.onCreate(savedInstanceState);
        if (aMap==null) aMap= mapView.getMap();
    }

    @OnClick({R.id.map_back, R.id.change_layer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.map_back:
                finish();
                break;
            case R.id.change_layer:
                initPopupData();
                if (popupWindow.isShowing())
                {
                    popupWindow.dismiss();
                }else {
                    popupWindow.showAsDropDown(Layerchange,xOffset,yOffset);
                }
                break;
        }
    }
}
