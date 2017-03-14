package com.example.videodemo.activity;

import android.graphics.Color;
import java.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

import com.example.videodemo.R;
import com.example.videodemo.api.ConstantTest;
import com.example.videodemo.bean.DataParse;
import com.example.videodemo.mychart.MyBottomMarkerView;
import com.example.videodemo.mychart.MyLeftMarkerView;
import com.example.videodemo.mychart.MyLineChart;
import com.example.videodemo.mychart.MyRightMarkerView;
import com.example.videodemo.mychart.MyXAxis;
import com.example.videodemo.utils.MyUtils;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//股票K线图
/*
导包：
repositories {
    maven { url "https://jitpack.io" }
}
ompile 'com.github.PhilJay:MPAndroidChart:v2.2.5'
 */
public class TChartActivity extends AppCompatActivity {

    private MyLineChart lineChart;
    private BarChart barChart;
    private YAxis axisLeft;
    private YAxis yAxisRight;
    private YAxis axisLeftBar;
    private YAxis axisRightBar;
    private DataParse mData;
    private LineDataSet d1;
    private LineDataSet d2;
    private BarDataSet barDataSet;
    private MyXAxis xAxis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tchart);
        lineChart = (MyLineChart) findViewById(R.id.lineChart);
        barChart = (BarChart) findViewById(R.id.barChart);
        initChart();
        getOffLineData();
    }

    //初始化分时图设置
    public void initChart() {
        lineChart.setScaleEnabled(false);  //是否可以伸缩
        lineChart.setDrawBorders(true);  //是否有边界
        lineChart.setBorderWidth(0.5f);  //四个边界宽度 单位float
        lineChart.setBorderColor(getResources().getColor(R.color.border_grey_color)); //四个边界的颜色
        lineChart.setDescription("");  //描述文字
        Legend lineChartLegend = lineChart.getLegend();
        lineChartLegend.setEnabled(false);   //是否显示图标实例说明

        barChart.setScaleEnabled(false);
        barChart.setDrawBorders(false);
        barChart.setDescription("");
        Legend barChartLegend = barChart.getLegend();
        barChartLegend.setEnabled(false);

        //linechart x/y轴
        xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  //x轴所在位置
//        xAxis.setLabelsToSkip(29);
        axisLeft = lineChart.getAxisLeft();
        axisLeft.setLabelCount(5, true);   //count:y轴显示数值数量  force:是否是强制性的数量
        axisLeft.setDrawLabels(true);
        //设置Y轴样式
        this.axisLeft.setValueFormatter(new YAxisValueFormatter() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public String getFormattedValue(float value, YAxis yAxis) {
                DecimalFormat decimalFormat = new DecimalFormat("#0.00");   //定义Y轴显示数字的格式
                return decimalFormat.format(value);
            }
        });
        //右边Y
        yAxisRight = lineChart.getAxisRight();
        yAxisRight.setLabelCount(5, true);
        yAxisRight.setDrawLabels(true);
        this.yAxisRight.setValueFormatter(new YAxisValueFormatter() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public String getFormattedValue(float value, YAxis yAxis) {
                DecimalFormat decimalFormat = new DecimalFormat("#0.00%");
                return decimalFormat.format(value);
            }
        });
        yAxisRight.setDrawGridLines(false);  //？
        yAxisRight.setDrawAxisLine(false);   //？
        //背景线
        xAxis.setGridColor(getResources().getColor(R.color.border_grey_color));   //背景分隔四方形竖线的颜色
        xAxis.setAxisLineColor(getResources().getColor(R.color.border_grey_color));
        axisLeft.setGridColor(getResources().getColor(R.color.border_grey_color)); //背景分隔四方形横线的颜色
