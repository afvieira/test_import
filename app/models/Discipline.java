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
    public String name;

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
    @JoinTable(name = "general_user_discipline") // Tabela user => general_user
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
                ", name='" + name + '\'' +
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

    public static List<Discipline> allByCourse(Long id_course){
        return find.where()
                .eq("course.id", id_course)
                .findList();
    }

    public static List<Discipline> findByUser(String emailuser){
        return find.where()
                .eq("users.email", emailuser)
                .orderBy("year DESC")
                .findList();
    }

    public static Discipline create(Discipline discipline, Long project_id){
        discipline.course = Course.find.ref(project_id);
        discipline.save();
        return discipline;
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }

    public static Discipline getById(Long id){
        return find.where()
                .eq("id", id)
                .findUnique();
    }

    public static void removeShift(Long id_discipline, Long id_shift){
        Discipline d = Discipline.find.setId(id_discipline).fetch("shifts", "id").findUnique();
        d.shifts.remove(
                Shift.find.ref(id_shift)
        );
        d.saveManyToManyAssociations("shifts");
    }

    public static void removeGroup(Long id_discipline, Long id_group){
        Discipline d = Discipline.find.setId(id_discipline).fetch("groups", "id").findUnique();
        d.groups.remove(
                Group.find.ref(id_group)
        );
        d.saveManyToManyAssociations("groups");
    }

    public static void removeProject(Long id_discipline, Long id_project){
        Discipline d = Discipline.find.setId(id_discipline).fetch("projects", "id").findUnique();
        d.projects.remove(
                Project.find.ref(id_project)
        );
        d.saveManyToManyAssociations("projects");
    }
}
