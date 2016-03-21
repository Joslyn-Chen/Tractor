package com.cajan.tractor.base;

import android.app.Application;
import com.cajan.commonlib.L;

/**
 * ClassName ：BaseApplication
 * Description ：BaseApplication
 * Created : Administrator
 * Time : 2016/2/18
 * Version : 1.0
 */
public class BaseApplication extends Application {
  @Override public void onCreate() {
    super.onCreate();
    L.init();
  }

  @Override public void onTerminate() {
    super.onTerminate();
  }
}
