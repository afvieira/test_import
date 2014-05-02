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

    public static List<StudentMilestone> getLastAvaliations(String emailuser){
        return find.where()
                .eq("student.email", emailuser)
                .isNotNull("avaliation")
                .orderBy("lastUpdate desc")
                .findList();
    }

    /**
     * Obter a lista de  StudentMilestone pelo User e id do projeto
     * @param emailUser
     * @param id_project
     * @return
     */
    public static List<StudentMilestone> getByProject(String emailUser, Long id_project){
        return find
                .fetch("milestone")
                .where()
                .eq("milestone.project.id", id_project)
                .eq("student.email",emailUser)
                .orderBy("milestone.endDate DESC")
                .findList();

    }

    /**
     * Obter o StudentMilestone pelo User e id da etapa
     * @param emailUser
     * @param id_milestone
     * @return
     */
    public static StudentMilestone getByUserMilestone(String emailUser, Long id_milestone){
        return find
                .where()
                .eq("milestone.id", id_milestone)
                .eq("student.email",emailUser)
                .findUnique();
    }
}
