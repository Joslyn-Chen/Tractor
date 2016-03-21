package com.cajan.tractor.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cajan.tractor.R;

/**
 * ClassName ：BaseActivity
 * Description ：BaseActivity
 * Created : Administrator
 * Time : 2016/3/14
 * Version : 1.0
 */
public class BaseActivity extends AppCompatActivity {

  public Context mContext;
  public Toolbar mToolbar;
  public TextView mTitle;
  public FrameLayout mContentContainer;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      Window window = getWindow();
      window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
          WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }
    mContext = getApplicationContext();
    super.setContentView(R.layout.layout_content_with_toolbar);
    setToolbar();
  }

  private void setToolbar() {
    mContentContainer = (FrameLayout) findViewById(R.id.main_frame);
    mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
    mTitle = (TextView) mToolbar.findViewById(R.id.main_title);
    mTitle.setError("错误提示！");

    setSupportActionBar(mToolbar);
    if (getSupportActionBar() != null) {
      //  不显示系统的Title
      getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    mToolbar.setNavigationIcon(R.drawable.ic_top_back);
    mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        finish();
      }
    });
  }

  /**
   *  设置View
   * @param layoutResID
   */
  public void setContentView(int layoutResID) {
    View view = LayoutInflater.from(this).inflate(layoutResID, null);
    view.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT));
    this.mContentContainer.addView(view);
  }

  /**
   *  设置标题
   * @param title
   */
  public void setTitle(CharSequence title){
    mTitle.setText(title);
  }


  @Override protected void onDestroy() {
    super.onDestroy();
  }
}
