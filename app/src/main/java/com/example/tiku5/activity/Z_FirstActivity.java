package com.example.tiku5.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiku5.R;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2019-12-26
 */
public class Z_FirstActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer)
    DrawerLayout drawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        ButterKnife.bind(this);
        initView();
        initClick();
    }

    private void initClick() {
        /*if (navView.getMenu().getItem(1).getItemId()==R.id.second){
            navView.getMenu().getItem(1).setVisible(false);
        }*/
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Class myClass = null;
                switch (menuItem.getItemId()) {
                    case R.id.yjfk:
                        myClass = S_YJFKActivity.class;
                        break;
                    case R.id.wlsz:
                        myClass = S_WLSZActivity.class;
                        break;
                    case R.id.etc:
                        myClass = S_GSETCActivity.class;
                        break;
                    case R.id.sjfx:
                        myClass = S_SJFXActivity.class;
                        break;
                    case R.id.lxdt:
                        myClass = S_LXDTActivity.class;
                        break;
                    case R.id.ssjt:
                        myClass = S_SSJTActivity.class;
                        break;
                    case R.id.wzfx:
                        myClass = S_WZLXFXActivity.class;
                        break;
                    case R.id.grzx:
                        myClass = S_ZHSZActivity.class;
                        break;
                    case R.id.first:
                        myClass = Z_SSHJActivity.class;
                        break;
                    case R.id.second:
                        myClass = Z_HLDActivity.class;
                        break;
                    case R.id.third:
                        myClass = Z_TCCActivity.class;
                        break;
                    case R.id.forth:
                        myClass = Z_CYActivity.class;
                        break;
                }
                startActivity(new Intent(Z_FirstActivity.this, myClass));
                drawer.closeDrawers();
                return true;
            }
        });
    }

    private void initView() {
        title.setText("主页面");
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        drawer.openDrawer(GravityCompat.START);
    }
}
