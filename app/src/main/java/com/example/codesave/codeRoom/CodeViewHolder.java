package com.example.codesave.codeRoom;

import android.graphics.Color;
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

    public void bind(String code, int codePosition, String color) {
        Log.d("codeSave", "code: "+code);
        String[] codes = code.split(",");
        String[] colors = color.split(",");
        codeItemView_1.setText(codes[0]);
        codeItemView_2.setText(codes[1]);
        codeItemView_3.setText(codes[2]);
        codeItemView_1.setBackgroundColor(string2Color(colors[0]));
        codeItemView_2.setBackgroundColor(string2Color(colors[1]));
        codeItemView_3.setBackgroundColor(string2Color(colors[2]));

    }

    static CodeViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_code, parent, false);
        return new CodeViewHolder(view);
    }

    private int string2Color(String v) { return Color.HSVToColor(150,new float[]{Float.parseFloat(v), 1,1});  }
}