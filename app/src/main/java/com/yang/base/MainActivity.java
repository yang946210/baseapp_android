package com.yang.base;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.yang.base.base.BaseActivity;
import com.yang.base.fragment.AudioRecorderFragment;
import com.yang.base.fragment.BaseTestFragment;
import com.yang.base.fragment.PlugTestFragment;
import com.yang.base.fragment.SingleFragment;
import com.yang.base.util.AudioRecordUtil;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends BaseActivity {

    private TabLayout tl_title;

    private ViewPager vp_content;

    private String[] tabList;

    private List<Fragment> fragmentList;

    private BaseTestFragment baseTestFragment;

    private AudioRecorderFragment audioRecorderFragment;

    private PlugTestFragment plugTestFragment;

    private SingleFragment singleFragment;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findViews() {
        tl_title=findViewById(R.id.tl_title);
        vp_content=findViewById(R.id.vp_content);
    }

    @Override
    protected void init(){
        tabList = new String[]{"Base","audio", "plug","single"};
        fragmentList=new ArrayList<>();
        baseTestFragment=new BaseTestFragment();
        audioRecorderFragment=new AudioRecorderFragment();
        plugTestFragment=new PlugTestFragment();
        singleFragment=new SingleFragment();
        fragmentList.add(baseTestFragment);
        fragmentList.add(audioRecorderFragment);
        fragmentList.add(plugTestFragment);
        fragmentList.add(singleFragment);
        initViewPager();
        tl_title.setupWithViewPager(vp_content);
    }

    private void initViewPager(){
        vp_content.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager(),0) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabList[position];
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        AudioRecordUtil.getInstance().closeAudioRecord();
    }
}
