package com.example.codesave;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class DisplayCode extends AppCompatActivity {

    private static final String TAG = "codeSave";
    private CodeViewModel codeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_code);


        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CodeListAdapter adapter = new CodeListAdapter(new CodeListAdapter.CodeDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create UI
        final Button delAll = findViewById(R.id.deleteAll);
        delAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeViewModel.deleteAll();
                Log.d(TAG, "Delete all");
            }
        });

        final Button createNew = findViewById(R.id.createNew);
        delAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                String value = String.valueOf(r.nextInt(9999));
                value = String.format("%04d", Integer.parseInt(value));
                Code code = new Code(value, "test", 2);
                codeViewModel.insert(code);
                Log.d(TAG, "Adding "+value);


            }
        });


        codeViewModel = new ViewModelProvider(this).get(CodeViewModel.class);
        codeViewModel.getCodeList().observe(this, codes -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(codes);
        });
    }
}