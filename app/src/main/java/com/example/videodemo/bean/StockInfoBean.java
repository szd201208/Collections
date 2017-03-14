package com.example.videodemo.bean;

public class StockInfoBean {
    private String code; // 股票唯一标记：exchange + symbol
    private String symbol; // 股票代码
    private String name; // 名称
    private String exchange; // SH, SZ
    private int type; // 类型：0=普通股票，1=指数

    private double high; // 最高价
    private double low; // 最低价
    private double open; // 开盘价
    private double yestclose; // 昨收价
    private double price; // 当前价
    private double updown; // 涨跌额
    private double percent; // 涨跌幅
    private double volume; // 成交量
    private double turnover; // 成交额
    private double per; // 市盈率
    private double pbr; // 市净率
    private double tor; // 换手率
    private double capital; // 市值
    private int upCount; // 上涨股票数（只针对指数有效）
    private int downCount; // 下跌股票数（只针对指数有效）

    // 买盘
    private double bid1;
    private double bid2;
    private double bid3;
    private double bid4;
    private double bid5;
    private long bidvol1;
    private long bidvol2;
    private long bidvol3;
    private long bidvol4;
    private long bidvol5;

    // 卖盘
    private double sell1;
    private double sell2;
    private double sell3;
    private double sell4;
    private double sell5;
    private long sellvol1;
    private long sellvol2;
    private long sellvol3;
    private long sellvol4;
    private long sellvol5;

    private int status; // 状态：交易中、收盘
    private String time; // 行情时间（格式：yyyy-MM-dd HH:mm:ss）
    private String update; // 当前记录更新时间（格式：yyyy-MM-dd HH:mm:ss）

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getYestclose() {
        return yestclose;
    }

    public void setYestclose(double yestclose) {
        this.yestclose = yestclose;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getUpdown() {
        return updown;
    }

    public void setUpdown(double updown) {
        this.updown = updown;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public double getPer() {
        return per;
    }

    public void setPer(double per) {
        this.per = per;
    }

    public double getPbr() {
        return pbr;
    }

    public void setPbr(double pbr) {
        this.pbr = pbr;
    }

    public double getTor() {
        return tor;
    }

    public void setTor(double tor) {
        this.tor = tor;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public int getUpCount() {
        return upCount;
    }

    public void setUpCount(int upCount) {
        this.upCount = upCount;
    }

    public int getDownCount() {
        return downCount;
    }

    public void setDownCount(int downCount) {
        this.downCount = downCount;
    }

    public double getBid1() {
        return bid1;
    }

    public void setBid1(double bid1) {
        this.bid1 = bid1;
    }

    public double getBid2() {
        return bid2;
    }

    public void setBid2(double bid2) {
        this.bid2 = bid2;
    }

    public double getBid3() {
        return bid3;
    }

    public void setBid3(double bid3) {
        this.bid3 = bid3;
    }

    public double getBid4() {
        return bid4;
    }

    public void setBid4(double bid4) {
        this.bid4 = bid4;
    }

    public double getBid5() {
        return bid5;
    }

    public void setBid5(double bid5) {
        this.bid5 = bid5;
    }

    public long getBidvol1() {
        return bidvol1;
    }

    public void setBidvol1(long bidvol1) {
        this.bidvol1 = bidvol1;
    }

    public long getBidvol2() {
        return bidvol2;
    }

    public void setBidvol2(long bidvol2) {
        this.bidvol2 = bidvol2;
    }

    public long getBidvol3() {
        return bidvol3;
    }

    public void setBidvol3(long bidvol3) {
        this.bidvol3 = bidvol3;
    }

    public long getBidvol4() {
        return bidvol4;
    }

    public void setBidvol4(long bidvol4) {
        this.bidvol4 = bidvol4;
    }

    public long getBidvol5() {
        return bidvol5;
    }

    public void setBidvol5(long bidvol5) {
        this.bidvol5 = bidvol5;
    }

    public double getSell1() {
        return sell1;
    }

    public void setSell1(double sell1) {
        this.sell1 = sell1;
    }

    public double getSell2() {
        return sell2;
    }

    public void setSell2(double sell2) {
        this.sell2 = sell2;
    }

    public double getSell3() {
        return sell3;
    }

    public void setSell3(double sell3) {
        this.sell3 = sell3;
    }

    public double getSell4() {
        return sell4;
    }

    public void setSell4(double sell4) {
        this.sell4 = sell4;
    }

    public double getSell5() {
        return sell5;
    }

    public void setSell5(double sell5) {
        this.sell5 = sell5;
    }

    public long getSellvol1() {
        return sellvol1;
    }

    public void setSellvol1(long sellvol1) {
        this.sellvol1 = sellvol1;
    }

    public long getSellvol2() {
        return sellvol2;
    }

    public void setSellvol2(long sellvol2) {
        this.sellvol2 = sellvol2;
    }

    public long getSellvol3() {
        return sellvol3;
    }

    public void setSellvol3(long sellvol3) {
        this.sellvol3 = sellvol3;
    }

    public long getSellvol4() {
        return sellvol4;
    }

    public void setSellvol4(long sellvol4) {
        this.sellvol4 = sellvol4;
    }

    public long getSellvol5() {
        return sellvol5;
    }

    public void setSellvol5(long sellvol5) {
        this.sellvol5 = sellvol5;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    @Override
    public String toString() {
        return "StockInfoBean{" +
                "code='" + code + '\'' +
                ", symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", exchange='" + exchange + '\'' +
                ", type=" + type +
                ", high=" + high +
                ", low=" + low +
                ", open=" + open +
                ", yestclose=" + yestclose +
                ", price=" + price +
                ", updown=" + updown +
                ", percent=" + percent +
                ", volume=" + volume +
                ", turnover=" + turnover +
                ", per=" + per +
                ", pbr=" + pbr +
                ", tor=" + tor +
                ", capital=" + capital +
                ", upCount=" + upCount +
                ", downCount=" + downCount +
                ", bid1=" + bid1 +
                ", bid2=" + bid2 +
                ", bid3=" + bid3 +
                ", bid4=" + bid4 +
                ", bid5=" + bid5 +
                ", bidvol1=" + bidvol1 +
                ", bidvol2=" + bidvol2 +
                ", bidvol3=" + bidvol3 +
                ", bidvol4=" + bidvol4 +
                ", bidvol5=" + bidvol5 +
                ", sell1=" + sell1 +
                ", sell2=" + sell2 +
                ", sell3=" + sell3 +
                ", sell4=" + sell4 +
                ", sell5=" + sell5 +
                ", sellvol1=" + sellvol1 +
                ", sellvol2=" + sellvol2 +
                ", sellvol3=" + sellvol3 +
                ", sellvol4=" + sellvol4 +
                ", sellvol5=" + sellvol5 +
                ", status=" + status +
                ", time='" + time + '\'' +
                ", update='" + update + '\'' +
                '}';
    }
}
