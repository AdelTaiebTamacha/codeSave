package com.example.codesave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.IpSecManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.Executor;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "codeSave";

    public static final String EXTRA_MESSAGE = "com.example.codesave.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Init Biometric
        verifBiometric();


        // Biometric prompt
        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("AUTHENTICATE")
                .setDescription("Use your fingerprint to login")
                .setNegativeButtonText("Cancel")
                .build();

        // Create UI
        final BiometricPrompt biometricAuthPrompt = initBiometricPrompt(() -> startApp());
        final Button authButton = findViewById(R.id.login);
        authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biometricAuthPrompt.authenticate(promptInfo);
                startApp();
            }
        });

        final Button resetButton = findViewById(R.id.reset);
        final BiometricPrompt biometricInitPrompt = initBiometricPrompt(() -> resetApp());
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biometricInitPrompt.authenticate(promptInfo);
                resetApp();
            }
        });



    }


    private BiometricPrompt initBiometricPrompt(Runnable sucess) {
        Executor biometricExecutor = ContextCompat.getMainExecutor(this);
        return new BiometricPrompt(MainActivity.this, biometricExecutor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Log.d(TAG, "Authentication Failed");
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Log.d(TAG, "Authentication Succeeded");
                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                sucess.run();
            }

        });
    }

    private void verifBiometric() {
        // Check biometric
        BiometricManager biometricManager = androidx.biometric.BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()) {

            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.d(TAG, "Biometric active");
                break;

            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Log.d(TAG, "Biometric not found");
                break;

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Log.d(TAG, "Biometric not available");
                break;

            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Log.d(TAG, "Biometric not enrolled");
                Toast.makeText(getApplicationContext(), "Your device doesn't have fingerprint saved,please check your security settings", Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void startApp() {
        Intent intent = new Intent(this, DisplayCode.class);
        String message = "message info";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void resetApp() {
        Intent intent = new Intent(this, InitCode.class);
        String message = "message info";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}