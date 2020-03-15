package com.example.theultimatedex.data;

import android.icu.lang.UProperty;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class PokemonRepo implements Serializable {
    public ArrayList<abilities> abilities;
    public String base_experience;
    public ArrayList<form> forms;
    public ArrayList<gameIndex> game_indices;
    public String height;
    public ArrayList<heldItem> held_items;
    public String id;
    public String is_default;
    public String location_area_encounters;
    public ArrayList<Moves> moves;
    public String name;
    public String order;
    public Species species;
    public sprites sprites;
    public ArrayList<Stats> stats;
    public ArrayList<Pktypes> types;
    public String weight;



    // Classes needed for the json returns.
    public class abilities {
        public ability ability;
        public String ishidden;
        public String slot;
        public class ability {
            public String name;
            public String url;
        }
    }
    public class form {
        public String name;
        public String url;
    }
    public class gameIndex{
        public String game_index;
        public Version version;
        public class Version {
            public String name;
            public String url;
        }
    }
    public class heldItem {
        public Item item;
        public ArrayList<VersionDetail> version_details;
        public class Item {
            public String name;
            public String url;
        }
        public class VersionDetail {
            public String rarity;
            public Version version;
            public class Version {
                public String name;
                public String url;
            }
        }
    }
    public class Moves {
        public Move move;
        public ArrayList<VersionGroupDetail> version_group_details;
        public class Move {
            public String name;
            public String url;
        }
        public class VersionGroupDetail {
            public String level_learned_at;
            public MoveLearnMethod move_learn_method;
            public VersionGroup version_group;
            public class MoveLearnMethod {
                public String name;
                public String url;
            }
            public class VersionGroup {
                public String name;
                public String url;
            }
        }
    }
    public class Species {
        public String name;
        public String url;
    }
    public class Stats {
        public String base_stat;
        public String effort;
        public Stat stat;
        public class Stat {
            public String name;
            public String url;
        }
    }
    public class Pktypes {
        public String slot;
        public Pktype type;
        public class Pktype {
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
