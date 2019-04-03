package com.tab.test;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnClickListener {
    //声明ViewPager
    private ViewPager mViewPager;
    //适配器
    private FragmentPagerAdapter mAdapter;
    //装载Fragment的集合
    private List<Fragment> mFragments;

    //四个Tab对应的布局
    private LinearLayout mTabHome;
    private LinearLayout mTabMine;
    private LinearLayout mTabMessage;
    private LinearLayout mTabDashboard;

    //四个Tab对应的ImageButton
    private ImageButton mImgHome;
    private ImageButton mImgMine;
    private ImageButton mImgMessage;
    private ImageButton mImgDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initViews();//初始化控件
        initEvents();//初始化事件
        initDatas();//初始化数据
    }

    private void initDatas() {
        mFragments = new ArrayList<>();
        //将四个Fragment加入集合中
        mFragments.add(new HomeFragment());
        mFragments.add(new MineFragment());
        mFragments.add(new MessageFragment());
        mFragments.add(new DashboardFragment());

        //初始化适配器
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {//从集合中获取对应位置的Fragment
                return mFragments.get(position);
            }

            @Override
            public int getCount() {//获取集合中Fragment的总数
                return mFragments.size();
            }

        };
        //不要忘记设置ViewPager的适配器
        mViewPager.setAdapter(mAdapter);
        //设置ViewPager的切换监听
        mViewPager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            //页面滚动事件
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //页面选中事件
            @Override
            public void onPageSelected(int position) {
                //设置position对应的集合中的Fragment
                mViewPager.setCurrentItem(position);
                resetImgs();
                selectTab(position);
            }

            @Override
            //页面滚动状态改变事件
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initEvents() {
        //设置四个Tab的点击事件
        mTabHome.setOnClickListener(this);
        mTabMine.setOnClickListener(this);
        mTabMessage.setOnClickListener(this);
        mTabDashboard.setOnClickListener(this);
    }

    //初始化控件
    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mTabHome = (LinearLayout) findViewById(R.id.id_tab_home);
        mTabMine = (LinearLayout) findViewById(R.id.id_tab_mine);
        mTabMessage = (LinearLayout) findViewById(R.id.id_tab_message);
        mTabDashboard = (LinearLayout) findViewById(R.id.id_tab_dashboard);

        mImgHome = (ImageButton) findViewById(R.id.id_tab_home_img);
        mImgMine = (ImageButton) findViewById(R.id.id_tab_mine_img);
        mImgMessage = (ImageButton) findViewById(R.id.id_tab_message_img);
        mImgDashboard = (ImageButton) findViewById(R.id.id_tab_dashboard_img);
    }

    @Override
    public void onClick(View v) {
        //先将四个ImageButton置为灰色
        resetImgs();
        //根据点击的Tab切换不同的页面及设置对应的ImageButton为绿色
        switch (v.getId()) {
            case R.id.id_tab_home:
                selectTab(0);
                break;
            case R.id.id_tab_mine:
                selectTab(1);
                break;
            case R.id.id_tab_message:
                selectTab(2);
                break;
            case R.id.id_tab_dashboard:
                selectTab(3);
                break;
        }
    }

    private void selectTab(int i) {
        //根据点击的Tab设置对应的ImageButton为绿色
        switch (i) {
            case 0:
                mImgHome.setImageResource(R.mipmap.tab_home_on);
                break;
            case 1:
                mImgMine.setImageResource(R.mipmap.tab_mine_on);
                break;
            case 2:
                mImgMessage.setImageResource(R.mipmap.tab_message_on);
                break;
            case 3:
                mImgDashboard.setImageResource(R.mipmap.tab_dashboard_on);
                break;
        }
        //设置当前点击的Tab所对应的页面
        mViewPager.setCurrentItem(i);
    }

    //将四个ImageButton设置为灰色
    private void resetImgs() {
        mImgHome.setImageResource(R.mipmap.tab_home_off);
        mImgMine.setImageResource(R.mipmap.tab_mine_off);
        mImgMessage.setImageResource(R.mipmap.tab_message_off);
        mImgDashboard.setImageResource(R.mipmap.tab_dashboard_off);
    }
}
