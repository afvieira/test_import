package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
                "} " + super.toString();
    }

    //DataBase

}
