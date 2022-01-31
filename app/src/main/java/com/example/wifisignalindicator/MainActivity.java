package com.example.wifisignalindicator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton plusButton,minusButton,offButton;
    private Button theme;
    private ImageView imageView;
    private int wifiStrength = 0;
    private static final String CURRENT_STATE = "current_state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plusButton = findViewById(R.id.plus_button);
        minusButton = findViewById(R.id.minus_button);
        offButton = findViewById(R.id.off_button);
        imageView = findViewById(R.id.wifi_image);
        theme = findViewById(R.id.theme);

        if (savedInstanceState != null) {
            wifiStrength = savedInstanceState.getInt(CURRENT_STATE);
            setWifiStrength(wifiStrength);
        }

        offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wifiStrength = 0;
                setWifiStrength(wifiStrength);
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wifiStrength == 0){
                    Toast.makeText(MainActivity.this,"Can't decrease below zero",Toast.LENGTH_SHORT).show();
                }
                else {
                    wifiStrength = wifiStrength - 1;
                    setWifiStrength(wifiStrength);
                }
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wifiStrength == 3){
                    Toast.makeText(MainActivity.this,"Can't increase above 3",Toast.LENGTH_SHORT).show();
                }
                else {
                    wifiStrength = wifiStrength + 1;
                    setWifiStrength(wifiStrength);
                }
            }
        });

        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

                switch (currentNightMode) {
                    case Configuration.UI_MODE_NIGHT_NO:
                        // Night mode is not active, we're in day time
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                    case Configuration.UI_MODE_NIGHT_YES:
                        // Night mode is active, we're at night!
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                }
            }
        });
    }

    private void setWifiStrength(int strength){
        switch (strength){
            case 0:
                imageView.setImageLevel(strength);
                break;
            case 1:
                imageView.setImageLevel(strength);
                break;
            case 2:
                imageView.setImageLevel(strength);
                break;
            case 3:
                imageView.setImageLevel(strength);
                break;
            default:
                imageView.setImageLevel(0);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_STATE, wifiStrength);

    }
}