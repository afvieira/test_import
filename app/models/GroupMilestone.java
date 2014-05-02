package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

/**
 * Created by NRAM on 23/04/14.
 */
@Entity
public class GroupMilestone extends Model {

    //Variaveis
    @Id
    @Constraints.Min(10)
    public Long id;

    @ManyToOne
    public Group group;

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
    public GroupMilestone() {
    }

    //ToString
    @Override
    public String toString() {
        return "GroupMilestone{" +
                "id=" + id +
                ", group=" + group +
                ", milestone=" + milestone +
                ", avaliation=" + avaliation +
                ", path='" + path + '\'' +
                ", privateComment='" + privateComment + '\'' +
                ", publicCommentTeacher='" + publicCommentTeacher + '\'' +
                ", publicCommentStudent='" + publicCommentStudent + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", closed='" + closed + '\'' +
                "} " + super.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public static Finder<Long, GroupMilestone> getFind() {
        return find;
    }

    public static void setFind(Finder<Long, GroupMilestone> find) {
        GroupMilestone.find = find;
    }

    //DataBase
    public static Finder<Long, GroupMilestone> find = new Finder(
            Long.class, GroupMilestone.class
    );

    public static List<GroupMilestone> all() {
        return find.all();
    }

    public static void create(GroupMilestone groupmilestone){
        groupmilestone.save();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }

    public static GroupMilestone getByGroupMilestone(Long id_milestone, Long id_group){
        return find
                .where()
                .eq("milestone.id", id_milestone)
                .eq("group.id", id_group)
                .findUnique();
    }

}
