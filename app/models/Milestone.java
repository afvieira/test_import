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
public class Milestone extends Model {

    //Variaveis
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

    @OneToMany
    public List<GroupMilestone> groups;

    @OneToMany
    public List<StudentMilestone> students;

    //Construtor
    public Milestone() {
    }

    //ToString
    @Override
    public String toString() {
        return "Milestone{" +
                "id=" + id +
                ", project=" + project +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", number=" + number +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", path='" + path + '\'' +
                ", percentage=" + percentage +
                ", groups=" + groups +
                ", students=" + students +
                "} " + super.toString();
    }

    //DataBase
    public static Finder<Long, Milestone> find = new Finder(
            Long.class, Milestone.class
    );

    public static List<Milestone> findByStudent(String emailuser){
        return find.where()
                .eq("students.student.email", emailuser)
                .findList();
    }

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
