package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by NRAM on 23/04/14.
 */
@Entity
public class Project extends Model {

    //Variaveis
    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    @ManyToOne
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

    @Constraints.Required
    public Date creationDate;

    @OneToMany
    public List<Milestone> milestones;

    @OneToMany
    public List<GroupProject> groups;

    @OneToMany
    public List<StudentProject> students;

    //Construtor
    public Project() {
    }

    //ToString
    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", discipline=" + discipline +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", creationDate=" + creationDate +
                ", milestones=" + milestones +
                ", groups=" + groups +
                ", students=" + students +
                "} " + super.toString();
    }

    //DataBase
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

    public static List<Project> getByCreatedDate(String emailuser){
        return find.where()
                .eq("students.student.email", emailuser)
                .orderBy("creationDate desc")
                .findList();
    }
}
