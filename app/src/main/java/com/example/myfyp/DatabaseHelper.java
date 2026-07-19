package com.example.myfyp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dss_database.db";

    // version +1
    private static final int DATABASE_VERSION = 3;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // RawData Table
        db.execSQL(
                "CREATE TABLE RawData (" +
                        "Raw_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "Firebase_UID TEXT," +
                        "Session_ID TEXT," +
                        "Platform TEXT," +
                        "Impressions INTEGER," +
                        "Clicks INTEGER," +
                        "Orders INTEGER," +
                        "Spend REAL," +
                        "Revenue REAL," +
                        "ROI REAL," +
                        "ROAS REAL," +
                        "CTR REAL," +
                        "CPC REAL," +
                        "CVR REAL," +
                        "Timestamp TEXT" +
                        ")"
        );

        // Ranking Table
        db.execSQL(
                "CREATE TABLE Ranking (" +
                        "Ranking_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "Firebase_UID TEXT," +
                        "Session_ID TEXT," +
                        "Best_Platform TEXT," +
                        "Timestamp TEXT" +
                        ")"
        );
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion
    ) {

        db.execSQL(
                "DROP TABLE IF EXISTS User"
        );

        db.execSQL(
                "DROP TABLE IF EXISTS RawData"
        );

        db.execSQL(
                "DROP TABLE IF EXISTS Ranking"
        );

        onCreate(db);
    }
}