package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by NRAM on 23/04/14.
 */
@Entity
public class GroupMilestone extends Model {

    //Variaveis
    @Id
    @Constraints.Min(10)
    public Long id;

    @ManyToOne
    public Group group;

    @ManyToOne
    public Milestone milestone;

    public Integer avaliation;

    public String path;

    public String privateComment;

    public String publicCommentTeacher;

    public String publicCommentStudent;

    //Construtor
    public GroupMilestone() {
    }

    //ToString
    @Override
    public String toString() {
        return "GroupMilestone{" +
                "id=" + id +
                ", group=" + group +
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
