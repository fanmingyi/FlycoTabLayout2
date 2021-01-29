package com.flyco.tablayout;

import android.content.Context;
import android.util.AttributeSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** 滑动TabLayout,对于ViewPager的依赖性强 */

/**
 * 请使用viewPager2
 */

public class SlidingTabLayout extends SlidingTabLayoutBase
    implements ViewPager.OnPageChangeListener {
  private ViewPager mViewPager;

  public SlidingTabLayout(Context context) {
    super(context);
  }

  public SlidingTabLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public SlidingTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public void setViewPager(ViewPager vp, List<String> titles) {

    if (vp == null || vp.getAdapter() == null) {
      throw new IllegalStateException("ViewPager or ViewPager adapter can not be NULL !");
    }

    if (titles == null || titles.size() == 0) {
      throw new IllegalStateException("Titles can not be EMPTY !");
    }

    if (titles.size() != vp.getAdapter().getCount()) {
      throw new IllegalStateException("Titles length must be the same as the page count !");
    }

    this.mViewPager = vp;
    mTitles = new ArrayList<>();
    mTitles.addAll(titles);
    this.mViewPager.removeOnPageChangeListener(this);
    this.mViewPager.addOnPageChangeListener(this);
    notifyDataSetChanged();
  }

  /** 关联ViewPager,用于不想在ViewPager适配器中设置titles数据的情况 */
  public void setViewPager(ViewPager vp, String[] titles) {
    ArrayList<String> titleList = new ArrayList<>();
    Collections.addAll(titleList, titles);
    setViewPager(vp, titleList);
  }

  /** 关联ViewPager,用于连适配器都不想自己实例化的情况 */
  public void setViewPager(ViewPager vp, List<String> titles, FragmentActivity fa,
      ArrayList<Fragment> fragments) {
    mTitles = new ArrayList<>();
    mTitles.addAll(titles);
    if (vp == null) {
      throw new IllegalStateException("ViewPager can not be NULL !");
    }

    if (titles == null || titles.size() == 0) {
      throw new IllegalStateException("Titles can not be EMPTY !");
    }

    this.mViewPager = vp;
    this.mViewPager.setAdapter(
        new InnerPagerAdapter(fa.getSupportFragmentManager(), fragments, titles));

    this.mViewPager.removeOnPageChangeListener(this);
    this.mViewPager.addOnPageChangeListener(this);
    notifyDataSetChanged();
  }

  /** 关联ViewPager,用于连适配器都不想自己实例化的情况 */
  public void setViewPager(ViewPager vp, String[] titles, FragmentActivity fa,
      ArrayList<Fragment> fragments) {

    ArrayList<String> stringList = new ArrayList<>();
    Collections.addAll(stringList, titles);
    setViewPager(vp, stringList, fa,
        fragments);
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    super.onPageScrolled(position, positionOffset, positionOffsetPixels);
  }

  @Override
  public void onPageSelected(int position) {
    updateTabSelection(position);
  }

  @Override
  public void onPageScrollStateChanged(int state) {
  }

  //setter and getter
  public void setCurrentTab(int currentTab) {
    this.mCurrentTab = currentTab;
    mViewPager.setCurrentItem(currentTab);
  }

  public void setCurrentTab(int currentTab, boolean smoothScroll) {
    this.mCurrentTab = currentTab;
    mViewPager.setCurrentItem(currentTab, smoothScroll);
  }

  @Override int getPageCount() {
    PagerAdapter adapter = mViewPager.getAdapter();
    if (adapter != null) {
      return adapter.getCount();
    }
    return 0;
  }

  @Override int getCurrentItem() {
    return mViewPager.getCurrentItem();
  }

  @Override void setCurrentItem(int position) {
    this.mCurrentTab = position;
    mViewPager.setCurrentItem(position);
  }

  @Override void setCurrentItem(int position, boolean smooth) {
    this.mCurrentTab = position;
    mViewPager.setCurrentItem(position, smooth);
  }
}
