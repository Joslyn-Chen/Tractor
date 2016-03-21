package com.cajan.tractor.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.cajan.tractor.R;
import com.cajan.tractor.widget.HeaderBar;
import java.lang.reflect.Field;

/**
 * ClassName ：BaseHeaderFragment
 * Description ：基类
 * Created : Administrator
 * Time : 2016/2/18
 * Version : 1.0
 */
public abstract class BaseHeaderFragment extends Fragment {

  public Context mContext;
  public HeaderBar mHeaderBar;
  private LinearLayout mContentContainer;
  public ProgressDialog mProDialog;

  public BaseHeaderFragment() {
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mContext = getActivity().getApplicationContext();
  }

  /**
   * 不需要Header可重写此方法，需要Head而必须实现createView
   */
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    ViewGroup view = (ViewGroup) inflater.inflate(getFrameLayoutId(), null);
    mContentContainer = (LinearLayout) view.findViewById(R.id.main_content_frame_content);
    mHeaderBar = (HeaderBar) view.findViewById(R.id.main_content_frame_header);
    mHeaderBar.setLeftImageViewOCL(new View.OnClickListener() {
      @Override public void onClick(View view) {
        //  空间听
      }
    });

    View contentView = createView(inflater, view, savedInstanceState);
    contentView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
    mContentContainer.addView(contentView);

    return view;
  }

  protected View createView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return null;
  }

  protected int getFrameLayoutId() {
    return R.layout.layout_content_with_headerbar;
  }

  public Context getApplicationContext() {
    return mContext;
  }

  /**
   * 显示Loading界面
   */
  public void displayLoading(Context context) {
    if (mProDialog == null) {
      mProDialog = ProgressDialog.show(context, null, "正在加载......", true, true);
    } else {
      mProDialog.show();
    }
  }

  /**
   * 显示Loading界面,有文字提示
   */
  public void displayLoading(Context context, String msg) {
    if (mProDialog == null) {
      mProDialog = ProgressDialog.show(context, null, msg, true, true);
    } else {
      mProDialog.show();
    }
  }

  /**
   * 关闭Loading界面
   */
  public void dismissLoading() {
    if (mProDialog != null && mProDialog.isShowing()) {
      mProDialog.dismiss();
    }
  }

  /**
   * 设置Fragment Title
   */
  public void setHeaderTitle(int id) {
    mHeaderBar.setHeaderTitle(id);
  }

  /**
   * 设置Fragment Title
   */
  public void setHeaderTitle(String title) {
    mHeaderBar.setHeaderTitle(title);
  }

  /**
   * 隐藏头部
   */
  public void hideHeader() {
    mHeaderBar.setVisibility(View.GONE);
  }

  /**
   * 打开键盘
   */
  public void showKeyboardAtView(View view) {
    InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
  }

  /**
   * 打开键盘0
   */
  public void showKeyboardAt() {
    InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
  }

  /**
   * 隐藏键盘
   */
  public void hideKeyboardForCurrentFocus() {
    InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
    if (getActivity().getCurrentFocus() != null) {
      imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }
    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
  }

  @Override public void onDetach() {
    super.onDetach();
    try {
      Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
      childFragmentManager.setAccessible(true);
      childFragmentManager.set(this, null);
    } catch (NoSuchFieldException e) {
      throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  @Override public void onDestroyView() {
    //        try {
    //            EventBus.getDefault().unregister(this);
    //        } catch (Exception e) {
    //        }
    super.onDestroyView();
    mProDialog = null;
  }
}
