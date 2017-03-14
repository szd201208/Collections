package com.example.videodemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.videodemo.R;
import com.example.videodemo.net.ADBean;
import com.example.videodemo.net.ApiService;
import com.example.videodemo.net.HttpUtils;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

public class NetActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = NetActivity.class.getSimpleName();

    private TextView resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        resultTv = (TextView) findViewById(R.id.result);
        findViewById(R.id.okhttp_btn).setOnClickListener(this);
        findViewById(R.id.retrofit_btn).setOnClickListener(this);
        findViewById(R.id.mix_btn).setOnClickListener(this);
        findViewById(R.id.get_noparams).setOnClickListener(this);
        findViewById(R.id.get_hasparams).setOnClickListener(this);
        findViewById(R.id.post_noparams).setOnClickListener(this);
        findViewById(R.id.post_hasparams).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.okhttp_btn:
                getOkhttpData();
                break;
            case R.id.retrofit_btn:
                getRetrofitData();
                break;
            case R.id.mix_btn:
                break;
            case R.id.get_noparams:
                getNoParams();
                break;
            case R.id.get_hasparams:
                break;
            case R.id.post_noparams:
                break;
            case R.id.post_hasparams:
                break;
            default:
                break;
        }
    }

    public void getNoParams() {  //Get无参  /live/boot
        HttpUtils.getHttpResult(HttpUtils.getApiService().getBootData(), new Subscriber<List<ADBean>>() {
            @Override
            public void onCompleted() {
//                Toast.makeText(NetActivity.this, "onCompleted", Toast.LENGTH_SHORT).show();
                if (!isUnsubscribed()) {  //取消订阅，避免内存泄漏
                    unsubscribe();
                }
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(NetActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(List<ADBean> o) {
                resultTv.setText(o.toString());
//                Toast.makeText(NetActivity.this, o.getData().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getRetrofitData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("/")
                .addConverterFactory(GsonConverterFactory.create())  //将ResponseBody转换为我们想要的类型
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())  //与rxjava结合
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        /*apiService.getBootData().subscribeOn(Schedulers.io()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        }); */ //Observable

       /*
       Call:
       call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });*/
    }

    public void getOkhttpData() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        //http://www.ssyer.com/uploads/org_2017010593503_775.jpg
        //http://www.baidu.com
        //Get 异步请求
//        Request request=new Request.Builder().url("http://www.ssyer.com/uploads/org_2017010593503_775.jpg").get().build();
//        FormBody formBody = new FormBody.Builder().add("name", "asd").build();  //Post 异步请求
     /*   RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"), new File("1.jpg"));  //上传文件
        Request request = new Request.Builder().url("http://www.baidu.com").post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "response:" + response.body().string());
              *//*
              有InpuitStream格式数据，可用于大文件下载
              InputStream inputStream=response.body().byteStream();
                File file=new File(Environment.getExternalStorageDirectory()+"大狮子.jpg");
                FileOutputStream outputStream=new FileOutputStream(file);
                byte[] bytes=new byte[2048];
                int len=0;
                while((len=inputStream.read(bytes))!=-1){
                    outputStream.write(bytes,0,len);
                }
                outputStream.flush();
                Log.e(TAG,"图片下载完成");*//*
            }
        });*/
    }

}
