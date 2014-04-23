package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Created by NRAM on 23/04/14.
 */
@Entity
public class StudentMilestone extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public Student student;

    @Constraints.Required
    public Milestone milestone;

    public Integer avaliation;

    public String path;

    public String privateComment;

    public String publicCommentTeacher;

    public String publicCommentStudent;

}
