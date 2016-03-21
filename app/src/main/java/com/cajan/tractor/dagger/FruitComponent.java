package com.cajan.tractor.dagger;

import dagger.Component;
import javax.inject.Singleton;

/**
 * ClassName ：FruitComponent
 * Description ：
 * Created : Administrator
 * Time : 2016/2/29
 * Version : 1.0
 */
@Singleton
@Component(modules = {FruitModule.class})
public interface FruitComponent {

  void inject(Container container);
}
