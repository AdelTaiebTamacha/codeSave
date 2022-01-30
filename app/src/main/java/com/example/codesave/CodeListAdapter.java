package com.example.codesave;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



public class CodeListAdapter extends ListAdapter<Code, CodeViewHolder> {

    public CodeListAdapter(@NonNull DiffUtil.ItemCallback<Code> diffCallBack) {
        super(diffCallBack);
    }

    @Override
    public CodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CodeViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(CodeViewHolder holder, int position) {
        Code current = getItem(position);
        holder.bind(current.getCode());
    }


    static class CodeDiff extends DiffUtil.ItemCallback<Code> {

        @Override
        public boolean areItemsTheSame(@NonNull Code oldItem, @NonNull Code newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Code oldItem, @NonNull Code newItem) {
            return oldItem.getCode().equals(newItem.getCode());
        }
    }
}
