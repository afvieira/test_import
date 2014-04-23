package models;


import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by NRAM on 07/04/14.
 */

@Entity
public class Course extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String code;

    @Constraints.Required
    public String description;

    @OneToMany
    public List<Discipline> disciplines;

    public Course() {}

    public Course(String code, String description, List<Discipline> disciplines) {
        this.code = code;
        this.description = description;
        this.disciplines = disciplines;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        Course course = (Course) object;

        if (!code.equals(course.code)) return false;
        if (!description.equals(course.description)) return false;
        if (!disciplines.equals(course.disciplines)) return false;
        if (!id.equals(course.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (disciplines != null ? disciplines.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new java.lang.StringBuffer("Course{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", disciplines=").append(disciplines);
        sb.append('}');
        return sb.toString();
    }

    public static Finder<Long, Course> find = new Finder(
            Long.class, Course.class
    );

    public static List<Course> all() {
        return find.all();
    }

    public static void create(Course course){
        course.save();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }
}
