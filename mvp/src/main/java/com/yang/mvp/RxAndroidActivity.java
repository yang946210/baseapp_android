package com.yang.mvp;

import android.util.Log;
import android.view.View;

import com.yang.base.base.BaseActivity;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;


public class RxAndroidActivity extends BaseActivity {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_rxandroid;
    }

    @Override
    protected void findViews() {}

    @Override
    protected void init() {}

    /**
     * 简单demo(create操作符)
     * @param view
     */
    public void testCreate(View view){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("create info1");
                emitter.onNext("create info2");
                emitter.onComplete();

            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i("===Observer===","===订阅："+d.isDisposed());
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i("===Observer===","===onNext："+s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("===Observer===","===onError:"+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i("===Observer===","===onComplete");
            }
        });
    }

    /**
     * just操作符
     * @param view
     */
    public void testJust(View view){
        //just最多传10个变量
        Observable.just("just info1","just info2").subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i("===Observer===","===订阅："+d.isDisposed());
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i("===Observer===","===onNext："+s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("===Observer===","===onError:"+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i("===Observer===","===onComplete");
            }
        });
    }

    /**
     * fromArray操作符
     * @param view
     */
    public void testFromArray(View view){
        Integer array[] = {1, 2, 3, 4};

        //和just功能一样，但可以传10个以上变量
        Observable.fromArray(array).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i("===Observer===","===订阅："+d.isDisposed());
            }

            @Override
            public void onNext(@NonNull Integer  s) {
                Log.i("===Observer===","===onNext："+s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("===Observer===","===onError:"+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i("===Observer===","===onComplete");
            }
        });
    }

    /**
     * map操作符
     * @param view
     */
    public void testMap(View view){

        //和just功能一样，但可以传10个以上变量
        Observable.just("1","2").map(new Function<String, Integer>() {
            @Override
            public Integer apply(@NonNull String s) throws Exception {
                Log.i("======info lala",s+"==");
                return 1;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i("===Observer===","===订阅："+d.isDisposed());
            }

            @Override
            public void onNext(@NonNull Integer  s) {
                Log.i("===Observer===","===onNext："+s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("===Observer===","===onError:"+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i("===Observer===","===onComplete");
            }
        });
    }


    public void test(){
        Observable.just(1,2,3).map(new Function<Integer, String>() {
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                return null;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}