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
public class GroupProject extends Model {

    public enum StateOfProject{
        AT, //Ativo
        AR, //Arquivado
        PU  //Publico
    }

    //Variaveis
    @Id
    @Constraints.Min(10)
    public Long id;

    @ManyToOne
    public Group group;

    @ManyToOne
    public Project project;

    public String path;

    @Constraints.Required
    public StateOfProject State;

    //Construtor
    public GroupProject() {
    }

    //ToString
    @Override
    public String toString() {
        return "GroupProject{" +
                "id=" + id +
                ", group=" + group +
                ", project=" + project +
                ", path='" + path + '\'' +
                ", State=" + State +
                "} " + super.toString();
    }

    //DataBase
}
