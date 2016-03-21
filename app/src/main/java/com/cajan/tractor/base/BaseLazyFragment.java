package com.cajan.tractor.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ClassName ：BaseLazyFragment
 * Description ：懒加载的Fragment
 * Created : Administrator
 * Time : 2016/2/18
 * Version : 1.0
 */
public abstract class BaseLazyFragment extends BaseHeaderFragment {

  /**
   * 是否可见状态
   */
  private boolean isVisible;
  /**
   * 标志位，View已经初始化完成。
   */
  private boolean isPrepared;
  /**
   * 是否第一次加载
   */
  private boolean isFirstLoad = true;

  @Override protected View createView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    isFirstLoad = true;
    View view = initViews(inflater, container, savedInstanceState);
    isPrepared = true;
    lazyLoad();
    return view;
  }

  /**
   * 如果是与ViewPager一起使用，调用的是setUserVisibleHint
   *
   * @param isVisibleToUser 是否显示出来了
   */
  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (getUserVisibleHint()) {
      isVisible = true;
      onVisible();
    } else {
      isVisible = false;
      onInvisible();
    }
  }

  /**
   * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
   * 若是初始就show的Fragment 为了触发该事件 需要先hide再show
   *
   * @param hidden
   */
  @Override
  public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);
    if (!hidden) {
      isVisible = true;
      onVisible();
    } else {
      isVisible = false;
      onInvisible();
    }
  }

  protected void onVisible() {
    lazyLoad();
  }

  protected void onInvisible() {
  }

  /**
   * 要实现延迟加载Fragment内容,需要在 onCreateView
   * isPrepared = true;
   */
  protected void lazyLoad() {
    if (!isPrepared || !isVisible || !isFirstLoad) {
      return;
    }
    isFirstLoad = false;
    initData();
  }

  protected abstract View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

  protected abstract void initData();
}
