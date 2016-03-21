package com.cajan.tractor.dagger;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

/**
 * ClassName ：
 * Description ：
 * Created : Administrator
 * Time : 2016/2/29
 * Version : 1.0
 */
@Qualifier //区分用途
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface IntNamed {
  int value();
}
