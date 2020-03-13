package com.example.theultimatedex;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theultimatedex.data.PokemonRepo;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonItemViewHolder> {

    private List<PokemonRepo> mPokemonItems;
    private OnPokemonItemClickListener mPokemonItemClickListener;


    public interface OnPokemonItemClickListener {
        void onPokemonItemClick(PokemonRepo pokemonRepo);
    }

    public PokemonAdapter(OnPokemonItemClickListener clickListener) {
        mPokemonItemClickListener = clickListener;
    }

    public void updateSearchResults(List<PokemonRepo> pokemonItems) {
        mPokemonItems = pokemonItems;
        notifyDataSetChanged();
    }


    @Override
    public PokemonItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.pokemon_list_item, parent, false);
        return new PokemonItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonItemViewHolder holder, int position) {

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



    public class PokemonItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mPokemonSpriteIV;
        private TextView mPokemonNumberTV;

        public PokemonItemViewHolder(View itemView) {
            super(itemView);
            mPokemonSpriteIV = itemView.findViewById(R.id.iv_pokemon_sprite);
            mPokemonNumberTV = itemView.findViewById(R.id.tv_pokemon_number);
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
            mPokemonItemClickListener.onPokemonItemClick(pokemonRepo);
        }
    }


}
