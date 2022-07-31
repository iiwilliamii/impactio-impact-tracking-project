package com.example.impactioproject.projects;

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
        projects.add(new Projects("01", "Project 1: The Equality Project", "P1", "The main goal of this project is to ensure that the children in South-East Asia and Africa are getting equality and no hunger."));
        projects.add(new Projects("02", "Project 2: Innovate + Regen", "P2", "Innovate to Regenerate seeks to support and amplify community-led regeneration. We’re working together to make sure communities are supported."));
        projects.add(new Projects("03", "Project 3: Cities of Tomorrow", "P3", "Innovate to Regenerate seeks to support and amplify community-led regeneration. We’re working together to make sure communities are supported."));
        return projects;
    }

    public static Projects findProjects(String symbol) {
        ArrayList<Projects> projects = Projects.getProjects();
        for (final Projects p1 : projects) {
            if (p1.getSymbol().equals(symbol)) {
                return p1;
            }
        }
        return null;
    }
}
