package com.example.fridge_partner;

// java/FridgeAdapter.java
// java/FridgeAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FridgeAdapter extends RecyclerView.Adapter<FridgeAdapter.ViewHolder> {

    interface OnFridgeClickListener {
        void onFridgeClick(String fridge);
    }

    private List<Item> mData;
    private OnFridgeClickListener mListener;

    public FridgeAdapter(List<Item> data, OnFridgeClickListener listener) {
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
        Item item = mData.get(position);
        holder.textViewTitle.setText(item.getTitle());
        holder.textViewDescription.setText(item.getDescription());
        holder.imageView.setImageResource(item.getImageResourceId());
        holder.itemView.setOnClickListener(v -> mListener.onFridgeClick(item.getTitle()));
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

    public static class Item {
        private String title, description;
        private int imageResourceId;

        public Item(String title, String description, int imageResourceId) {
            this.title = title;
            this.description = description;
            this.imageResourceId = imageResourceId;
        }

        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public int getImageResourceId() { return imageResourceId; }
    }
}
