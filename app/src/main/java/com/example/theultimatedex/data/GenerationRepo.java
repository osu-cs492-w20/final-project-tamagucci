package com.example.theultimatedex.data;

import java.util.ArrayList;

public class GenerationRepo {
    public ArrayList<ability> abilities;
    public String id;
    public main_region main_region;
    public ArrayList<moves> moves;
    public String name;
    public ArrayList<names> names;
    public ArrayList<pokemon_species> pokemon_species;
    public ArrayList<types> types;
    public ArrayList<version_groups> version_groups;

    public class ability {
        public String name;
        public String url;
    }

    public class main_region {
        public String name;
        public String url;
    }
    public class names {
        public language language;
        public String name;

        public class language {
            public String name;
            public String url;
        }
    }
    public class moves {
        public String name;
        public String url;
    }
    public class pokemon_species {
        public String name;
        public String url;
    }
    public class types {
        public String name;
        public String url;
    }
    public class version_groups {
        public String name;
        public String url;
    }
}
