package com.example.impactioproject.projects;

import com.example.impactioproject.R;

import java.util.ArrayList;

public class Projects {
    private String id;
    private String name;
    private String symbol;
    private String description;
    private Integer image;

    public Projects(String id, String name, String symbol, String description, Integer image) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.description = description;
        this.image = image;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
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
        projects.add(new Projects("01", "Project 1: Sustainable Impact", "P1", "We are seeking projects that offer smarter, faster ways to make real progress on the United Nations Sustainable Development Goals and transform the way we do business in NSW. Our mission is to solve wicked sustainability problems. ", R.drawable.regen_hd_proreshq_20211203_00_11_58_00_2));
        projects.add(new Projects("02", "Project 2: Innovate + Regen", "P2", "Innovate to Regenerate seeks to support and amplify community-led regeneration. We’re working together to make sure communities are supported.", R.drawable.regen_hero));
        projects.add(new Projects("03", "Project 3: Cities of Tomorrow", "P3", "Innovate to Regenerate seeks to support and amplify community-led regeneration. We’re working together to make sure communities are supported.", R.drawable.cities_of_tomorrow));
        projects.add(new Projects("04", "Project 4: Bushfire Challenge", "P4", "We look for solutions that enable and incentivise landholders and communities to own and drive regeneration outcomes. Solutions should cover at least one of these areas: fire risk management, regenerative land use, species recovery and building ecological, economic and social resilience to climate change..", R.drawable.sa_challenge));
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
