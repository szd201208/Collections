package com.example.videodemo.net;


import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by suzhudan on 2017/3/7.
 */

public interface ApiService {
    @GET("live/boot")
    Observable<HttpResult<List<ADBean>>> getBootData();
}
