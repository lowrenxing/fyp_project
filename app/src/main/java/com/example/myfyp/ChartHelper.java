package com.example.myfyp;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;

import com.github.mikephil.charting.components.XAxis;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class ChartHelper {

    public static void setupCharts(
            BarChart chartPerformance,
            BarChart chartTraffic,
            BarChart chartCPC,
            AdvertisingData shopee,
            AdvertisingData lazada,
            AdvertisingData tiktok
    ) {

        // ROI + ROAS Chart
        ArrayList<String> platformLabels =
                new ArrayList<>();

        ArrayList<BarEntry> roiEntries =
                new ArrayList<>();

        ArrayList<BarEntry> roasEntries =
                new ArrayList<>();

        int index = 0;

        if(shopee != null){

            roiEntries.add(
                    new BarEntry(
                            index,
                            (float) shopee.getRoi()
                    )
            );

            roasEntries.add(
                    new BarEntry(
                            index,
                            (float) shopee.getRoas()
                    )
            );

            platformLabels.add("Shopee");
            index++;
        }

        if(lazada != null){

            roiEntries.add(
                    new BarEntry(
                            index,
                            (float) lazada.getRoi()
                    )
            );

            roasEntries.add(
                    new BarEntry(
                            index,
                            (float) lazada.getRoas()
                    )
            );

            platformLabels.add("Lazada");
            index++;
        }

        if(tiktok != null){

            roiEntries.add(
                    new BarEntry(
                            index,
                            (float) tiktok.getRoi()
                    )
            );

            roasEntries.add(
                    new BarEntry(
                            index,
                            (float) tiktok.getRoas()
                    )
            );

            platformLabels.add("TikTok");
            index++;
        }

        BarDataSet roiSet =
                new BarDataSet(
                        roiEntries,
                        "ROI"
                );

        roiSet.setColor(
                Color.parseColor("#1976D2")
        );

        BarDataSet roasSet =
                new BarDataSet(
                        roasEntries,
                        "ROAS"
                );

        roasSet.setColor(
                Color.parseColor("#43A047")
        );

        BarData performanceData =
                new BarData(
                        roiSet,
                        roasSet
                );

        performanceData.setBarWidth(0.35f);

        chartPerformance.setData(
                performanceData
        );

        chartPerformance.groupBars(
                -0.5f,
                0.2f,
                0.05f
        );

        XAxis performanceXAxis =
                chartPerformance.getXAxis();

        performanceXAxis.setValueFormatter(
                new IndexAxisValueFormatter(
                        platformLabels
                )
        );

        performanceXAxis.setPosition(
                XAxis.XAxisPosition.BOTTOM
        );

        performanceXAxis.setGranularity(1f);

        chartPerformance.getDescription()
                .setEnabled(false);

        chartPerformance.animateY(1000);

        chartPerformance.invalidate();

        // CTR + CVR Chart

        ArrayList<BarEntry> ctrEntries =
                new ArrayList<>();

        ArrayList<BarEntry> cvrEntries =
                new ArrayList<>();

        index = 0;

        if(shopee != null){

            ctrEntries.add(
                    new BarEntry(
                            index,
                            (float) shopee.getCtr()
                    )
            );

            cvrEntries.add(
                    new BarEntry(
                            index,
                            (float) shopee.getCvr()
                    )
            );

            index++;
        }

        if(lazada != null){

            ctrEntries.add(
                    new BarEntry(
                            index,
                            (float) lazada.getCtr()
                    )
            );

            cvrEntries.add(
                    new BarEntry(
                            index,
                            (float) lazada.getCvr()
                    )
            );

            index++;
        }

        if(tiktok != null){

            ctrEntries.add(
                    new BarEntry(
                            index,
                            (float) tiktok.getCtr()
                    )
            );

            cvrEntries.add(
                    new BarEntry(
                            index,
                            (float) tiktok.getCvr()
                    )
            );

            index++;
        }

        BarDataSet ctrSet =
                new BarDataSet(
                        ctrEntries,
                        "CTR"
                );

        ctrSet.setColor(
                Color.parseColor("#1976D2")
        );

        BarDataSet cvrSet =
                new BarDataSet(
                        cvrEntries,
                        "CVR"
                );

        cvrSet.setColor(
                Color.parseColor("#43A047")
        );

        BarData trafficData =
                new BarData(
                        ctrSet,
                        cvrSet
                );

        trafficData.setBarWidth(0.35f);

        chartTraffic.setData(
                trafficData
        );

        chartTraffic.groupBars(
                -0.5f,
                0.2f,
                0.05f
        );

        XAxis trafficXAxis =
                chartTraffic.getXAxis();

        trafficXAxis.setValueFormatter(
                new IndexAxisValueFormatter(
                        platformLabels
                )
        );

        trafficXAxis.setPosition(
                XAxis.XAxisPosition.BOTTOM
        );

        trafficXAxis.setGranularity(1f);

        chartTraffic.getDescription()
                .setEnabled(false);

        chartTraffic.animateY(1000);

        chartTraffic.invalidate();

        //cpc bar chart
        ArrayList<BarEntry> barEntries =
                new ArrayList<>();

        index = 0;

        if(shopee != null){
            barEntries.add(
                    new BarEntry(
                            index++,
                            (float) shopee.getCpc()
                    )
            );
        }

        if(lazada != null){
            barEntries.add(
                    new BarEntry(
                            index++,
                            (float) lazada.getCpc()
                    )
            );
        }

        if(tiktok != null){
            barEntries.add(
                    new BarEntry(
                            index++,
                            (float) tiktok.getCpc()
                    )
            );
        }

        BarDataSet dataSet =
                new BarDataSet(
                        barEntries,
                        "CPC"
                );

        dataSet.setColor(
                Color.parseColor("#1976D2")
        );

        BarData barData =
                new BarData(
                        dataSet
                );

        chartCPC.setData(
                barData
        );

        XAxis xAxis =
                chartCPC.getXAxis();

        xAxis.setValueFormatter(
                new IndexAxisValueFormatter(
                        platformLabels
                )
        );

        xAxis.setPosition(
                XAxis.XAxisPosition.BOTTOM
        );

        xAxis.setGranularity(1f);

        chartCPC.getDescription()
                .setEnabled(false);

        chartCPC.animateY(1000);

        chartCPC.invalidate();

    }

}
