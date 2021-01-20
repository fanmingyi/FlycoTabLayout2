package com.flyco.tablayoutsamples.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.SlidingTabLayout2;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.widget.MsgView;
import com.flyco.tablayoutsamples.R;
import com.flyco.tablayoutsamples.utils.ViewFindUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SlidingTab2Activity extends AppCompatActivity implements OnTabSelectListener {
  private Context mContext = this;
  private ArrayList<Fragment> mFragments = new ArrayList<>();

  private final String[] mTitles = {
      "热门", "iOS", "Android"
      , "前端", "后端", "设计", "工具资源"
  };
  private SlidingTab2Activity.MyPagerAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sliding_tab2);

    for (String title : mTitles) {
      mFragments.add(SimpleCardFragment.getInstance(title));
    }

    View decorView = getWindow().getDecorView();
    ViewPager2 vp = ViewFindUtils.find(decorView, R.id.vp);
    mAdapter = new SlidingTab2Activity.MyPagerAdapter(this);
    vp.setAdapter(mAdapter);

    /** 默认 */
    SlidingTabLayout2 tabLayout_1 = ViewFindUtils.find(decorView, R.id.tl_1);
    /**自定义部分属性*/
    SlidingTabLayout2 tabLayout_2 = ViewFindUtils.find(decorView, R.id.tl_2);
    /** 字体加粗,大写 */
    SlidingTabLayout2 tabLayout_3 = ViewFindUtils.find(decorView, R.id.tl_3);
    /** tab固定宽度 */
    SlidingTabLayout2 tabLayout_4 = ViewFindUtils.find(decorView, R.id.tl_4);
    /** indicator固定宽度 */
    SlidingTabLayout2 tabLayout_5 = ViewFindUtils.find(decorView, R.id.tl_5);
    /** indicator圆 */
    SlidingTabLayout2 tabLayout_6 = ViewFindUtils.find(decorView, R.id.tl_6);
    /** indicator矩形圆角 */
    final SlidingTabLayout2 tabLayout_7 = ViewFindUtils.find(decorView, R.id.tl_7);
    /** indicator三角形 */
    SlidingTabLayout2 tabLayout_8 = ViewFindUtils.find(decorView, R.id.tl_8);
    /** indicator圆角色块 */
    SlidingTabLayout2 tabLayout_9 = ViewFindUtils.find(decorView, R.id.tl_9);
    /** indicator圆角色块 */
    SlidingTabLayout2 tabLayout_10 = ViewFindUtils.find(decorView, R.id.tl_10);

    tabLayout_1.setViewPager(vp, mTitles);
    tabLayout_2.setViewPager(vp, mTitles);
    tabLayout_2.setOnTabSelectListener(this);
    tabLayout_3.setViewPager(vp, mTitles);
    tabLayout_4.setViewPager(vp, mTitles);
    tabLayout_5.setViewPager(vp, mTitles);
    tabLayout_6.setViewPager(vp, mTitles);
    tabLayout_7.setViewPager(vp, mTitles);
    tabLayout_8.setViewPager(vp, mTitles, this, mFragments);
    tabLayout_9.setViewPager(vp, mTitles);
    tabLayout_10.setViewPager(vp, mTitles);

    vp.setCurrentItem(4);

    tabLayout_1.showDot(4);
    tabLayout_3.showDot(4);
    tabLayout_2.showDot(4);

    tabLayout_2.showMsg(3, 5);
    tabLayout_2.setMsgMargin(3, 0, 10);
    MsgView rtv_2_3 = tabLayout_2.getMsgView(3);
    if (rtv_2_3 != null) {
      rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
    }

    tabLayout_2.showMsg(5, 5);
    tabLayout_2.setMsgMargin(5, 0, 10);

    //        tabLayout_7.setOnTabSelectListener(new OnTabSelectListener() {
    //            @Override
    //            public void onTabSelect(int position) {
    //                Toast.makeText(mContext, "onTabSelect&position--->" + position, Toast.LENGTH_SHORT).show();
    //            }
    //
    //            @Override
    //            public void onTabReselect(int position) {
    //                mFragments.add(SimpleCardFragment.getInstance("后端"));
    //                mAdapter.notifyDataSetChanged();
    //                tabLayout_7.addNewTab("后端");
    //            }
    //        });
  }

  @Override
  public void onTabSelect(int position) {
    Toast.makeText(mContext, "onTabSelect&position--->" + position, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onTabReselect(int position) {
    Toast.makeText(mContext, "onTabReselect&position--->" + position, Toast.LENGTH_SHORT).show();
  }

  private class MyPagerAdapter extends androidx.viewpager2.adapter.FragmentStateAdapter {

    public MyPagerAdapter(
        FragmentActivity fragmentActivity) {
      super(fragmentActivity);
    }

    public MyPagerAdapter(
        Fragment fragment) {
      super(fragment);
    }

    public MyPagerAdapter(
        FragmentManager fragmentManager,
        Lifecycle lifecycle) {
      super(fragmentManager, lifecycle);
    }

    public Fragment createFragment(int position) {
      return mFragments.get(position);
    }

    @Override public int getItemCount() {
      return mFragments.size();
    }
  }
}
