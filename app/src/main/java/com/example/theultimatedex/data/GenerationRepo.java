package com.example.theultimatedex.data;

import java.io.Serializable;
import java.util.ArrayList;

public class GenerationRepo implements Serializable {
    public ArrayList<ability> abilities;
    public String id;
    public main_region main_region;
    public ArrayList<moves> moves;
    public String name;
    public ArrayList<names> names;
    public ArrayList<pokemon_species> pokemon_species;
    public ArrayList<types> types;
    public ArrayList<version_groups> version_groups;

    public class ability implements Serializable {
        public String name;
        public String url;
    }

    public class main_region implements Serializable {
        public String name;
        public String url;
    }
    public class names implements Serializable {
        public language language;
        public String name;

        public class language implements Serializable {
            public String name;
            public String url;
        }
    }
    public class moves implements Serializable {
        public String name;
        public String url;
    }
    public class pokemon_species implements Serializable {
        public String name;
        public String url;
    }
    public class types implements Serializable {
        public String name;
        public String url;
    }
    public class version_groups implements Serializable {
        public String name;
        public String url;
    }
}
