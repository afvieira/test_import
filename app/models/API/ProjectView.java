package models.API;

import models.Project;

import java.util.Date;

/**
 * Created by afv on 10/07/14.
 */
public class ProjectView {
    public Long id;
    public String code;
    public String title;
    public String description;
    public Date startDate;
    public Date endDate;
    public Date creationDate;

    public ProjectView(Project p) {
        this.id = p.id;
        this.code = p.code;
        this.title = p.title;
        this.description = p.description;
        this.startDate = p.startDate;
        this.endDate = p.endDate;
        this.creationDate = p.creationDate;
    }
}
