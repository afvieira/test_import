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
public class Milestone extends Model {

    //Variaveis
    @Id
    @Constraints.Min(10)
    public Long id;

    @ManyToOne
    public Project project;

    @Constraints.Required
    public String code;

    @Constraints.Required
    public String description;

    @Constraints.Required
    public Integer number;

    @Constraints.Required
    public Date startDate;

    @Constraints.Required
    public Date endDate;

    @Constraints.Required
    public String path;

    @Constraints.Required
    public Integer percentage;

    @OneToMany
    public List<GroupMilestone> groups;

    @OneToMany
    public List<StudentMilestone> students;

    //Construtor
    public Milestone() {
    }

    //ToString
    @Override
    public String toString() {
        return "Milestone{" +
                "id=" + id +
                ", project=" + project +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", number=" + number +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", path='" + path + '\'' +
                ", percentage=" + percentage +
                ", groups=" + groups +
                ", students=" + students +
                "} " + super.toString();
    }

    //DataBase
    public static Finder<Long, Milestone> find = new Finder(
            Long.class, Milestone.class
    );

    public static List<Milestone> findByStudent(String emailuser){
        return find.fetch("students").where()
                    .eq("students.student.email", emailuser)
                .findList();
    }

    public static List<Milestone> all() {
        return find.all();
    }

    public static void create(Milestone milestone){
        milestone.save();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }


    /**
     * Próximas etapas a entregar de um dado utilizador
     * @param emailuser
     * @return List<Milestone>
     */
    public static List<Milestone> nextDeliveryByUser(String emailuser) {
        return find.fetch("students").where()
                .ge("endDate", new Date())
                .eq("students.student.email", emailuser)
                .orderBy("endDate ASC")
                .findList();
    }

    /**
     * Etapas não entregues por estudante
     * @param emailUser
     * @return List<Milestone>
     */
    public static List<Milestone> notDeliveryByUser(String emailUser){
        return find
                .fetch("students")
                .where()
                .lt("endDate", new Date())
                .isNull("path")
                .eq("students.student.email", emailUser)
                .orderBy("endDate ASC")
                .findList();
    }

    /**
     * Próximas etapas para entrega de uma dada disciplina e utilizador
     * @param emailUser
     * @param id_discipline
     * @return List<Milestone>
     */
    public static List<Milestone> nextDeliveryByUserDiscipline(String emailUser, Long id_discipline){
        return find
                .fetch("project")
                .fetch("students")
                .where()
                .ge("endDate", new Date())
                .eq("students.student.email", emailUser)
                .eq("project.discipline.id",id_discipline)
                .orderBy("endDate ASC")
                .findList();
    }

    /**
     * Etapas não entregues de uma dada disciplina e utilizador
     * @param emailUser
     * @param id_discipline
     * @return Lista de Milestones
     */
    public static List<Milestone> notDeliveryByUserDiscipline(String emailUser, Long id_discipline){
        return find
                .fetch("project")
                .fetch("students")
                .where()
                .lt("endDate", new Date())
                .isNull("path")
                .eq("students.student.email", emailUser)
                .eq("project.discipline.id",id_discipline)
                .orderBy("endDate ASC")
                .findList();
    }

    /**
     * Etapas não entregues de uma dada disciplina e utilizador
     * @param emailUser
     * @param id_discipline
     * @return Lista de Milestones
     */
    public static List<Milestone> allMilestoneByUserDiscipline(String emailUser, Long id_discipline){
        return find
                .fetch("project")
                .fetch("students")
                .where()
                .eq("students.student.email", emailUser)
                .eq("project.discipline.id",id_discipline)
                .orderBy("endDate ASC")
                .findList();
    }

    /**
     * Todas as etapas de um projeto
     * @param id_project
     * @return Lista de Milestones
     */
    public static List<Milestone> allMilestonesByProject(Long id_project){
        return find
                .where()
                .eq("project.id",id_project)
                .orderBy("endDate ASC")
                .findList();
    }

    /**
     * Próximas Etapas de um projeto
     * @param id_project
     * @return List<Milestone>
     */
    public static List<Milestone> nextDeliveriesByProject(Long id_project){
        return find
                .where()
                .eq("project.id",id_project)
                .ge("endDate", new Date())
                .orderBy("endDate ASC")
                .findList();
    }


    /**
     * Etapas não entregues de um projeto
     * @param id_project
     * @return List<Milestone>
     */
    public static List<Milestone> notDeliveriesByUserProject(String emailUser, Long id_project){
        return find
                .fetch("students")
                .where()
                .eq("project.id",id_project)
                .eq("students.student.email", emailUser)
                .ge("endDate", new Date())
                .orderBy("endDate ASC")
                .findList();
    }

    /**
     * Dados de uma Etapa
     * @param id_milestone
     * @return Milestone
     */
    public static Milestone getById(Long id_milestone){
        return find
                .where()
                .eq("id", id_milestone)
                .findUnique();
    }

}
