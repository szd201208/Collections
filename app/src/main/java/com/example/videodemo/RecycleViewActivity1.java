package com.example.videodemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.videodemo.view.EllipTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxiaoqi on 2016/12/22.
 */
public class RecycleViewActivity1 extends Activity {
    private RecyclerView recycleView;
    private List<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        for (int i = 0; i < 10; i++) {
            items.add("111");
            items.add("jjjjjjjshshsjksjsjsksks");
            items.add("像机械迷城游戏里的小工具一样，每一个控件都是我们手里一个有用的工具");
            items.add("像机械迷城游戏里的小工具一样，每一个控件都是我们手里一个有用的工具，"
                    +
                    "由于时间的问题可以暂时先学会其基本用法，但是只要稍有时候还是需要系统的学习一下，" +
                    "起码知道有这么个属性有这么个方法，以便对某些问题发挥关键的作用，只有充分了解了才会运用自如");
            items.add("111");
            items.add(";;;");
        }
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setAdapter(new MyAdapter());
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        LayoutInflater mLayoutInflater;

        public MyAdapter() {
            mLayoutInflater = RecycleViewActivity1.this.getLayoutInflater();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(mLayoutInflater.inflate(R.layout.view_recycle_item, parent, false));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.textView.setText(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        EllipTextView textView;
        TextView textViewMore;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (EllipTextView) itemView.findViewById(R.id.textView);
            textViewMore = (TextView) itemView.findViewById(R.id.textViewMore);
            textView.setChangedListener(new EllipTextView.OnOverSizeChangedListener() {
                @Override
                public void onChanged(boolean isOverSize) {
                    if (isOverSize) {
                        textViewMore.setVisibility(View.VISIBLE);
                    } else {
                        textViewMore.setVisibility(View.GONE);
                    }
                }
            });
        }
    }
}
