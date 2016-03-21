package com.cajan.tractor.dagger;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * ClassName ：
 * Description ：
 * Created : Administrator
 * Time : 2016/3/1
 * Version : 1.0
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApp {
}
