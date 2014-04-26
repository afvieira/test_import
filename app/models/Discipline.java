package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by NRAM on 07/04/14.
 */
@Entity
public class Discipline extends Model {

    //Variaveis
    @Id
    @Constraints.Min(10)
    public Long id;

    @ManyToOne
    @Constraints.Required
    public Course course;

    @Constraints.Required
    public String code;

    @Constraints.Required
    public Integer course_year;

    @Constraints.Required
    public Integer year;

    @OneToMany
    public List<Shift> shifts;

    @OneToMany
    public List<Group> groups;

    @OneToMany
    public List<Project> projects;

    @ManyToMany
    @JoinTable(name = "user_discipline")
    public List<User> users;

    //construtor
    public Discipline() {
    }

    //ToString
    @Override
    public String toString() {
        return "Discipline{" +
                "id=" + id +
                ", course=" + course.toString() +
                ", code='" + code + '\'' +
                ", course_year=" + course_year +
                ", year=" + year +
                ", disciplines=" + shifts +
                ", groups=" + groups +
                ", projects=" + projects +
                "} " + super.toString();
    }

    //DataBase
    public static Finder<Long, Discipline> find = new Finder(
            Long.class, Discipline.class
    );

    public static List<Discipline> all() {
        return find.all();
    }

    //TODO: duvidas
    public static List<Discipline> allByCourse(Long id_course){
        return find.where()
                .eq("course.id", id_course)
                .findList();
    }

    public static List<Discipline> findByStudent(String emailuser){
        return find.where()
                .eq("users.email", emailuser)
                .findList();
    }

    public static void create(Discipline discipline){
        discipline.save();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }
}
