package com.gmail.etauroginskaya.repository.model;

public class Document {

    private Long id;
    private String unique_number;
    private String description;
    private Integer deleted;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnique_number() {
        return unique_number;
    }

    public void setUnique_number(String unique_number) {
        this.unique_number = unique_number;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
