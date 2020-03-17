package com.example.theultimatedex.data;

import android.icu.lang.UProperty;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "repos")
public class PokemonRepo implements Serializable {
    @Ignore
    public ArrayList<abilities> abilities;
    public String base_experience;
    @Ignore
    public ArrayList<form> forms;
    @Ignore
    public ArrayList<gameIndex> game_indices;
    public String height;
    @Ignore
    public ArrayList<heldItem> held_items;

    @PrimaryKey
    @NonNull
    public String id;

    public String is_default;
    public String location_area_encounters;
    @Ignore
    public ArrayList<Moves> moves;
    public String name;
    public String order;
    @Ignore
    public Species species;
    @Ignore
    public sprites sprites;
    @Ignore
    public ArrayList<Stats> stats;
    @Ignore
    public ArrayList<Pktypes> types;
    public String weight;



    // Classes needed for the json returns.
    //@TypeConverter            < -- doesn't work
    public class abilities implements Serializable {
        public ability ability;
        public String ishidden;
        public String slot;
        public class ability implements Serializable {
            public String name;
            public String url;
        }
    }
    public class form implements Serializable {
        public String name;
        public String url;
    }
    public class gameIndex implements Serializable {
        public String game_index;
        public Version version;
        public class Version implements Serializable {
            public String name;
            public String url;
        }
    }
    public class heldItem implements Serializable {
        public Item item;
        public ArrayList<VersionDetail> version_details;
        public class Item implements Serializable {
            public String name;
            public String url;
        }
        public class VersionDetail implements Serializable {
            public String rarity;
            public Version version;
            public class Version implements Serializable {
                public String name;
                public String url;
            }
        }
    }
    public class Moves implements Serializable {
        public Move move;
        public ArrayList<VersionGroupDetail> version_group_details;
        public class Move implements Serializable {
            public String name;
            public String url;
        }
        public class VersionGroupDetail implements Serializable {
            public String level_learned_at;
            public MoveLearnMethod move_learn_method;
            public VersionGroup version_group;
            public class MoveLearnMethod implements Serializable {
                public String name;
                public String url;
            }
            public class VersionGroup implements Serializable {
                public String name;
                public String url;
            }
        }
    }
    public class Species implements Serializable {
        public String name;
        public String url;
    }
    public class Stats implements Serializable {
        public String base_stat;
        public String effort;
        public Stat stat;
        public class Stat implements Serializable {
            public String name;
            public String url;
        }
    }
    public class Pktypes implements Serializable {
        public String slot;
        public Pktype type;
        public class Pktype implements Serializable {
            public String name;
            public String url;
        }
    }
    // Other stuff that kira might need.


    //@SerializedName("pokemon_species")
    //public String species;


    //public ArrayList<BaseStats> stats;

    // Info to retrieve from API of each pokemon
    // species {} -- actually no there's just a place for name
    //      - name
    // sprites {}
    //      - front_default
    // stats [ {} ] ( for each stat )
    //      - base stat
    //      - stat {}
    //          - name
    // types [ {} ] ( for each potential type )
    //      - type {}
    //          - name
}
