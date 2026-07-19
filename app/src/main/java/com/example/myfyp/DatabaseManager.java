package com.example.myfyp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {

    private final DatabaseHelper dbHelper;

    public DatabaseManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // =========================
    // INSERT RANKING
    // =========================

    public long insertRanking(
            String firebaseUid,
            String sessionId,
            String bestPlatform,
            String timestamp
    ) {

        SQLiteDatabase db =
                dbHelper.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put(
                "Firebase_UID",
                firebaseUid
        );

        values.put(
                "Session_ID",
                sessionId
        );

        values.put(
                "Best_Platform",
                bestPlatform
        );

        values.put(
                "Timestamp",
                timestamp
        );

        return db.insert(
                "Ranking",
                null,
                values
        );
    }

    // =========================
    // INSERT RAW DATA
    // =========================

    public long insertRawData(
            String firebaseUid,
            String sessionId,
            AdvertisingData data,
            String timestamp
    ) {

        SQLiteDatabase db =
                dbHelper.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put(
                "Firebase_UID",
                firebaseUid
        );

        values.put(
                "Session_ID",
                sessionId
        );

        values.put(
                "Platform",
                data.getPlatform()
        );

        values.put(
                "Impressions",
                data.getImpressions()
        );

        values.put(
                "Clicks",
                data.getClicks()
        );

        values.put(
                "Orders",
                data.getOrders()
        );

        values.put(
                "Spend",
                data.getSpend()
        );

        values.put(
                "Revenue",
                data.getRevenue()
        );

        values.put(
                "ROI",
                data.getRoi()
        );

        values.put(
                "ROAS",
                data.getRoas()
        );

        values.put(
                "CTR",
                data.getCtr()
        );

        values.put(
                "CPC",
                data.getCpc()
        );

        values.put(
                "CVR",
                data.getCvr()
        );

        values.put(
                "Timestamp",
                timestamp
        );

        return db.insert(
                "RawData",
                null,
                values
        );
    }

    // =========================
    // GET HISTORY
    // =========================

    public Cursor getHistory(
            String firebaseUid
    ) {

        SQLiteDatabase db =
                dbHelper.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM Ranking " +
                        "WHERE Firebase_UID=? " +
                        "ORDER BY Timestamp DESC",
                new String[]{
                        firebaseUid
                }
        );
    }

    // =========================
    // GET SESSION DATA
    // =========================

    public Cursor getSessionData(
            String sessionId
    ) {

        SQLiteDatabase db =
                dbHelper.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM RawData " +
                        "WHERE Session_ID=?",
                new String[]{
                        sessionId
                }
        );
    }
}