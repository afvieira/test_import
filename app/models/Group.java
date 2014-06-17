package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by NRAM on 23/04/14.
 */
@Entity
@Table(name = "general_group") //a tabela nao se pode chamar Group, visto ser uma palavra reservada da base de dados
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

    @ManyToMany
    @JoinTable(name = "general_user_general_group")
    public List<User> students;

    @OneToMany
    public List<GroupMilestone> milestones;

    @OneToMany
    public List<GroupProject> projects;

    //Construtor
    public Group() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLimitNumber() {
        return limitNumber;
    }

    public void setLimitNumber(Integer limitNumber) {
        this.limitNumber = limitNumber;
    }

    public String getPasswordRegistration() {
        return passwordRegistration;
    }

    public void setPasswordRegistration(String passwordRegistration) {
        this.passwordRegistration = passwordRegistration;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public List<GroupMilestone> getMilestones() {
        return milestones;
    }

    public void setMilestones(List<GroupMilestone> milestones) {
        this.milestones = milestones;
    }

    public List<GroupProject> getProjects() {
        return projects;
    }

    public void setProjects(List<GroupProject> projects) {
        this.projects = projects;
    }

    public static Finder<Long, Group> getFind() {
        return find;
    }

    public static void setFind(Finder<Long, Group> find) {
        Group.find = find;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
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
