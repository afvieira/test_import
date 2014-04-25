package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by NRAM on 23/04/14.
 */
@Entity
@Table(name = "GroupTable") //a tabela nao se pode chamar Group, visto ser uma palavra reservada da base de dados
public class Group extends Model {

    //Variaveis
    @Id
    @Constraints.Min(10)
    public Long id;

    @ManyToOne
    @Constraints.Required
    public Discipline discipline;

    @Constraints.Required
    public String code;

    @Constraints.Required
    public String description;

    @Constraints.Required
    public Integer limitNumber;

    @Constraints.Required
    public String passwordRegistration;

    @Constraints.Required
    public Boolean closed;

    @OneToMany
    public List<GroupMilestone> milestones;

    @OneToMany
    public List<GroupProject> projects;

    //Construtor
    public Group() {
    }

    //ToString
    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", discipline=" + discipline +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", limitNumber=" + limitNumber +
                ", passwordRegistration='" + passwordRegistration + '\'' +
                ", closed=" + closed +
                ", milestones=" + milestones +
                ", projects=" + projects +
                "} " + super.toString();
    }

    //DataBase
    public static Finder<Long, Group> find = new Finder(
            Long.class, Group.class
    );

    public static List<Group> all() {
        return find.all();
    }

    public static void create(Group group){
        group.save();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }
}
