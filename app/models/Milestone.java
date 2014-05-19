package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Collection;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public List<GroupMilestone> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupMilestone> groups) {
        this.groups = groups;
    }

    public List<StudentMilestone> getStudents() {
        return students;
    }

    public void setStudents(List<StudentMilestone> students) {
        this.students = students;
    }

    public static Finder<Long, Milestone> getFind() {
        return find;
    }

    public static void setFind(Finder<Long, Milestone> find) {
        Milestone.find = find;
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

    public static void create(Milestone milestone, Long id_project){
        milestone.project = Project.find.ref(id_project);
        milestone.save();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }


    public static void removeGroup(Long id_milestone, Long id_group){
        Milestone m = Milestone.find.setId(id_milestone).fetch("groups", "id").findUnique();
        m.groups.remove(
                GroupMilestone.getByGroupMilestone(id_milestone, id_group)
        );
        m.saveManyToManyAssociations("groups");
    }

    public static void removeStudent(Long id_milestone, Long id_student){
        Milestone m = Milestone.find.setId(id_milestone).fetch("students", "id").findUnique();
        m.students.remove(
                StudentMilestone.getByStudentMilestone(id_milestone, id_student)
        );
        m.saveManyToManyAssociations("students");
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

    /**
     * Todas as milestones dos projetos de um dado professor
     * @param emailUser
     * @return
     */
    public static List<Milestone> getAllMilestoneByTeacher(String emailUser){
        return find
                .fetch("project")
                .where()
                .eq("project.createdBy.email", emailUser)
                .orderBy("endDate ASC")
                .findList();
    }

    /**
     * Todas as milestones dos projetos de um dado professor numa disciplina
     * @param emailUser
     * @return
     */
    public static List<Milestone> getAllMilestoneByTeacherDiscipline(String emailUser, Long id_discipline){
        return find
                .fetch("project")
                .where()
                .eq("project.discipline.id", id_discipline)
                .eq("project.createdBy.email", emailUser)
                .orderBy("endDate ASC")
                .findList();
    }

    /**
     * Próximas milestones dos projetos de um dado professor numa disciplina
     * @param emailUser
     * @param id_discipline
     * @return
     */
    public static List<Milestone> nextDeliveryMilestoneByTeacherDiscipline(String emailUser, Long id_discipline){
        return find
                .fetch("project")
                .where()
                .ge("endDate", new Date())
                .eq("project.discipline.id", id_discipline)
                .eq("project.createdBy.email", emailUser)
                .orderBy("endDate ASC")
                .findList();
    }

    /**
     * Próximas milestones dos projetos de uma disciplina
     * @param id_discipline
     * @return
     */
    public static List<Milestone> nextDeliveryMilestoneByDiscipline(Long id_discipline){
        return find
                .fetch("project")
                .where()
                .ge("endDate", new Date())
                .eq("project.discipline.id", id_discipline)
                .orderBy("endDate ASC")
                .findList();
    }
    /**
     * Milestones dos projetos de uma disciplina
     * @param id_discipline
     * @return
     */
    public static List<Milestone> allMilestonesByDiscipline(Long id_discipline){
        return find
                .fetch("project")
                .where()
                .eq("project.discipline.id", id_discipline)
                .orderBy("endDate ASC")
                .findList();
    }

    // TODO: Verificar se funciona [BEGIN]

    /**
     * Vai buscar todas as milestones dos projetos de todas as disciplinas que lecciona
     * @param emailUser
     * @return List<Milestone>
     */
    public static List<Milestone> getAllMilestoneByAllDisciplinesTeacher(String emailUser){
        return find
                .fetch("project")
                .where()
                .eq("project.discipline.users.email", emailUser)
                .orderBy("endDate ASC")
                .findList();
    }

    /**
     * Vai buscar as próximas milestones a entregar dos projetos de todas as disciplinas que lecciona
     * @param emailUser
     * @return List<Milestone>
     */
    public static List<Milestone> nextDeliveriesMilestoneByAllDisciplinesTeacher(String emailUser){
        return find
                .fetch("project")
                .where()
                .ge("endDate", new Date())
                .eq("project.discipline.users.email", emailUser)
                .orderBy("endDate ASC")
                .findList();
    }


    public static List<Milestone> nextDeliveriesMilestonesByProjectsId(Collection<Long> project_id){
        return find
                .where()
                .ge("endDate", new Date())
                .in("project.id", project_id)
                .orderBy("endDate ASC")
                .findList();
    }


    // TODO: Verificar se funciona [END]

}
