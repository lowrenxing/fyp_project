package com.example.myfyp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsActivity extends AppCompatActivity {

    private ImageButton btnBack;

    private CardView cardPassword;
    private CardView cardLogout;

    private TextView txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btnBack = findViewById(R.id.btnBack);

        cardPassword = findViewById(R.id.cardPassword);
        cardLogout = findViewById(R.id.cardLogout);

        txtEmail = findViewById(R.id.txtEmail);

        // Display current user email
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            txtEmail.setText(user.getEmail());
        }

        // Back Button
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());

        // Reset Password
        cardPassword.setOnClickListener(v -> {

            Intent intent = new Intent(
                    SettingsActivity.this,
                    ResetPasswordActivity.class
            );

            startActivity(intent);

        });

        // Logout
        cardLogout.setOnClickListener(v -> {

            FirebaseAuth.getInstance().signOut();

            Intent intent = new Intent(
                    SettingsActivity.this,
                    LoginActivity.class
            );

            intent.addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_CLEAR_TASK
            );

            startActivity(intent);

            finish();
        });
    }
}