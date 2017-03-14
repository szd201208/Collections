package com.example.videodemo.utils;

import com.example.videodemo.service.HttpService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;

/**
 * Created by suzhudan on 2016/12/5.
 */
public class HttpUtils {
    private static final String TAG = HttpUtils.class.getSimpleName();

    //new retrofit
    private Retrofit retrofit;
    public static HttpService httpService;

    public void init() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        retrofit=getRetrofit(okHttpClient);
        httpService = retrofit.create(HttpService.class);


    }

    private Retrofit getRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl("baseUrl")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

//    public static <T> Subscription subscribe(Observable<HttpResult<T>> observable, Subscriber<T> subscriber) {
    public static <T> Subscription subscribe(Observable<T> observable,Subscriber<T> subscriber){
//        return observable.subscribeOn(Schedulers.newThread()).observeOn(Schedulers.io()).subscribeOn(subscriber);
        return observable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
    }

}
