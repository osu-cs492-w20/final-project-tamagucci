package com.example.theultimatedex;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PokeAdapter extends RecyclerView.Adapter<PokeAdapter.PokeHolder> {
    public interface pokeItemClickListener {
        void onPokeItemClick();
    }
    @NonNull
    @Override
    public PokeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PokeHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class PokeHolder extends RecyclerView.ViewHolder {

        public PokeHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void bind() {

        }
    }
}
