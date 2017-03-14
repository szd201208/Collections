package com.example.videodemo.gift;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.videodemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by suzhudan on 2017/3/13.
 */

public class SendGiftLayout extends RelativeLayout {

    private List<SendGift> list = new ArrayList<SendGift>();
    private int[] colors = new int[7];
    private Random random = new Random();

    private ImageView avator;
    private TextView nick;
    private TextView giftName;
    private ImageView giftImage;
    private TextView giftNumber;
    private View view;
    private ImageView background;

    public SendGiftLayout(Context context) {
        super(context);
        init(context);
    }

    public SendGiftLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        view = View.inflate(context, R.layout.layout_send_gift, null);
        this.addView(view);
        view.setVisibility(INVISIBLE);
        background = (ImageView) view.findViewById(R.id.background);
        avator = (ImageView) view.findViewById(R.id.avator);
        nick = (TextView) view.findViewById(R.id.nick);
        giftName = (TextView) view.findViewById(R.id.giftname);
        giftImage = (ImageView) view.findViewById(R.id.giftimage);
        giftNumber = (TextView) view.findViewById(R.id.giftnumber);

        colors[0] = Color.RED;
        colors[1] = Color.GRAY;
        colors[2] = Color.GREEN;
        colors[3] = Color.BLACK;
        colors[4] = Color.BLUE;
        colors[5] = Color.YELLOW;
        colors[6] = Color.DKGRAY;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_GIFT:
                    if (view.getVisibility() != VISIBLE) {
                        view.setVisibility(VISIBLE);
                    }
                    SendGift sendGift = list.get(0);
                    nick.setText(sendGift.getNick());
                    background.setBackgroundColor(colors[random.nextInt(7)]);
                    giftName.setText("送了 " + sendGift.getGift());
                    giftNumber.setText("X  " + sendGift.getCount());
                    if (list.size() > 1 && list.get(1).getNick().equals(sendGift.getNick()) && list.get(1).getGift().equals(sendGift.getGift())) {
                        mHandler.sendEmptyMessageDelayed(SHOW_GIFT, 3000);
                    } else {
                        mHandler.sendEmptyMessageDelayed(CANCEL_SHOW_GIFT, 5000);
                    }
                    list.remove(0);
                    break;
                case CANCEL_SHOW_GIFT:
                    /*if (view.getVisibility() == VISIBLE) {
                        view.setVisibility(INVISIBLE);
                    }*/
                    nick.setText("");
                    giftName.setText("");
                    giftNumber.setText("");
                    if (list.size() > 0) {
                        mHandler.sendEmptyMessage(SHOW_GIFT);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public void addGift(SendGift sendGift) {
        list.add(sendGift);
        if (TextUtils.isEmpty(nick.getText().toString().trim()) && TextUtils.isEmpty(giftName.getText().toString().trim())) {
            mHandler.sendEmptyMessage(SHOW_GIFT);
        }
    }

    private static final int SHOW_GIFT = 1;
    private static final int CANCEL_SHOW_GIFT = 2;

}
