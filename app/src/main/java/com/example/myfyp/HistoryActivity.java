package com.example.myfyp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerHistory;

    private ImageButton btnBack;
    private ArrayList<HistoryItem> historyList;

    private HistoryAdapter adapter;

    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Back Button
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());

        recyclerHistory =
                findViewById(R.id.recyclerHistory);

        recyclerHistory.setLayoutManager(
                new LinearLayoutManager(this)
        );

        historyList =
                new ArrayList<>();

        adapter =
                new HistoryAdapter(
                        historyList,
                        item -> {

                            Intent intent =
                                    new Intent(
                                            HistoryActivity.this,
                                            HistoryDetailActivity.class
                                    );

                            intent.putExtra(
                                    "sessionId",
                                    item.getSessionId()
                            );

                            startActivity(intent);
                        }
                );

        recyclerHistory.setAdapter(adapter);

        dbManager =
                new DatabaseManager(this);

        loadHistory();
    }

    private void loadHistory() {

        historyList.clear();

        FirebaseUser user =
                FirebaseAuth.getInstance()
                        .getCurrentUser();

        if(user == null){

            return;
        }

        String firebaseUid =
                user.getUid();

        Cursor cursor =
                dbManager.getHistory(
                        firebaseUid
                );

        while (cursor.moveToNext()) {

            String sessionId =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    "Session_ID"
                            )
                    );

            String bestPlatform =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    "Best_Platform"
                            )
                    );

            String timestamp =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    "Timestamp"
                            )
                    );

            historyList.add(
                    new HistoryItem(
                            sessionId,
                            bestPlatform,
                            timestamp
                    )
            );
        }

        cursor.close();

        adapter.notifyDataSetChanged();
    }
}