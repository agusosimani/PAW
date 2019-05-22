package ar.edu.itba.paw2019a2.model;

import java.util.List;

public class RecipeList {
    private int id;
    private String name;
    private List<Recipe> list;

    public RecipeList(String name, List<Recipe> list) {
        this.name = name;
        this.list = list;
    }

    public RecipeList(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Recipe> getList() {
        return list;
    }

    public void setList(List<Recipe> list) {
        this.list = list;
    }
}
