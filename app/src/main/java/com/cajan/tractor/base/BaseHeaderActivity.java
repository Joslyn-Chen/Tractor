package com.cajan.tractor.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.cajan.tractor.R;
import com.cajan.tractor.widget.HeaderBar;

/**
 * ClassName ：BaseHeaderActivity
 * Description ：基类
 * Created : Administrator
 * Time : 2016/2/17
 * Version : 1.0
 */
public abstract class BaseHeaderActivity extends AppCompatActivity {

  public Context mContext;
  //  头部
  public HeaderBar mHeaderBar;
  //  内容容器
  public LinearLayout mContentContainer;
  //  加载
  public ProgressDialog mProDialog;

  public BaseHeaderActivity() {
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      Window window = getWindow();
      window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
          WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }
    //EventBus.getDefault().register(this);
    mContext = getApplicationContext();
    this.initViews();
  }

  public void initViews() {
    super.setContentView(this.getFrameLayoutId());
    this.mHeaderBar = this.getHeaderBar();
    this.mContentContainer = this.getContentContainer();
    this.mHeaderBar.setLeftImageViewOCL(new View.OnClickListener() {
      public void onClick(View v) {
        finish();
      }
    });
  }

  public void setContentView(int layoutResID) {
    View view = LayoutInflater.from(this).inflate(layoutResID, null);
    view.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT));
    this.mContentContainer.addView(view);
  }

  public int getFrameLayoutId() {
    return R.layout.layout_content_with_headerbar;
  }

  public HeaderBar getHeaderBar() {
    return (HeaderBar) this.findViewById(R.id.main_content_frame_header);
  }

  public LinearLayout getContentContainer() {
    return (LinearLayout) this.findViewById(R.id.main_content_frame_content);
  }


  @Override public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
    super.onSaveInstanceState(outState, outPersistentState);
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
   * 完全自定义View
   */
  public void setContentViewSupper(int layoutResID) {
    super.setContentView(layoutResID);
  }

  /**
   * 设置Activity Title
   */
  public void setHeaderTitle(int id) {
    mHeaderBar.setHeaderTitle(id);
  }

  /**
   * 设置Activity Title
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
    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
  }

  /**
   * 打开键盘0
   */
  public void showKeyboardAt() {
    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
  }

  /**
   * 隐藏键盘
   */
  public void hideKeyboardForCurrentFocus() {
    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    if (getCurrentFocus() != null) {
      imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
  }

  /**
   * 退出全屏
   */
  protected void exitFullScreen() {
    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
  }

  /*private void setToolbar(){
    final Toolbar mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
    TextView title = (TextView) mToolbar.findViewById(R.id.main_title);
    title.setText("Tractor");
    setSupportActionBar(mToolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    mToolbar.setNavigationIcon(R.drawable.ic_top_back);
    mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Toast.makeText(mContext,"返回",Toast.LENGTH_SHORT).show();
        Snackbar.make(mToolbar, "返回SSSS", Snackbar.LENGTH_SHORT).show();
      }
    });

    mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
          case R.id.action_one:
            Toast.makeText(mContext,"11111",Toast.LENGTH_SHORT).show();
            break;
          case R.id.action_two:
            Toast.makeText(mContext,"2222222222",Toast.LENGTH_SHORT).show();
            break;
        }
        return true;
      }
    });
  }*/

  @Override protected void onDestroy() {
    try {
      //EventBus.getDefault().unregister(this);
    } catch (Exception e) {
    }
    super.onDestroy();
    //mLoadingDialog = null;
    if (mProDialog != null) {
      mProDialog.dismiss();
      mProDialog = null;
    }
  }
}
