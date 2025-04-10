package org.jvk.yccompanydirectory.company;

import jakarta.persistence.*;

import java.util.List;

// @Entity says that this class will be mapped to a database table
// Represents data model in our application

@Entity
@Table(name="yc_companies")
public class Company {

    @Id
    // unique = true bc no duplciates & nullable = false bc no null entries
    @Column(name = "yc_url", unique = true, nullable = false)
    private String ycURL;
    private String name;
    private String description;
    private String websiteURL;
    private String batch;

    @ElementCollection
    private List<String> tags;

    // Constructors
    public Company() {
    }

    public Company(String ycURL, String name, String description, String websiteURL, String batch, List<String> tags) {
        this.ycURL = ycURL;
        this.name = name;
        this.description = description;
        this.websiteURL = websiteURL;
        this.batch = batch;
        this.tags = tags;
    }

    // Getters
    public String getYcURL() {
        return ycURL;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public String getBatch() {
        return batch;
    }

    public List<String> getTags() {
        return tags;
    }

    // Setters
    public void setYcURL(String ycURL) {
        this.ycURL = ycURL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
