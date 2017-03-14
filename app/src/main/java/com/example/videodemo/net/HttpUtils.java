package com.example.videodemo.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by suzhudan on 2017/3/8.
 */

public class HttpUtils {
    private static final String BASE_URL = "https://api.icaibei.net/";
    private static ApiService apiService;
    private static HttpUtils mInstance = new HttpUtils();

    HttpUtils() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(3000, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        apiService = retrofit.create(ApiService.class);
    }

    public static ApiService getApiService() {
        return mInstance.apiService;
    }

    public static <T> void getHttpResult(Observable<HttpResult<T>> result, Subscriber subscriber) {
        result.compose(transformer).subscribe(subscriber);
    }


    private static Observable.Transformer transformer = new Observable.Transformer() {
        @Override
        public Object call(Object o) {
            return ((Observable) o).map(func1).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()); //线程切换
        }
    };

    private static Func1 func1 = new Func1() {
        @Override
        public Object call(Object o) {
            HttpResult httpResult = (HttpResult) o;
            if (httpResult.getStatus() > 900 && httpResult.getStatus() < 999) {
                throw new RuntimeException("httpResult.getStatus()=" + httpResult.getStatus());
            }
            return httpResult.getData();
        }
    };

}
