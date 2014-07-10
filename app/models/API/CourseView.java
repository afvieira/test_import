package models.API;

import models.Course;

public class CourseView {
    public Long id;
    public String code;
    public String description;

    public CourseView(Course c) {
        this.id = c.id;
        this.code = c.code;
        this.description = c.description;
    }
}