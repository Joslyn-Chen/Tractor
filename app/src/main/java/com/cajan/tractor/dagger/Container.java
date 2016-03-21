package com.cajan.tractor.dagger;

import javax.inject.Inject;

/**
 * ClassName ：Container
 * Description ：
 * Created : Administrator
 * Time : 2016/2/29
 * Version : 1.0
 */
public class Container {

  @Inject
  Fruit fruit;

  public void init(){
    DaggerFruitComponent.builder().fruitModule(new FruitModule()).build();
    DaggerFruitComponent.create().inject(this);
  }
}
