package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
}
