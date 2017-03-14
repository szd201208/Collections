package com.example.videodemo.gift;

/**
 * Created by suzhudan on 2017/3/13.
 */

public class SendGift {
    SendGift() {
    }

    public SendGift(String nick, String gift) {
        this.nick = nick;
        this.gift = gift;
    }

    private String nick = "";
    private String gift = "";
    private int count=1;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
