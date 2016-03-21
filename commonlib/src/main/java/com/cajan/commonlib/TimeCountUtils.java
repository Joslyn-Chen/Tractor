package com.cajan.commonlib;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Button;

/**
 * ClassName ：TimeCountUtils
 * Description ：倒计时
 * Created : Administrator
 * Time : 2015/12/30
 * Version : 1.0
 */
public class TimeCountUtils extends CountDownTimer {

  private Context mContext;
  private Button mButton;

  /**
   * @param millisInFuture The number of millis in the future from the call
   * to {@link #start()} until the countdown is done and {@link #onFinish()}
   * is called.
   * @param countDownInterval The interval along the way to receive
   * {@link #onTick(long)} callbacks.
   */
  public TimeCountUtils(long millisInFuture, long countDownInterval, Context context,
      Button button) {
    super(millisInFuture, countDownInterval);
    this.mContext = context;
    this.mButton = button;
  }

  @Override public void onTick(long millisUntilFinished) {
    mButton.setClickable(false);
    mButton.setText(millisUntilFinished / 1000 + "秒");
    mButton.setTextColor(mContext.getResources().getColor(R.color.red));
    //        Spannable span = new SpannableString(mButton.getText().toString());//获取按钮的文字
    //        span.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时时间显示为红色
    //        mButton.setBackgroundResource(R.color.message_text);
  }

  @Override public void onFinish() {
    mButton.setClickable(true);
    mButton.setText("获取");
    mButton.setTextColor(mContext.getResources().getColor(R.color.black));
    //        mButton.setBackgroundResource(R.drawable.border_transparent_around_black);
  }
}
