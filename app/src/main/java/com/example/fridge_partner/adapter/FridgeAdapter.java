package com.example.fridge_partner.adapter;

// java/FridgeAdapter.java
// java/FridgeAdapter.java

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fridge_partner.R;
import com.example.fridge_partner.entity.FridgeEntity;

import java.util.List;

public class FridgeAdapter extends RecyclerView.Adapter<FridgeAdapter.ViewHolder> {

    public interface OnFridgeClickListener {
        void onFridgeClick(FridgeEntity entity);

        void onFridgeLongClick(FridgeEntity entity);
    }

    private List<FridgeEntity> mData;
    private OnFridgeClickListener mListener;

    public FridgeAdapter(List<FridgeEntity> data, OnFridgeClickListener listener) {
        mData = data;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fridge_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FridgeEntity item = mData.get(position);
        holder.textViewTitle.setText(item.getTitle());
        holder.textViewDescription.setText(item.getDesc());
        holder.imageView.setImageResource(R.drawable.fridge);
        holder.itemView.setOnClickListener(v -> mListener.onFridgeClick(item));
        holder.itemView.setOnLongClickListener(v -> {
            mListener.onFridgeLongClick(item);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle, textViewDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
        }
    }

}
