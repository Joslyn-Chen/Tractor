package com.cajan.tractor.dagger;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * ClassName ：
 * Description ：
 * Created : Administrator
 * Time : 2016/2/29
 * Version : 1.0
 */
@Module
public class FruitModule {

  @Singleton
  @Provides
  Fruit provideFruit(){
    return new Apple(1,1);
  }
}
