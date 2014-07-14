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

    public GroupProject(Project project) {
        this.project = project;
        this.State = StateOfProject.AT;
    }

    public GroupProject(Long id, Group group, Project project, String path, StateOfProject state) {
        this.id = id;
        this.group = group;
        this.project = project;
        this.path = path;
        State = state;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public StateOfProject getState() {
        return State;
    }

    public void setState(StateOfProject state) {
        State = state;
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
    public static Finder<Long, GroupProject> find = new Finder(
            Long.class, GroupProject.class
    );

    public static List<GroupProject> all() {
        return find.all();
    }

    public static GroupProject saveCourse(GroupProject groupProject, Long group_id){
        groupProject.group = Group.getById(group_id);
        groupProject.update();
        return groupProject;
    }

    public static GroupProject create(GroupProject groupProject){
        groupProject.save();
        return groupProject;
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }

    // TODO: Alterar o nome do método
    public static List<GroupProject> getLastAvaliations(String emailuser){
        return find.where()
                .eq("student.email", emailuser)
                .orderBy("lastUpdate desc")
                .findList();
    }

    public static GroupProject findById(Long id){
        return find.where()
                .eq("id", id)
                .findUnique();
    }

}