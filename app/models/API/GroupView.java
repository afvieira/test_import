package models.API;

import models.Group;

/**
 * Created by afv on 10/07/14.
 */
public class GroupView {
    public Long id;
    public String code;
    public String description;
    public Integer limitNumber;
    public String passwordRegistration;
    public Boolean closed;

    public GroupView(Group g) {
        this.id = g.id;
        this.code = g.code;
        this.description = g.description;
        this.limitNumber = g.limitNumber;
        this.passwordRegistration = g.passwordRegistration;
        this.closed = g.closed;
    }
}
