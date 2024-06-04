
package com.project.RK.pojos;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Activity {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("dueDate")
    private String dueDate;
    @JsonProperty("completed")
    private Boolean completed;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("dueDate")
    public String getDueDate() {
        return dueDate;
    }

    public LocalDateTime getDueDateFormatted() {
        LocalDateTime dueDateFormatted = LocalDateTime.parse(dueDate.substring(0,16));
        return dueDateFormatted;
    }

    @JsonProperty("dueDate")
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @JsonProperty("completed")
    public Boolean getCompleted() {
        return completed;
    }

    @JsonProperty("completed")
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

}
