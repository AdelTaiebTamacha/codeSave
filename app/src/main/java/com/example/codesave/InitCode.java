package com.example.codesave;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codesave.codeRoom.Code;
import com.example.codesave.codeRoom.CodeViewModel;

import java.util.ArrayList;
import java.util.Random;

public class InitCode extends AppCompatActivity {


    private CodeViewModel codeViewModel;
    private static int codeNumber = 12*3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_code);

        codeViewModel = new ViewModelProvider(this).get(CodeViewModel.class);

        final Button setButton = findViewById(R.id.setCode);
        final EditText codeText = findViewById(R.id.editCode);
        final SeekBar colorSeekBar = findViewById(R.id.colorSeekBar);


        // Color picker
        colorSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int bgColor = Color.HSVToColor(new float[]{i*360/100, 1,1});
                colorSeekBar.setBackgroundColor(bgColor);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

        });

        // Validating action
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check if input code is formated [0-9]{4}
                String inputCode = String.valueOf(codeText.getText());
                if (inputCode.length() != 4) {
                    Toast.makeText(getApplicationContext(), "Please enter a correct code format, ex: 1234", Toast.LENGTH_LONG);
                } else {
                    codeViewModel.deleteAll();

                    ArrayList<String> fakeCodes = new ArrayList<String>();
                    ArrayList<String> colors = new ArrayList<String>();


                    // Random code list
                    Random r = new Random();
                    int indexRightCode = r.nextInt(codeNumber);
                    int indexRightColor = colorSeekBar.getProgress();
                    Log.d("codeSave", "Progress value = " + String.valueOf(indexRightColor));
                    for (int i = 0; i < codeNumber; i++) {
                        String value = String.valueOf(r.nextInt(9999));
                        value = String.format("%04d", Integer.parseInt(value));
                        fakeCodes.add(value);

                        int delta = 360/codeNumber;
                        int alpha = indexRightColor*360/100;
                        int color = (360 + alpha + (i-indexRightCode)*delta)%360;
                        colors.add(String.valueOf(color));
//                        Log.d("codeSave", "Code :" + value + ", color:" + String.valueOf(color));
                    }

                    // Insert real code
                    fakeCodes.set(indexRightCode, inputCode);

                    // Save codes
                    Code code;
                    for (int i = 0; i < fakeCodes.size()/3; i++) {
                        String newCodes = "" + fakeCodes.get(3*i) + ","
                                + fakeCodes.get(3*i+1) + ","
                                + fakeCodes.get(3*i+2);

                        String newColors = "" + colors.get(3*i) + ","
                                + colors.get(3*i + 1) + ","
                                + colors.get(3*i + 2);
                        code = new Code(newCodes, i, newColors, "test", 1);
                        codeViewModel.insert(code);
//                        Log.d("codeSave", "New codes:" + newCodes + " # " + newColors);
                    }

                    // Return to main activity
                    finish();
                }

            }
        });
    }
}