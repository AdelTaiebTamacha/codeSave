package com.example.codesave;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.codesave.R;

public class CodeViewHolder extends RecyclerView.ViewHolder{
    private final TextView codeItemView_1;
    private final TextView codeItemView_2;
    private final TextView codeItemView_3;


    private CodeViewHolder(View itemView){
        super(itemView);
        codeItemView_1 = itemView.findViewById(R.id.recyclerCodeValue_1);
        codeItemView_2 = itemView.findViewById(R.id.recyclerCodeValue_2);
        codeItemView_3 = itemView.findViewById(R.id.recyclerCodeValue_3);
    }

    public void bind(String text, int codePosition, int recyclerPosition) {
        int rank = codePosition/3;
        Log.d("codeSave", "code: "+String.valueOf(codePosition) + " recy: " + String.valueOf(recyclerPosition));

        switch (codePosition) {
            case 1: codeItemView_1.setText(text); break;
            case 2: codeItemView_2.setText(text); break;
            case 3: codeItemView_3.setText(text); break;
            default: Log.e("codeSave", "EROOR"); break;
        }
    }

    static CodeViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new CodeViewHolder(view);
    }
}