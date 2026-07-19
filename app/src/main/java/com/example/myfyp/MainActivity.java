package com.example.myfyp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Button btnCompare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCompare = findViewById(R.id.btnCompare);

        btnCompare.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            UploadActivity.class
                    );

            startActivity(intent);

        });

        BottomNavigationView bottomNav =
                findViewById(R.id.bottomNavigation);

        bottomNav.setSelectedItemId(R.id.nav_home);

        bottomNav.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.nav_home) {
                return true;
            }

            if (item.getItemId() == R.id.nav_profile) {

                startActivity(
                        new Intent(
                                MainActivity.this,
                                ProfileActivity.class
                        )
                );

                finish();

                return true;
            }

            return false;
        });
    }
}