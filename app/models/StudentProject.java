package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * Created by afv on 25/04/14.
 */
@Entity
public class StudentProject extends Model {

    //Variaveis
    @Id
    @Constraints.Min(10)
    public Long id;

    @ManyToOne
    public User student;

    @ManyToOne
    public Project project;

    public Integer avaliation;

    //Construtor
    public StudentProject() {
    }

    public StudentProject(Long id, User student) {
        this.id = id;
        this.student = student;
    }

    public StudentProject(Long id, User student, Project project, Integer avaliation) {
        this.id = id;
        this.student = student;
        this.project = project;
        this.avaliation = avaliation;
    }

    //ToString
    @Override
    public String toString() {
        return "StudentProject{" +
                "id=" + id +
                ", student=" + student +
                ", project=" + project +
                ", avaliation=" + avaliation +
                "} " + super.toString();
    }

    //DataBase
    public static Finder<Long, StudentProject> find = new Finder(
            Long.class, StudentProject.class
    );

    public static List<StudentProject> all() {
        return find.all();
    }

    public static void create(StudentProject project){
        project.save();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }

    public static List<StudentProject> getByCreatedDate(String emailuser){
        return find.where()
                .eq("student.email", emailuser)
                .findList();
    }
}