//        yAxisRight.setAxisLineColor(Color.RED);  //？

        //barchart X/Yz轴
        XAxis xAxisBar = barChart.getXAxis();
        xAxisBar.setDrawLabels(false);
        xAxisBar.setDrawGridLines(false);   //竖直分隔线
        axisLeftBar = barChart.getAxisLeft();
        axisLeftBar.setDrawGridLines(false);
        axisRightBar = barChart.getAxisRight();
        axisRightBar.setDrawGridLines(false);

    }

    public void setShowLabels(SparseArray<String> labels) {
        xAxis.setXLabels(labels);
//        xAxisBar.setXLabels(labels);
    }

    private SparseArray<String> setXLabels() {
        SparseArray<String> xLabels = new SparseArray<>();
        xLabels.put(0, "09:30");
        xLabels.put(60, "10:30");
        xLabels.put(121, "11:30/13:00");
        xLabels.put(182, "14:00");
        xLabels.put(241, "15:00");
        return xLabels;
    }

    //获取测试数据
    private void getOffLineData() {
           /*方便测试，加入假数据*/
        mData = new DataParse();
        JSONObject object = null;
        try {
            object = new JSONObject(ConstantTest.MINUTESURL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mData.parseMinutes(object);
        setData(mData);
    }

    private void setMarkerView(DataParse mData) {
        MyLeftMarkerView leftMarkerView = new MyLeftMarkerView(TChartActivity.this, R.layout.mymarkerview);
        MyRightMarkerView rightMarkerView = new MyRightMarkerView(TChartActivity.this, R.layout.mymarkerview);
        MyBottomMarkerView bottomMarkerView = new MyBottomMarkerView(TChartActivity.this, R.layout.mymarkerview);
//        lineChart.setMarker(leftMarkerView, rightMarkerView,bottomMarkerView, mData);
//        barChart.setMarker(leftMarkerView, rightMarkerView,bottomMarkerView, mData);
    }

    //塞入数据
    private void setData(DataParse mData) {
        setShowLabels(setXLabels());
//        setMarkerView(mData);
//        setShowLabels(stringSparseArray);
        Log.e("###", mData.getDatas().size() + "ee");
        if (mData.getDatas().size() == 0) {
            lineChart.setNoDataText("暂无数据");
            return;
        }
        //设置y左右两轴最大最小值
        axisLeft.setAxisMinValue(mData.getMin());  //10.04-0.189
        axisLeft.setAxisMaxValue(mData.getMax());   //10.04+0.189
        yAxisRight.setAxisMinValue(mData.getPercentMin());    //-0.189/10.04
        yAxisRight.setAxisMaxValue(mData.getPercentMax());   //0.189/10.04


        axisLeftBar.setAxisMaxValue(mData.getVolmax());  //2056480
        /*单位*/
        String unit = MyUtils.getVolUnit(mData.getVolmax());
        int u = 1;
        if ("万手".equals(unit)) {
            u = 4;
        } else if ("亿手".equals(unit)) {
            u = 8;
        }
        /*次方*/
//        axisLeftBar.setValueFormatter(new VolFormatter((int) Math.pow(10, u)));
//        axisLeftBar.setShowMaxAndUnit(unit);
        axisLeftBar.setAxisMaxValue(mData.getVolmax());
        axisLeftBar.setDrawLabels(true);  //左侧数据标注
        //axisLeftBar.setAxisMinValue(0);//即使最小是不是0，也无碍
        axisLeftBar.setShowOnlyMinMax(true);  //只显示最大最小值
        axisRightBar.setAxisMaxValue(mData.getVolmax());
        //   axisRightBar.setAxisMinValue(mData.getVolmin);//即使最小是不是0，也无碍
        //axisRightBar.setShowOnlyMinMax(true);

        //基准线
        LimitLine ll = new LimitLine(0);
        ll.setLineWidth(1f);
        ll.setLineColor(Color.RED);
        ll.enableDashedLine(100f, 100f, 0f);  //?
        ll.setLineWidth(10f);
        axisLeft.addLimitLine(ll);
//        axisRightLine.setBaseValue(0);

        ArrayList<Entry> lineCJEntries = new ArrayList<>();
        ArrayList<Entry> lineJJEntries = new ArrayList<>();
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();
        Log.e("##", Integer.toString(xVals.size()));
        for (int i = 0, j = 0; i < mData.getDatas().size(); i++, j++) {
           /* //避免数据重复，skip也能正常显示
            if (mData.getDatas().get(i).time.equals("13:30")) {
                continue;
            }*/
        /*    MinutesBean t = mData.getDatas().get(j);

            if (t == null) {
                lineCJEntries.add(new Entry(Float.NaN, i));
                lineJJEntries.add(new Entry(Float.NaN, i));
                barEntries.add(new BarEntry(Float.NaN, i));
                continue;
            }
            if (!TextUtils.isEmpty(stringSparseArray.get(i)) &&
                    stringSparseArray.get(i).contains("/")) {
                i++;
            }*/
            lineCJEntries.add(new Entry(mData.getDatas().get(i).cjprice, i));  //成交价
            lineJJEntries.add(new Entry(mData.getDatas().get(i).avprice, i));   //均价
            barEntries.add(new BarEntry(mData.getDatas().get(i).cjnum, i));  //成交数量
             dateList.add(mData.getDatas().get(i).time);
        }
        d1 = new LineDataSet(lineCJEntries, "成交价");
        d2 = new LineDataSet(lineJJEntries, "均价");
        d1.setDrawValues(true);
        d2.setDrawValues(false);
        barDataSet = new BarDataSet(barEntries, "成交量");

        d1.setCircleRadius(0);
        d2.setCircleRadius(0);
        d1.setColor(Color.RED);
//        d2.setColor(getResources().getColor(R.color.minute_yellow));
        d1.setHighLightColor(Color.WHITE);  //十字架的颜色
        d2.setHighlightEnabled(false);
        d1.setDrawFilled(true);

        barDataSet.setBarSpacePercent(50); //bar空隙
        barDataSet.setHighLightColor(Color.BLUE);  //高亮颜色
        barDataSet.setHighLightAlpha(255);
        barDataSet.setDrawValues(false);  //显示数值
        barDataSet.setHighlightEnabled(false);   //是否有高亮效果
        barDataSet.setColor(Color.RED);  //设置矩形填充颜色
        List<Integer> list=new ArrayList<>();
        list.add(Color.RED);
        list.add(Color.GREEN);
        barDataSet.setColors(list);
        //谁为基准
        d1.setAxisDependency(YAxis.AxisDependency.LEFT);
        // d2.setAxisDependency(YAxis.AxisDependency.RIGHT);
        ArrayList<ILineDataSet> sets = new ArrayList<>();
        sets.add(d1);
        sets.add(d2);
        /*注老版本LineData参数可以为空，最新版本会报错，修改进入ChartData加入if判断*/
        LineData cd = new LineData(dateList, sets);
        lineChart.setData(cd);
        BarData barData = new BarData(dateList, barDataSet);
        barChart.setData(barData);
//
//        setOffset();
        lineChart.invalidate();//刷新图
        barChart.invalidate();


    }


}
