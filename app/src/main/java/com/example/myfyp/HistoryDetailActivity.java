package com.example.myfyp;

import android.database.Cursor;
import android.graphics.Typeface;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HistoryDetailActivity extends AppCompatActivity {

    private TextView txtSession;
    private TextView txtDate;
    private TextView txtRanking;

    private TableLayout tableKPI;

    private BarChart chartPerformance;
    private BarChart chartTraffic;
    private BarChart chartCPC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtSession = findViewById(R.id.txtSession);
        txtDate = findViewById(R.id.txtDate);
        txtRanking = findViewById(R.id.txtRanking);

        tableKPI = findViewById(R.id.tableKPI);

        chartPerformance = findViewById(R.id.chartPerformance);
        chartTraffic = findViewById(R.id.chartTraffic);

        chartCPC = findViewById(R.id.chartCPC);

        String sessionId =
                getIntent().getStringExtra(
                        "sessionId"
                );

        DatabaseManager dbManager =
                new DatabaseManager(this);

        Cursor cursor =
                dbManager.getSessionData(
                        sessionId
                );

        AdvertisingData shopee = null;
        AdvertisingData lazada = null;
        AdvertisingData tiktok = null;

        String timestamp = "";

        while (cursor.moveToNext()) {

            String platform =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    "Platform"
                            )
                    );

            AdvertisingData data =
                    new AdvertisingData(
                            platform,

                            cursor.getDouble(
                                    cursor.getColumnIndexOrThrow(
                                            "Impressions"
                                    )
                            ),

                            cursor.getDouble(
                                    cursor.getColumnIndexOrThrow(
                                            "Clicks"
                                    )
                            ),

                            cursor.getDouble(
                                    cursor.getColumnIndexOrThrow(
                                            "Orders"
                                    )
                            ),

                            cursor.getDouble(
                                    cursor.getColumnIndexOrThrow(
                                            "Spend"
                                    )
                            ),

                            cursor.getDouble(
                                    cursor.getColumnIndexOrThrow(
                                            "Revenue"
                                    )
                            )
                    );

            timestamp =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    "Timestamp"
                            )
                    );

            data.calculateKPI();

            switch (platform) {

                case "Shopee":
                    shopee = data;
                    break;

                case "Lazada":
                    lazada = data;
                    break;

                case "TikTok":
                    tiktok = data;
                    break;
            }
        }

        cursor.close();

        List<AdvertisingData> platforms =
                new ArrayList<>();

        if (shopee != null) {
            platforms.add(shopee);
        }

        if (lazada != null) {
            platforms.add(lazada);
        }

        if (tiktok != null) {
            platforms.add(tiktok);
        }

        if (platforms.size() < 2) {

            finish();
            return;
        }

        String displayId = sessionId;

        if (displayId.length() > 12) {

            displayId =
                    displayId.substring(
                            0,
                            12
                    );
        }

        txtSession.setText(
                "Session ID : " +
                        displayId
        );

        txtDate.setText(
                timestamp
        );

        Map<String, Double> scores =
                SAWCalculator.calculateScores(
                        platforms
                );


        double shopeeScore =
                scores.getOrDefault(
                        "Shopee",
                        0.0
                );

        double lazadaScore =
                scores.getOrDefault(
                        "Lazada",
                        0.0
                );

        double tiktokScore =
                scores.getOrDefault(
                        "TikTok",
                        0.0
                );


        //table
        TableRow header =
                new TableRow(this);

        String[] titles = {
                "Platform",
                "ROI",
                "ROAS",
                "CTR",
                "CVR",
                "CPC",
                "SAW"
        };

        for (String s : titles) {

            TextView tv =
                    new TextView(this);

            tv.setBackgroundResource(
                    R.drawable.table_cell_border
            );

            tv.setText(s);

            tv.setTypeface(
                    null,
                    Typeface.BOLD
            );

            tv.setPadding(
                    15,
                    15,
                    15,
                    15
            );

            header.addView(tv);
        }

        tableKPI.addView(header);

        //shopee row
        if (shopee != null) {
            addRow(
                    "Shopee",
                    shopee.getRoi(),
                    shopee.getRoas(),
                    shopee.getCtr(),
                    shopee.getCvr(),
                    shopee.getCpc(),
                    shopeeScore
            );
        }

        if (lazada != null) {
            addRow(
                    "Lazada",
                    lazada.getRoi(),
                    lazada.getRoas(),
                    lazada.getCtr(),
                    lazada.getCvr(),
                    lazada.getCpc(),
                    lazadaScore
            );
        }

        if (tiktok != null) {
            addRow(
                    "TikTok",
                    tiktok.getRoi(),
                    tiktok.getRoas(),
                    tiktok.getCtr(),
                    tiktok.getCvr(),
                    tiktok.getCpc(),
                    tiktokScore
            );
        }

        List<Map.Entry<String, Double>> ranking =
                new ArrayList<>(scores.entrySet());

        Collections.sort(
                ranking,
                (a, b) -> Double.compare(
                        b.getValue(),
                        a.getValue()
                )
        );

        StringBuilder rankingText =
                new StringBuilder();

        for (int i = 0; i < ranking.size(); i++) {

            rankingText.append(i + 1)
                    .append(". ")
                    .append(ranking.get(i).getKey())
                    .append(" (")
                    .append(String.format(
                            Locale.getDefault(),
                            "%.4f",
                            ranking.get(i).getValue()
                    ))
                    .append(")");

            if (i < ranking.size() - 1) {
                rankingText.append("\n");
            }
        }

        txtRanking.setText(
                rankingText.toString()
        );

        ChartHelper.setupCharts(
                chartPerformance,
                chartTraffic,
                chartCPC,
                shopee,
                lazada,
                tiktok
        );
    }

    //add row
    private void addRow(
            String platform,
            double roi,
            double roas,
            double ctr,
            double cvr,
            double cpc,
            double saw){

        TableRow row =
                new TableRow(this);

        String[] data = {
                platform,
                String.format("%.2f%%", roi),
                String.format("%.2f", roas),
                String.format("%.3f", ctr),
                String.format("%.3f", cvr),
                String.format("RM %.2f", cpc),
                String.format("%.3f", saw)
        };

        for(String s : data){

            TextView tv =
                    new TextView(this);

            tv.setBackgroundResource(
                    R.drawable.table_cell_border
            );

            tv.setText(s);

            tv.setPadding(
                    15,
                    15,
                    15,
                    15
            );

            row.addView(tv);
        }

        tableKPI.addView(row);
    }
}
