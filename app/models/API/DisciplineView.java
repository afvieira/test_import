package models.API;

import models.Discipline;

public class DisciplineView {
    public Long id;
    public String code;
    public String name;
    public Integer course_year;
    public Integer year;

    public DisciplineView(Discipline d) {
        this.id = d.id;
        this.code = d.code;
        this.name = d.name;
        this.course_year = d.course_year;
        this.year = d.year;
    }
}
