package com.cajan.tractor.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cajan.tractor.R;

/**
 * ClassName ：HeaderBar
 * Description : 统一头部
 * Created : Cajan
 * Time : 2015/9/16
 * Version : 1.0
 */
public class HeaderBar extends RelativeLayout {

  private Context mContext;
  private ImageView mLeftImageView;
  private TextView mCenterTitleTextView;
  private RelativeLayout mLeftViewContainer;
  private RelativeLayout mRightViewContainer;
  private RelativeLayout mCenterViewContainer;

  public HeaderBar(Context context) {
    this(context, null);
  }

  public HeaderBar(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public HeaderBar(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    mContext = context;
    LayoutInflater.from(context).inflate(getHeaderViewLayoutId(), this);
    mLeftImageView = (ImageView) findViewById(R.id.header_bar_left_image);
    mCenterTitleTextView = (TextView) findViewById(R.id.header_bar_title);
    mLeftViewContainer = (RelativeLayout) findViewById(R.id.header_bar_left_container);
    mCenterViewContainer = (RelativeLayout) findViewById(R.id.header_bar_center_container);
    mRightViewContainer = (RelativeLayout) findViewById(R.id.header_bar_right_container);
  }

  protected int getHeaderViewLayoutId() {
    return R.layout.layout_headerbar;
  }

  public ImageView getLeftImageView() {
    return mLeftImageView;
  }

  public void setHeaderTitle(int id) {
    setHeaderTitle(mContext.getString(id));
  }

  public void setHeaderTitle(String title) {
    mCenterTitleTextView.setText(title);
  }

  public void setLeftImageViewOCL(OnClickListener l) {
    getLeftImageView().setOnClickListener(l);
  }

  private LayoutParams makeLayoutParams(View view) {
    ViewGroup.LayoutParams lpOld = view.getLayoutParams();
    LayoutParams lp;
    if (lpOld == null) {
      lp = new LayoutParams(-2, -1);
    } else {
      lp = new LayoutParams(lpOld.width, lpOld.height);
    }
    return lp;
  }

  /**
   * set customized view to left side
   *
   * @param view the view to be added to left side
   */
  public void setCustomizedLeftView(View view) {
    mLeftImageView.setVisibility(GONE);
    LayoutParams lp = makeLayoutParams(view);
    lp.addRule(CENTER_VERTICAL);
    lp.addRule(ALIGN_PARENT_LEFT);
    getLeftViewContainer().addView(view, lp);
  }

  /**
   * set customized view to left side
   *
   * @param layoutId the xml layout file id
   */
  public void setCustomizedLeftView(int layoutId) {
    View view = inflate(getContext(), layoutId, null);
    setCustomizedLeftView(view);
  }

  /**
   * set customized view to center
   *
   * @param view the view to be added to center
   */
  public void setCustomizedCenterView(View view) {
    mCenterTitleTextView.setVisibility(GONE);
    LayoutParams lp = makeLayoutParams(view);
    lp.addRule(CENTER_IN_PARENT);
    getCenterViewContainer().addView(view, lp);
  }

  /**
   * set customized view to center
   *
   * @param layoutId the xml layout file id
   */
  public void setCustomizedCenterView(int layoutId) {
    View view = inflate(getContext(), layoutId, null);
    setCustomizedCenterView(view);
  }

  /**
   * set customized view to right side
   *
   * @param view the view to be added to right side
   */
  public void setCustomizedRightView(View view) {
    LayoutParams lp = makeLayoutParams(view);
    lp.addRule(CENTER_VERTICAL);
    lp.addRule(ALIGN_PARENT_RIGHT);
    getRightViewContainer().addView(view, lp);
  }

  /**
   * set customized view to right
   *
   * @param layoutId the xml layout file id
   */
  public void setCustomizedRightView(int layoutId) {
    View view = inflate(getContext(), layoutId, null);
    setCustomizedRightView(view);
  }

  public RelativeLayout getLeftViewContainer() {
    return mLeftViewContainer;
  }

  public RelativeLayout getCenterViewContainer() {
    return mCenterViewContainer;
  }

  public RelativeLayout getRightViewContainer() {
    return mRightViewContainer;
  }

  public void setLeftOnClickListener(OnClickListener l) {
    mLeftViewContainer.setOnClickListener(l);
  }

  public void setCenterOnClickListener(OnClickListener l) {
    mCenterViewContainer.setOnClickListener(l);
  }

  public void setRightOnClickListener(OnClickListener l) {
    mRightViewContainer.setOnClickListener(l);
  }
}
