package com.cajan.tractor;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.cajan.tractor.base.BaseActivity;

public class MainActivity extends BaseActivity {

  //@Bind(R.id.main_text) TextView mainText;
  //Subscription subscription;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setToolbar();
    ButterKnife.bind(this);
  }

  private void setToolbar() {
    setTitle("Tractor");

    mToolbar.inflateMenu(R.menu.menu_main);
    mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
          case R.id.action_one:
            Toast.makeText(MainActivity.this, "11111", Toast.LENGTH_SHORT).show();
            break;
          case R.id.action_two:
            Toast.makeText(MainActivity.this, "2222222222", Toast.LENGTH_SHORT).show();
            break;
          default:
            break;
        }
        return true;
      }
    });

    if (getSupportActionBar() != null) {
      //  取消返回键
      getSupportActionBar().setDisplayShowHomeEnabled(false);
      getSupportActionBar().hide();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  /**
   * RxJava
   */
  /*private void textRxJava() {
    Observable.create(new Observable.OnSubscribe<String>() {
      @Override public void call(Subscriber<? super String> subscriber) {

      }
    });
    Observable.defer(new Func0<Observable<Object>>() {
      @Override public Observable<Object> call() {
        return null;
      }
    });
    Observable.empty();
    Observable.never();
    Observable.error(new Throwable("TestError"));
    String[] id = { "1", "2", "3" };
    Observable.from(id);
    Observable.interval(10000, TimeUnit.MILLISECONDS);
    Observable.interval(5000, 2000, TimeUnit.MILLISECONDS);
    Observable.just("Just");
    Observable.range(1, 5);
    Observable.empty().repeat();
    Observable.timer(5000, TimeUnit.MILLISECONDS);

    Observable<List<String>> observable1 = Observable.just("123").buffer(1);

    Single.create(new Single.OnSubscribe<String>() {
      @Override public void call(SingleSubscriber<? super String> singleSubscriber) {
        singleSubscriber.onSuccess("s");
      }
    }).subscribe(new Subscriber<String>() {
      @Override public void onCompleted() {

      }

      @Override public void onError(Throwable e) {

      }

      @Override public void onNext(String s) {

      }
    });
    String[] ids = { "1", "2", "3" };
    Observable.from(ids).take(2).subscribe(new Subscriber<String>() {
      @Override public void onCompleted() {
        LogUtil.e("onCompleted");
      }

      @Override public void onError(Throwable e) {
        LogUtil.e(e);
      }

      @Override public void onNext(String s) {
        LogUtil.e(s);
      }
    });
    Observable.OnSubscribe<String> observable = new Observable.OnSubscribe<String>() {
      @Override public void call(Subscriber<? super String> subscriber) {
        subscriber.onNext("1");
        subscriber.onCompleted();
      }
    };`
    Subscriber<String> subscriber = new Subscriber<String>() {
      @Override public void onStart() {

      }

      @Override public void onCompleted() {
        LogUtil.e("onCompleted");
      }

      @Override public void onError(Throwable e) {
        LogUtil.e(e);
      }

      @Override public void onNext(String s) {
        LogUtil.e(s);
      }
    };
    Observable<String> observable0 = Observable.create(observable);
    observable0.subscribeOn(Schedulers.immediate());
    observable0.observeOn(AndroidSchedulers.mainThread());
    observable0.subscribe(subscriber);
    observable0.subscribe(subscriber);

    Scheduler.Worker worker = Schedulers.newThread().createWorker();
    worker.schedule(new Action0() {
      @Override public void call() {

      }
    }, 1000, TimeUnit.MILLISECONDS);
    worker.schedule(new Action0() {
      @Override public void call() {

      }
    });
    //worker.unsubscribe();

    Observable.create(new Observable.OnSubscribe<String>() {
      @Override public void call(Subscriber<? super String> s) {

      }
    })
        .map(new Func1<String, Integer>() {
          @Override public Integer call(String s) {
            return Integer.parseInt(s);
          }
        })
        .filter(new Func1<Integer, Boolean>() {
          @Override public Boolean call(Integer i) {
            return i < 5;
          }
        })
        .subscribeOn(Schedulers.immediate())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Integer>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {

          }

          @Override public void onNext(Integer integer) {

          }
        });
  }*/
  @Override protected void onDestroy() {
    //subscription.unsubscribe();
    super.onDestroy();
  }
}
