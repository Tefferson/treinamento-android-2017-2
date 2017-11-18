package com.example.teffe.troopersapp.model;

import java.io.Serializable;

/**
 * Created by teffe on 18/11/2017.
 */

public class Trooper implements Serializable {
    private int id;
    private String name;
    private String imageUrl;
    private String description;
    private Affiliation affiliation;

    public Trooper(int id, String name, String imageUrl, String description, Affiliation affiliation) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.affiliation = affiliation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }
}
