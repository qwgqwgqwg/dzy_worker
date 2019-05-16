package com.example.app_dzy328;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import com.example.app_dzy328.fragment.AddresslistFragment;
import com.example.app_dzy328.fragment.ClockinFragment;
import com.example.app_dzy328.fragment.MessageFragment;
import com.example.app_dzy328.fragment.MineFragment;
import com.example.app_dzy328.fragment.WorkFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout messageLL;
    private LinearLayout clockinLL;
    private LinearLayout workLL;
    private LinearLayout addresslistLL;
    private LinearLayout mineLL;
    private ViewPager mViewPager;
    private FragmentManager mFragmentManager;
    List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private CofferPageAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1.获取系统的Fragment管理器
        mFragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_main);//activity设置一个layout布局
        //2.初始化4个Fragment对象，并且放在一个容器中List
        initFragmentList();
        //3.适配器
        mAdapter = new CofferPageAdapter(mFragmentManager,mFragmentList);
        //4.初始化view（界面容器）（大布局）
        initView();
        //5.初始化ViewPager
        initViewPager();
    }
    public void initFragmentList(){
        Fragment messageFragment = new MessageFragment();
        Fragment clockinFragment = new ClockinFragment();
        Fragment workFragment = new WorkFragment();
        Fragment addresslistFragment = new AddresslistFragment();
        Fragment mineFragment = new MineFragment();
        mFragmentList.add(messageFragment);
        mFragmentList.add(clockinFragment);
        mFragmentList.add(workFragment);
        mFragmentList.add(addresslistFragment);
        mFragmentList.add(mineFragment);
    }

    public void initView(){
        //设置监听器
        messageLL = (LinearLayout) findViewById(R.id.messageLL);
        clockinLL = (LinearLayout) findViewById(R.id.clockinLL);
        workLL = (LinearLayout) findViewById(R.id.workLL);
        addresslistLL = (LinearLayout) findViewById(R.id.addresslistLL);
        mineLL = (LinearLayout) findViewById(R.id.mineLL);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        messageLL.setOnClickListener(this);
        clockinLL.setOnClickListener(this);
        workLL.setOnClickListener(this);
        addresslistLL.setOnClickListener(this);
        mineLL.setOnClickListener(this);

    }

    @Override
    public  void onClick(View view){
        switch (view.getId()){
            case R.id.messageLL:
                mViewPager.setCurrentItem(0);
                updateBottomLinerLayoutSelect(true,false,false,false,false);
                break;
            case R.id.clockinLL:
                mViewPager.setCurrentItem(1);
                updateBottomLinerLayoutSelect(false,true,false,false,false);
                break;
            case R.id.workLL:
                mViewPager.setCurrentItem(2);
                updateBottomLinerLayoutSelect(false,false,true,false,false);
                break;
            case R.id.addresslistLL:
                mViewPager.setCurrentItem(3);
                updateBottomLinerLayoutSelect(false,false,false,true,false);
                break;
            case R.id.mineLL:
                mViewPager.setCurrentItem(4);
                updateBottomLinerLayoutSelect(false,false,false,false,true);
                break;
            default:
                break;
        }
    }
    private void updateBottomLinerLayoutSelect(boolean m,boolean c,boolean w,boolean a,boolean mi){
        messageLL.setSelected(m);
        clockinLL.setSelected(c);
        workLL.setSelected(w);
        addresslistLL.setSelected(a);
        mineLL.setSelected(mi);

    }

    class ViewPageOnPagerChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            boolean[] state = new boolean[mFragmentList.size()];
            state[position] = true;
            updateBottomLinerLayoutSelect(state[0],state[1],state[2],state[3],state[4]);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    private  void initViewPager(){
        //1.设置ViewPager监听
        mViewPager.addOnPageChangeListener(new ViewPageOnPagerChangeListener());
        //2.设置适配器
        mViewPager.setAdapter(mAdapter);
        //3.当App第一次加载时，设置productLL是选中状态，显示productFragment页面
        mViewPager.setCurrentItem(0);
        updateBottomLinerLayoutSelect(false,false,true,false,false);

    }

}