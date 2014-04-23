package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * Created by NRAM on 23/04/14.
 */
@Entity
public class Project extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public Discipline discipline;

    @Constraints.Required
    public String code;

    @Constraints.Required
    public String title;

    @Constraints.Required
    public String description;

    @Constraints.Required
    public Date startDate;

    @Constraints.Required
    public Date endDate;

    @OneToMany
    public List<Milestone> milestones;

    @OneToMany
    public List<Group> groups;

    @OneToMany
    public List<Student> students;

    public static Finder<Long, Project> find = new Finder(
            Long.class, Project.class
    );

    public static List<Project> all() {
        return find.all();
    }

    public static void create(Project project){
        project.save();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }
}
