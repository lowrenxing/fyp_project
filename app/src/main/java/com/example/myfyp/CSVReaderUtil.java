package com.example.myfyp;

import android.content.Context;
import android.net.Uri;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CSVReaderUtil {

    public static AdvertisingData readCSV(
            Context context,
            Uri fileUri,
            String platform
    ) {

        double impressions = 0;
        double clicks = 0;
        double orders = 0;
        double spend = 0;
        double revenue = 0;

        try {

            InputStream inputStream =
                    context.getContentResolver()
                            .openInputStream(fileUri);

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(inputStream)
                    );

            // Read Header
            String header = reader.readLine();

            if (header == null) {

                reader.close();
                return null;
            }

            header = header.toLowerCase();

            // Platform Validation

            if (platform.equals("TikTok")) {

                if (!header.contains("likes")
                        || !header.contains("comments")
                        || !header.contains("shares")) {

                    reader.close();
                    return null;
                }
            }

            if (platform.equals("Shopee")) {

                if (!header.contains("add_to_cart")) {

                    reader.close();
                    return null;
                }
            }

            if (platform.equals("Lazada")) {

                if (!header.contains("wishlist")) {

                    reader.close();
                    return null;
                }
            }

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] row = line.split(",");

                if (row.length < 8) {

                    reader.close();
                    return null;
                }

                try {

                    double rowImpressions =
                            Double.parseDouble(row[3].trim());

                    double rowClicks =
                            Double.parseDouble(row[4].trim());

                    double rowSpend =
                            Double.parseDouble(row[5].trim());

                    double rowOrders =
                            Double.parseDouble(row[6].trim());

                    double rowRevenue =
                            Double.parseDouble(row[7].trim());

                    impressions += rowImpressions;
                    clicks += rowClicks;
                    spend += rowSpend;
                    orders += rowOrders;
                    revenue += rowRevenue;

                }

                catch (Exception e) {

                    reader.close();
                    return null;
                }
            }

            reader.close();

            AdvertisingData data =
                    new AdvertisingData(
                            platform,
                            impressions,
                            clicks,
                            orders,
                            spend,
                            revenue
                    );

            // Business Validation
            if (!data.isValidData()) {
                return null;
            }

            data.calculateKPI();

            return data;

        }

        catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }
}