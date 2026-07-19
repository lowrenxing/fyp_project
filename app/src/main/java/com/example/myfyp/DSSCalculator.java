package com.example.myfyp;

public class DSSCalculator {

    public static double calculateScore(
            AdvertisingData data
    ) {

        return
                data.getRoi() * 0.40 + data.getRoas() * 0.30 + data.getCtr() * 0.15 + data.getCvr() * 0.10 - data.getCpc() * 0.05;
    }
}