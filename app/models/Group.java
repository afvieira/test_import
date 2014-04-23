package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by NRAM on 23/04/14.
 */
@Entity
public class Group extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public Discipline discipline;

    @Constraints.Required
    public List<Student> students;

    @Constraints.Required
    public String code;

    @Constraints.Required
    public String description;

    @Constraints.Required
    public Integer limitNumber;

    @Constraints.Required
    public String passwordRegistration;

    @Constraints.Required
    public Boolean closed;


    public static Finder<Long, Milestone> find = new Finder(
            Long.class, Milestone.class
    );

    public static List<Milestone> all() {
        return find.all();
    }

    public static void create(Milestone milestone){
        milestone.save();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }
}
