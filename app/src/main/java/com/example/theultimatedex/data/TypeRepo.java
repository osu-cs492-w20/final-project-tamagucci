package com.example.theultimatedex.data;

import java.io.Serializable;
import java.util.ArrayList;

public class TypeRepo implements Serializable {
    public ArrayList<Pokem> pokemon;
    public String name;
    public class Pokem {
        public PokeMon pokemon;
        public String slot;
        public class PokeMon {
            public String name;
            public String url;
        }
    }
}
