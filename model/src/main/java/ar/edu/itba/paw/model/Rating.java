package ar.edu.itba.paw.model;

import java.util.Map;

public class Rating {
    private int recipeId;
    //private int rating;
    private Map<User, Integer> users;

    /*protected*/ Rating() {
        //Para hibernate futuro.
    }


}
