package com.cajan.commonlib.image;

import android.widget.ImageView;

import com.bumptech.glide.load.Transformation;

/**
 * ClassName ：ImageLoader
 * Description : 图片加载
 * Created : Administrator
 * Time : 2015/10/22
 * Version : 1.0
 */
public interface ImageLoader {

    void load(ImageView view, String url);

    void loadWithTransformation(ImageView view, String url, Transformation transformation);
}
