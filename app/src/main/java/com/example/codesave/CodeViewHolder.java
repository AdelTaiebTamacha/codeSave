package com.example.codesave;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.codesave.R;

public class CodeViewHolder extends RecyclerView.ViewHolder{
    private final TextView codeItemView;

    private CodeViewHolder(View itemView){
        super(itemView);
        codeItemView = itemView.findViewById(R.id.recyclerCodeValue);
    }

    public void bind(String text) {
        codeItemView.setText(text);
    }

    static CodeViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new CodeViewHolder(view);
    }
}