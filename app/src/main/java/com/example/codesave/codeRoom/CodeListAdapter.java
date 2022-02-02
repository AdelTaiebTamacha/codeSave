package com.example.codesave.codeRoom;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;


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
        holder.bind(current.getCode(), current.getPosition(), current.getColor());
    }


    public static class CodeDiff extends DiffUtil.ItemCallback<Code> {

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
