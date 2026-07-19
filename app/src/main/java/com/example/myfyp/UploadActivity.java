package com.example.myfyp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class UploadActivity extends AppCompatActivity {

    private static final int PICK_SHOPEE = 1;
    private static final int PICK_LAZADA = 2;
    private static final int PICK_TIKTOK = 3;

    private ImageButton btnBack;

    private CardView cardShopee;
    private CardView cardLazada;
    private CardView cardTiktok;

    private ImageView imgShopee;
    private ImageView imgLazada;
    private ImageView imgTiktok;

    private TextView txtShopee;
    private TextView txtLazada;
    private TextView txtTiktok;

    private Button btnCompareNow;

    private boolean shopeeUploaded = false;
    private boolean lazadaUploaded = false;
    private boolean tiktokUploaded = false;

    private AdvertisingData shopeeData;
    private AdvertisingData lazadaData;
    private AdvertisingData tiktokData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        btnBack = findViewById(R.id.btnBack);

        cardShopee = findViewById(R.id.cardShopee);
        cardLazada = findViewById(R.id.cardLazada);
        cardTiktok = findViewById(R.id.cardTiktok);

        imgShopee = findViewById(R.id.imgShopee);
        imgLazada = findViewById(R.id.imgLazada);
        imgTiktok = findViewById(R.id.imgTiktok);

        txtShopee = findViewById(R.id.txtShopee);
        txtLazada = findViewById(R.id.txtLazada);
        txtTiktok = findViewById(R.id.txtTiktok);

        btnCompareNow = findViewById(R.id.btnCompareNow);

        btnBack.setOnClickListener(v -> finish());

        // Shopee Upload
        cardShopee.setOnClickListener(v -> {

            Intent intent =
                    new Intent(Intent.ACTION_OPEN_DOCUMENT);

            intent.setType("*/*");

            startActivityForResult(
                    intent,
                    PICK_SHOPEE
            );
        });

        // Lazada Upload
        cardLazada.setOnClickListener(v -> {

            Intent intent =
                    new Intent(Intent.ACTION_OPEN_DOCUMENT);

            intent.setType("*/*");

            startActivityForResult(
                    intent,
                    PICK_LAZADA
            );
        });

        // TikTok Upload
        cardTiktok.setOnClickListener(v -> {

            Intent intent =
                    new Intent(Intent.ACTION_OPEN_DOCUMENT);

            intent.setType("*/*");

            startActivityForResult(
                    intent,
                    PICK_TIKTOK
            );
        });

        // Compare Button
        btnCompareNow.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            UploadActivity.this,
                            LoadingActivity.class
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
        });
    }

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data
    ) {

        super.onActivityResult(
                requestCode,
                resultCode,
                data
        );

        if (resultCode != RESULT_OK ||
                data == null ||
                data.getData() == null) {

            return;
        }

        if (requestCode == PICK_SHOPEE) {

            shopeeData =
                    CSVReaderUtil.readCSV(
                            this,
                            data.getData(),
                            "Shopee"
                    );

            if (shopeeData == null) {

                Toast.makeText(
                        this,
                        "Invalid Shopee CSV File",
                        Toast.LENGTH_LONG
                ).show();

                return;
            }

            imgShopee.setImageResource(
                    R.drawable.success
            );

            txtShopee.setText(
                    "Uploaded Shopee CSV Successfully"
            );

            txtShopee.setTextColor(
                    getResources().getColor(
                            R.color.success
                    )
            );

            shopeeUploaded = true;

            checkUploadStatus();
        }

        if (requestCode == PICK_LAZADA) {

            lazadaData =
                    CSVReaderUtil.readCSV(
                            this,
                            data.getData(),
                            "Lazada"
                    );

            if (lazadaData == null) {

                Toast.makeText(
                        this,
                        "Invalid Lazada CSV File",
                        Toast.LENGTH_LONG
                ).show();

                return;
            }

            imgLazada.setImageResource(
                    R.drawable.success
            );

            txtLazada.setText(
                    "Uploaded Lazada CSV Successfully"
            );

            txtLazada.setTextColor(
                    getResources().getColor(
                            R.color.success
                    )
            );

            lazadaUploaded = true;

            checkUploadStatus();
        }

        if (requestCode == PICK_TIKTOK) {

            tiktokData =
                    CSVReaderUtil.readCSV(
                            this,
                            data.getData(),
                            "TikTok"
                    );

            if (tiktokData == null) {

                Toast.makeText(
                        this,
                        "Invalid TikTok CSV File",
                        Toast.LENGTH_LONG
                ).show();

                return;
            }

            imgTiktok.setImageResource(
                    R.drawable.success
            );

            txtTiktok.setText(
                    "Uploaded TikTok CSV Successfully"
            );

            txtTiktok.setTextColor(
                    getResources().getColor(
                            R.color.success
                    )
            );

            tiktokUploaded = true;

            checkUploadStatus();
        }
    }

    private void checkUploadStatus() {

        int uploadedCount = 0;

        if (shopeeUploaded) {

            uploadedCount++;
        }

        if (lazadaUploaded) {
            uploadedCount++;
        }

        if (tiktokUploaded) {
            uploadedCount++;
        }

        if (uploadedCount >= 2) {

            btnCompareNow.setVisibility(
                    View.VISIBLE
            );

        } else {

            btnCompareNow.setVisibility(
                    View.GONE
            );
        }
    }
}