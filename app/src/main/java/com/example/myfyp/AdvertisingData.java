package com.example.myfyp;

import java.io.Serializable;

public class AdvertisingData implements Serializable {

    private String platform;

    private double impressions;
    private double clicks;
    private double orders;
    private double spend;
    private double revenue;

    private double roi;
    private double roas;
    private double ctr;
    private double cpc;
    private double cvr;

    public AdvertisingData(
            String platform,
            double impressions,
            double clicks,
            double orders,
            double spend,
            double revenue
    ) {
        this.platform = platform;
        this.impressions = impressions;
        this.clicks = clicks;
        this.orders = orders;
        this.spend = spend;
        this.revenue = revenue;
    }

    public String getPlatform() {
        return platform;
    }

    public double getImpressions() {
        return impressions;
    }

    public double getClicks() {
        return clicks;
    }

    public double getOrders() {
        return orders;
    }

    public double getSpend() {
        return spend;
    }

    public double getRevenue() {
        return revenue;
    }

    public double getRoi() {
        return roi;
    }

    public double getRoas() {
        return roas;
    }

    public double getCtr() {
        return ctr;
    }

    public double getCpc() {
        return cpc;
    }

    public double getCvr() {
        return cvr;
    }
    public boolean isValidData() {

        // Negative values
        if (impressions < 0 || clicks < 0 || orders < 0 ||
                spend < 0 || revenue < 0) {
            return false;
        }

        // Required values
        if (impressions <= 0) {
            return false;
        }

        if (spend <= 0) {
            return false;
        }

        // Logical validation
        if (clicks > impressions) {
            return false;
        }

        return true;
    }
    public void calculateKPI() {

        // ROI & ROAS
        roi = ((revenue - spend) / spend) * 100;
        roas = revenue / spend;

        // CTR
        ctr = clicks / impressions;

        // CPC & CVR
        if (clicks > 0) {
            cpc = spend / clicks;
            cvr = orders / clicks;
        } else {
            cpc = 0;
            cvr = 0;
        }
    }
}