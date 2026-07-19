package com.example.myfyp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private LinearLayout layoutSettings;
    private LinearLayout layoutHistory;

    private TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        layoutSettings = findViewById(R.id.layoutSettings);
        layoutHistory = findViewById(R.id.layoutHistory);

        txtName = findViewById(R.id.txtName);

        // Display current user name
        FirebaseUser user =
                FirebaseAuth.getInstance()
                        .getCurrentUser();

        if (user != null) {

            if (user.getDisplayName() != null &&
                    !user.getDisplayName().isEmpty()) {

                txtName.setText(
                        user.getDisplayName()
                );

            } else {

                txtName.setText("User");
            }
        }

        // =========================
        // History
        // =========================

        layoutHistory.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            ProfileActivity.this,
                            HistoryActivity.class
                    );

            startActivity(intent);

        });

        // =========================
        // Settings
        // =========================

        layoutSettings.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            ProfileActivity.this,
                            SettingsActivity.class
                    );

            startActivity(intent);

        });

        // =========================
        // Bottom Navigation
        // =========================

        BottomNavigationView bottomNav =
                findViewById(
                        R.id.bottomNavigation
                );

        bottomNav.setSelectedItemId(
                R.id.nav_profile
        );

        bottomNav.setOnItemSelectedListener(item -> {

            if (item.getItemId() ==
                    R.id.nav_home) {

                startActivity(
                        new Intent(
                                ProfileActivity.this,
                                MainActivity.class
                        )
                );

                finish();

                return true;
            }

            if (item.getItemId() ==
                    R.id.nav_profile) {

                return true;
            }

            return false;
        });
    }
}