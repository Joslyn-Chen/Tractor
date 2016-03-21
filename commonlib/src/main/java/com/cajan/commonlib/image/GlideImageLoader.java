package com.cajan.commonlib.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.Transformation;
import com.cajan.commonlib.R;

/**
 * ClassName ：GlideImageLoader
 * Description : Glide图片加载
 * Created : Administrator
 * Time : 2015/10/22
 * Version : 1.0
 */
public class GlideImageLoader implements ImageLoader{

    /**
     * load : 加载
     * centerCrop : 中心截取
     * crossFade : 淡入淡出
     * placeholder : 占位图
     * error : 错误图
     * transform : 变换效果
     * asBitmap : Bitmap图（与crossFade冲突）
     * asGif : GIF图
     * thumbnail : 缩略图
     * downloadOnly : 预加载（与centerCrop冲突）
     */

//    private static GlideImageLoader mGlideImageLoader;
//    private static Context mContext;
//    private Glide glide;
    private RequestManager mRequestManager;

    public GlideImageLoader(Context context) {
//        GlideImageLoader.mContext = context;
        this.mRequestManager = Glide.with(context);
    }

    @Override
    public void load(ImageView view, String url) {
        mRequestManager.load(url).crossFade().error(R.drawable.ic_ioc).into(view);
    }

    @Override
    public void loadWithTransformation(ImageView view, String url, Transformation transformation) {
        mRequestManager.load(url).crossFade().error(R.drawable.ic_ioc).transform(transformation).into(view);
    }

//    public static GlideImageLoader getInstance(Context context) {
//        if (mGlideImageLoader == null) {
//            synchronized (GlideImageLoader.class) {
//                if (mGlideImageLoader == null) {
//                    mGlideImageLoader = new GlideImageLoader(context);
//                }
//            }
//        }
//        return mGlideImageLoader;
//    }

    /**
     * 头像加载
     *
     * @param view
     * @param url
     */
    public void onLoadSingle(ImageView view, String url) {
        mRequestManager.load(url).centerCrop().crossFade().error(R.drawable.ic_ioc).into(view);
    }

    /**
     * ListView中图片加载
     *
     * @param view
     * @param url
     */
    public void onLoadList(ImageView view, String url) {
        mRequestManager.load(url).centerCrop().crossFade().error(R.drawable.ic_ioc).into(view);
    }

    /**
     * 自定义变换
     *
     * @param view
     * @param url
     * @param transformation
     */
    public void onLoadTransformation(ImageView view, String url, Transformation transformation) {
        mRequestManager.load(url).crossFade().error(R.drawable.ic_ioc).transform(transformation).into(view);
    }

    /**
     * bitmap图
     * @param view
     * @param url
     */
    public void onLoadBitmap(ImageView view, String url) {
        mRequestManager.load(url).asBitmap().into(view);
    }

}
