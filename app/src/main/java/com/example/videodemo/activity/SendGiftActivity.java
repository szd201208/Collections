package com.example.videodemo.activity;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.videodemo.R;
import com.example.videodemo.gift.SendGift;
import com.example.videodemo.gift.SendGiftLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class SendGiftActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private TextView sendGiftText;
    private SendGiftLayout sendGiftLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_gift);
        sendGiftText = (TextView) findViewById(R.id.send_gift_text);
        RadioGroup giftRadioGroup = (RadioGroup) findViewById(R.id.gif_radiogroup);
        RadioGroup userRadioGroup = (RadioGroup) findViewById(R.id.user_radiogroup);
        giftRadioGroup.setOnCheckedChangeListener(this);
        userRadioGroup.setOnCheckedChangeListener(this);
        findViewById(R.id.btn_send).setOnClickListener(this);
        sendGiftLayout = (SendGiftLayout) findViewById(R.id.sendGiftLayout);

    }

    private List<SendGift> list = new ArrayList<>();
    private Queue queue = new LinkedBlockingQueue(Integer.MAX_VALUE);

    private SendGift lastShowSendGift = new SendGift("", "");
    private int[] colors = new int[7];

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    SendGift sendGift = list.get(0);
                    sendGiftText.setTextColor(colors[new Random().nextInt(7)]);
                    sendGiftText.setText(sendGift.getNick() + " 送 " + sendGift.getGift());
                    if (list.size() > 1 && list.get(1).getGift().equals(sendGift.getGift()) && list.get(1).getNick().equals(sendGift.getNick())) {
                        mHandler.sendEmptyMessageDelayed(1, 3000);
                    } else {
                        mHandler.sendEmptyMessageDelayed(1, 5000);
                    }
                    lastShowSendGift = list.remove(0);

                    break;
                case 1:
                    sendGiftText.setText("");
                    if (list.size() > 0) {
                        mHandler.sendEmptyMessage(0);
                    }
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                if (TextUtils.isEmpty(gift) || TextUtils.isEmpty(user)) {
                    Toast.makeText(SendGiftActivity.this, "送礼或送礼人不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    sendGiftLayout.addGift(new SendGift(user, gift));
                  /*  list.add(new SendGift(user, gift));
                    if (TextUtils.isEmpty(sendGiftText.getText().toString().trim())) {
                        mHandler.sendEmptyMessage(0);
                    }*/
                }
                break;
            default:
                break;
        }
    }

    private String gift, user;


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.gift_hua:
                gift = "花";
                break;
            case R.id.gift_cao:
                gift = "草";
                break;
            case R.id.gift_yu:
                gift = "鱼";
                break;
            case R.id.user_ming:
                user = "小明";
                break;
            case R.id.user_zhang:
                user = "小张";
                break;
            case R.id.user_hong:
                user = "小红";
                break;
        }
    }

}
