package ar.edu.itba.paw.model;

import java.util.List;

public class RecipeList {
    private String name;
    private List<Recipe> list;

    public RecipeList(String name, List<Recipe> list) {
        this.name = name;
        this.list = list;
    }

    public RecipeList(String name) {
        this.name = name;
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
