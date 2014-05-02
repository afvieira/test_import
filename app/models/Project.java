package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by NRAM on 23/04/14.
 */
@Entity
public class Project extends Model {

    //Variaveis
    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    @ManyToOne
    public Discipline discipline;

    @Constraints.Required
    public String code;

    @Constraints.Required
    public String title;

    @Constraints.Required
    public String description;

    @Constraints.Required
    public Date startDate;

    @Constraints.Required
    public Date endDate;

    @Constraints.Required
    public Date creationDate;

    @OneToMany
    public List<Milestone> milestones;

    @OneToMany
    public List<GroupProject> groups;

    @OneToMany
    public List<StudentProject> students;

    //Construtor
    public Project() {
    }

    //ToString


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Project{");
        sb.append("id=").append(id);
        sb.append(", discipline=").append(discipline);
        sb.append(", code='").append(code).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", creationDate=").append(creationDate);
        sb.append(", milestones=").append(milestones);
        sb.append(", groups=").append(groups);
        sb.append(", students=").append(students);
        sb.append('}');
        return sb.toString();
    }

    //DataBase
    public static Finder<Long, Project> find = new Finder(
            Long.class, Project.class
    );

    public static List<Project> all() {
        return find.all();
    }

    public static void create(Project project){
        project.save();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }

    public static List<Project> getByCreatedDate(String emailuser){
        return find
                .fetch("students")
                .where()
                .eq("students.student.email", emailuser)
                .orderBy("creationDate desc")
                .findList();
    }

    /**
     * Lista de Projetos por Disciplina e User
     * @param emailUser
     * @param id_discipline
     * @return List<Project>
     */
    public static List<Project> getAllByUserDiscipline(String emailUser, Long id_discipline){
        return find
                .fetch("students")
                .where()
                .eq("discipline.id", id_discipline)
                .eq("students.student.email", emailUser)
                .orderBy("creationDate desc")
                .findList();
    }

    /**
     * Lista de Projetos de uma disciplina
     * @param id_discipline
     * @return List<Project>
     */
    public static List<Project> getAllByDiscipline(Long id_discipline){
        return find
                .where()
                .eq("discipline.id", id_discipline)
                .orderBy("creationDate desc")
                .findList();
    }
}
