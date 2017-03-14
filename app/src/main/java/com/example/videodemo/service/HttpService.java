package com.example.videodemo.service;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by suzhudan on 2016/12/5.
 */
public interface HttpService {

    @GET("/getList/{id}")
    public Observable<List<String>> getList(@Path("id")String id);
}
