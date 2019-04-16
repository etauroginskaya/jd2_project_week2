package com.gmail.etauroginskaya.service.model;

public class DocumentDTO {

    private Long id;
    private String unique_number;
    private String description;


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

    @Override
    public String toString() {
        return "DocumentDTO{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", unique_number='" + unique_number + '\'' +
                '}';
    }
}
