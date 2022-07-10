package com.example.impactioproject;

import java.util.ArrayList;

public class Projects {
    private String id;
    private String name;
    private String symbol;
    private String description;

    public Projects(String id, String name, String symbol, String description) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ArrayList<Projects> getProjects() {
        ArrayList<Projects> projects = new ArrayList<>();
        projects.add(new Projects("01", "Project 1", "P1", "test"));
        return projects;
    }
}
