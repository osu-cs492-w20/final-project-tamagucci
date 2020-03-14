package com.example.theultimatedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theultimatedex.data.PokemonRepo;

import java.util.List;

public class PokeAdapter extends RecyclerView.Adapter<PokeAdapter.PokeHolder> {

    private List<PokemonRepo> mPokemonItems;
    private PokeAdapter.pokeItemClickListener onPokeItemClickListener;

    public interface pokeItemClickListener {
        void onPokeItemClick(PokemonRepo pokeRepo);
    }

    public PokeAdapter(pokeItemClickListener listener) {
        onPokeItemClickListener = listener;
    }

    public void updateSearchResults(List<PokemonRepo> pokemonItems) {
        mPokemonItems = pokemonItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PokeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.poke_item, parent, false);
        return new PokeAdapter.PokeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PokeHolder holder, int position) {
        holder.bind(mPokemonItems.get(position));
    }

    @Override
    public int getItemCount() {
        if (mPokemonItems != null) {
            return mPokemonItems.size();
        } else {
            return 0;
        }
    }

    public class PokeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mPokemonSpriteIV;
        private TextView mPokemonNumberTV;

        public PokeHolder(@NonNull View itemView) {
            super(itemView);
            mPokemonSpriteIV = itemView.findViewById(R.id.iv_poke_sprite);
            mPokemonNumberTV = itemView.findViewById(R.id.tv_poke_number);
            itemView.setOnClickListener(this);
        }
        public void bind(PokemonRepo pokemonRepo) {
            Context context = mPokemonNumberTV.getContext();
            // *** SHARED PREFS NEED TO GO IN HERE ***
            String pokemonNumber = pokemonRepo.id;
            mPokemonNumberTV.setText(pokemonNumber);
            /*
            for reference for getting sprite later
            String iconURL = OpenWeatherMapUtils.buildIconURL(forecastItem.icon);
            Glide.with(mWeatherIconIV.getContext()).load(iconURL).into(mWeatherIconIV);
             */
        }

        @Override
        public void onClick(View v) {
            PokemonRepo pokemonRepo = mPokemonItems.get(getAdapterPosition());
            onPokeItemClickListener.onPokeItemClick(pokemonRepo);
        }
    }
}
