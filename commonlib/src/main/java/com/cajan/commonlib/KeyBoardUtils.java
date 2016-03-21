package com.cajan.commonlib;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * ClassName ：KeyBoardUtils
 * Description : 软键盘工具
 * Created : Cajan
 * Time : 2015/9/21
 * Version : 1.0
 */
public class KeyBoardUtils {

  /**
   * 打卡软键盘
   *
   * @param mEditText 输入框
   * @param mContext 上下文
   */
  public static void openKeybord(EditText mEditText, Context mContext) {
    InputMethodManager imm =
        (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
  }

  /**
   * 关闭软键盘
   *
   * @param mContext 上下文
   */
  public static void closeKeybord(View view, Context mContext) {
    InputMethodManager imm =
        (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
  }
}
