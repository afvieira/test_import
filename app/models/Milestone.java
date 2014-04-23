package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

/**
 * Created by NRAM on 23/04/14.
 */
@Entity
public class Milestone extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @ManyToOne
    public Project project;

    @Constraints.Required
    public String code;

    @Constraints.Required
    public String description;

    @Constraints.Required
    public Integer number;

    @Constraints.Required
    public Date startDate;

    @Constraints.Required
    public Date endDate;

    @Constraints.Required
    public String path;

    @Constraints.Required
    public Integer percentage;

    @ManyToMany
    public List<Group> groups;


    public static Finder<Long, Group> find = new Finder(
            Long.class, Group.class
    );

    public static List<Group> all() {
        return find.all();
    }

    public static void create(Group group){
        group.save();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }
}
