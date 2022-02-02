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

import com.example.codesave.codeRoom.Code;
import com.example.codesave.codeRoom.CodeListAdapter;
import com.example.codesave.codeRoom.CodeViewModel;

import org.w3c.dom.Text;

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


        codeViewModel = new ViewModelProvider(this).get(CodeViewModel.class);
        codeViewModel.getCodeList().observe(this, codes -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(codes);
        });

//        // Create UI
//        final Button delAll = findViewById(R.id.deleteAll);
//        delAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                codeViewModel.deleteAll();
//                Log.d(TAG, "Delete all");
//            }
//        });
//
//        final Button shuffle = findViewById(R.id.shuffle);
//        shuffle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Random r = new Random();
////                String value = String.valueOf(r.nextInt(9999));
////                value = String.format("%04d", Integer.parseInt(value));
////                Code code = new Code(value, "test", 2);
////                codeViewModel.insert(code);
////                Log.d(TAG, "Adding "+value);
//                codeViewModel.shuffle();
//            }
//        });


//        // Create UI
//        final Button restart = findViewById(R.id.restart);
//        restart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                codeViewModel.deleteAll();
//                Code code;
//                code = new Code("1111,1112,1113", 0, "0.0 ,0.0,0.0", "test", 1);	codeViewModel.insert(code);
//                code = new Code("2221,2222,2223", 1, "40.0 ,40.0,40.0", "test", 1);	codeViewModel.insert(code);
//                code = new Code("3331,3332,3333", 2, "80.0 ,80.0,80.0", "test", 1);	codeViewModel.insert(code);
//                code = new Code("4441,4442,4443", 3, "120.0 ,120.0,120.0", "test", 1);	codeViewModel.insert(code);
//                code = new Code("5551,5552,5553", 4, "160.0 ,160.0,160.0", "test", 1);	codeViewModel.insert(code);
//                code = new Code("6661,6662,6663", 5, "200.0 ,200.0,200.0", "test", 1);	codeViewModel.insert(code);
//                code = new Code("7771,7772,7773", 6, "240.0 ,240.0,240.0", "test", 1);	codeViewModel.insert(code);
//                code = new Code("8881,8882,8883", 7, "280.0 ,280.0,280.0", "test", 1);	codeViewModel.insert(code);
//                code = new Code("9991,9992,9993", 8, "320.0 ,320.0,320.0", "test", 1);	codeViewModel.insert(code);
//                code = new Code("0001,0002,0003", 9, "360.0 ,360.0,360.0", "test", 1);	codeViewModel.insert(code);
//            }
//        });
    }

    // Shuffle all the codes
    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        codeViewModel.shuffle();
    }
}