package com.example.myfyp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SAWCalculator {

    public static Map<String, Double> calculateScores(
            List<AdvertisingData> platforms
    ) {

        Map<String, Double> scores =
                new HashMap<>();

        double maxROI = 0;
        double maxROAS = 0;
        double maxCTR = 0;
        double maxCVR = 0;

        double minCPC = Double.MAX_VALUE;

        // Find max and min values

        for (AdvertisingData data : platforms) {

            if (data.getRoi() > maxROI) {
                maxROI = data.getRoi();
            }

            if (data.getRoas() > maxROAS) {
                maxROAS = data.getRoas();
            }

            if (data.getCtr() > maxCTR) {
                maxCTR = data.getCtr();
            }

            if (data.getCvr() > maxCVR) {
                maxCVR = data.getCvr();
            }

            if (data.getCpc() < minCPC) {
                minCPC = data.getCpc();
            }
        }

        // Calculate SAW Score

        for (AdvertisingData data : platforms) {

            double roiNorm =
                    data.getRoi() / maxROI;

            double roasNorm =
                    data.getRoas() / maxROAS;

            double ctrNorm =
                    data.getCtr() / maxCTR;

            double cvrNorm =
                    data.getCvr() / maxCVR;

            double cpcNorm =
                    minCPC / data.getCpc();

            double score =
                    (roiNorm * 0.40) + (roasNorm * 0.30) + (ctrNorm * 0.15) + (cvrNorm * 0.10) + (cpcNorm * 0.05);
            scores.put(
                    data.getPlatform(),
                    score
            );
        }

        return scores;
    }
}