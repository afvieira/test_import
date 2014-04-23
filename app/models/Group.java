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
