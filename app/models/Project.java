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
    public Long id;

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

    public Date creationDate;

    @ManyToOne
    public User createdBy;

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
        sb.append(", createdBy=").append(createdBy);
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

    public static Project create(Project project, Long id_discipline, String emailUser){
        project.createdBy = User.findByEmail(emailUser);
        project.creationDate = new Date();
        project.discipline = Discipline.find.ref(id_discipline);
        project.save();
        return project;
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }

    public static void update(Project project, Long discipline_id){
        project.discipline = Discipline.find.ref(discipline_id);
        project.update();
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

    /**
     * Informação de um dado projeto
     * @param id_project
     * @return Project
     */
    public static Project getById(Long id_project){
        return find
                .where()
                .eq("id", id_project)
                .findUnique();
    }

    /**
     * Todos os projetos criado por um dado professor
     * @param emailUser
     * @return
     */
    public static List<Project> getByTeacher(String emailUser){
        return find
                .where()
                .eq("createdBy.email", emailUser)
                .orderBy("creationDate DESC")
                .findList();
    }

    // TODO: TESTAR [BEGIN]
    /**
     * Todos os projetos das disciplinas lecionadas por um dado professor
     * @param emailUser
     * @return
     */
    public static List<Project> getByTeacherDisciplines(String emailUser){
        return find
                .fetch("discipline").fetch("discipline")
                .where()
                .eq("discipline.users.email", emailUser)
                .orderBy("creationDate desc")
                .findList();
    }
    // TODO: TESTAR [END]
}
