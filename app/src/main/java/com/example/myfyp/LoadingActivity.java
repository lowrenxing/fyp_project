package com.example.myfyp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class LoadingActivity extends AppCompatActivity {

    private AdvertisingData shopeeData;
    private AdvertisingData lazadaData;
    private AdvertisingData tiktokData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        shopeeData =
                (AdvertisingData) getIntent()
                        .getSerializableExtra("shopeeData");

        lazadaData =
                (AdvertisingData) getIntent()
                        .getSerializableExtra("lazadaData");

        tiktokData =
                (AdvertisingData) getIntent()
                        .getSerializableExtra("tiktokData");

        new Handler().postDelayed(() -> {

            DatabaseManager dbManager =
                    new DatabaseManager(this);

            FirebaseUser user =
                    FirebaseAuth.getInstance()
                            .getCurrentUser();

            if(user == null){

                finish();
                return;
            }

            String firebaseUid =
                    user.getUid();

            String sessionId =
                    "H" + System.currentTimeMillis();

            String timestamp =
                    new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss",
                            Locale.getDefault()
                    ).format(new Date());

            // Save Raw Data

            if (shopeeData != null) {
                dbManager.insertRawData(
                        firebaseUid,
                        sessionId,
                        shopeeData,
                        timestamp
                );
            }

            if (lazadaData != null) {
                dbManager.insertRawData(
                        firebaseUid,
                        sessionId,
                        lazadaData,
                        timestamp
                );
            }

            if (tiktokData != null) {
                dbManager.insertRawData(
                        firebaseUid,
                        sessionId,
                        tiktokData,
                        timestamp
                );
            }

            // Calculate Ranking

            List<AdvertisingData> platforms =
                    new ArrayList<>();

            if (shopeeData != null)
                platforms.add(shopeeData);

            if (lazadaData != null)
                platforms.add(lazadaData);

            if (tiktokData != null)
                platforms.add(tiktokData);

            String bestPlatform = "Unknown";

            if (!platforms.isEmpty()) {

                Map<String, Double> scores =
                        SAWCalculator.calculateScores(
                                platforms
                        );

                double highestScore = -1;

                for (Map.Entry<String, Double> entry :
                        scores.entrySet()) {

                    if (entry.getValue() >
                            highestScore) {

                        highestScore =
                                entry.getValue();

                        bestPlatform =
                                entry.getKey();
                    }
                }
            }

            // Save Ranking

            dbManager.insertRanking(
                    firebaseUid,
                    sessionId,
                    bestPlatform,
                    timestamp
            );

            Intent intent =
                    new Intent(
                            LoadingActivity.this,
                            ResultActivity.class
                    );

            intent.putExtra(
                    "shopeeData",
                    shopeeData
            );

            intent.putExtra(
                    "lazadaData",
                    lazadaData
            );

            intent.putExtra(
                    "tiktokData",
                    tiktokData
            );

            startActivity(intent);

            finish();

        }, 3000);
    }
}