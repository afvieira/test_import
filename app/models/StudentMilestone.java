package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by NRAM on 23/04/14.
 */
@Entity
public class StudentMilestone extends Model {

    //Variaveis
    @Id
    @Constraints.Min(10)
    public Long id;

    @ManyToOne
    public User student;

    @ManyToOne
    public Milestone milestone;

    public Integer avaliation;

    public String path;

    public String privateComment;

    public String publicCommentTeacher;

    public String publicCommentStudent;

    @Constraints.Required
    public Date lastUpdate;

    @Constraints.Required
    public boolean closed;

    //Construtor
    public StudentMilestone() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public Integer getAvaliation() {
        return avaliation;
    }

    public void setAvaliation(Integer avaliation) {
        this.avaliation = avaliation;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPrivateComment() {
        return privateComment;
    }

    public void setPrivateComment(String privateComment) {
        this.privateComment = privateComment;
    }

    public String getPublicCommentTeacher() {
        return publicCommentTeacher;
    }

    public void setPublicCommentTeacher(String publicCommentTeacher) {
        this.publicCommentTeacher = publicCommentTeacher;
    }

    public String getPublicCommentStudent() {
        return publicCommentStudent;
    }

    public void setPublicCommentStudent(String publicCommentStudent) {
        this.publicCommentStudent = publicCommentStudent;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public static Finder<Long, StudentMilestone> getFind() {
        return find;
    }

    public static void setFind(Finder<Long, StudentMilestone> find) {
        StudentMilestone.find = find;
    }

    //ToString
    @Override
    public String toString() {
        return "StudentMilestone{" +
                "id=" + id +
                ", student=" + student +
                ", milestone=" + milestone +
                ", avaliation=" + avaliation +
                ", path='" + path + '\'' +
                ", privateComment='" + privateComment + '\'' +
                ", publicCommentTeacher='" + publicCommentTeacher + '\'' +
                ", publicCommentStudent='" + publicCommentStudent + '\'' +
                ", closed='" + closed + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                "} " + super.toString();
    }


    //DataBase
    public static Finder<Long, StudentMilestone> find = new Finder(
            Long.class, StudentMilestone.class
    );

    public static List<StudentMilestone> all() {
        return find.all();
    }

    public static void create(StudentMilestone studentmilestone){
        studentmilestone.save();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }

    public static List<StudentMilestone> getLastAvaliations(String emailuser){
        return find.where()
                .eq("student.email", emailuser)
                .isNotNull("avaliation")
                .orderBy("lastUpdate desc")
                .findList();
    }

    /**
     * Obter a lista de  StudentMilestone pelo User e id do projeto
     * @param emailUser
     * @param id_project
     * @return
     */
    public static List<StudentMilestone> getByProject(String emailUser, Long id_project){
        return find
                .fetch("milestone")
                .where()
                .eq("milestone.project.id", id_project)
                .eq("student.email",emailUser)
                .orderBy("milestone.endDate DESC")
                .findList();

    }

    /**
     * Obter o StudentMilestone pelo User e id da etapa
     * @param emailUser
     * @param id_milestone
     * @return
     */
    public static StudentMilestone getByUserMilestone(String emailUser, Long id_milestone){
        return find
                .where()
                .eq("milestone.id", id_milestone)
                .eq("student.email",emailUser)
                .findUnique();
    }

    public static List<StudentMilestone> getByMilestone(Long id_milestone){
        return find
                .where()
                .eq("milestone.id", id_milestone)
                .findList();
    }

    public static StudentMilestone getByStudentMilestone(Long id_milestone, Long id_student){
        return find
                .where()
                .eq("milestone.id", id_milestone)
                .eq("student.id", id_student)
                .findUnique();
    }

    public static List<StudentMilestone> getLastAvaliationsByDiscipline(String emailUser, Collection<Long> project_id){
        return find
                .fetch("milestone")
                .where()
                .eq("student.email", emailUser)
                .in("milestone.project.id", project_id)
                .isNotNull("avaliation")
                .orderBy("lastUpdate desc")
                .findList();
    }
}
