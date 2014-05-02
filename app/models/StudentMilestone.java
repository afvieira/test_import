package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

/**
 * Created by NRAM on 23/04/14.
 */
@Entity
public class StudentMilestone extends Model {

    //Variaveis
    @Id
    @Constraints.Min(10)
    public Long id;

    @ManyToOne
    public User student;

    @ManyToOne
    public Milestone milestone;

    public Integer avaliation;

    public String path;

    public String privateComment;

    public String publicCommentTeacher;

    public String publicCommentStudent;

    @Constraints.Required
    public Date lastUpdate;

    @Constraints.Required
    public boolean closed;

    //Construtor
    public StudentMilestone() {
    }

    //ToString
    @Override
    public String toString() {
        return "StudentMilestone{" +
                "id=" + id +
                ", student=" + student +
                ", milestone=" + milestone +
                ", avaliation=" + avaliation +
                ", path='" + path + '\'' +
                ", privateComment='" + privateComment + '\'' +
                ", publicCommentTeacher='" + publicCommentTeacher + '\'' +
                ", publicCommentStudent='" + publicCommentStudent + '\'' +
                ", closed='" + closed + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                "} " + super.toString();
    }


    //DataBase
    public static Finder<Long, StudentMilestone> find = new Finder(
            Long.class, StudentMilestone.class
    );

    public static List<StudentMilestone> all() {
        return find.all();
    }

    public static void create(StudentMilestone studentmilestone){
        studentmilestone.save();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }

    // TODO: Alterar o nome do método
    public static List<StudentMilestone> getLastAvaliations(String emailuser){
        return find.where()
                .eq("student.email", emailuser)
                .isNotNull("avaliation")
                .orderBy("lastUpdate desc")
                .findList();
    }

}
