package models;


import java.util.*;

import play.data.validation.Constraints;
import play.data.validation.Constraints.*;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * Created by NRAM on 07/04/14.
 */

@Entity
public class Course extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Required
    public String code;

    @Required
    public String description;

    public Course(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public Course() {}

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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        Course course = (Course) object;

        if (!code.equals(course.code)) return false;
        if (!description.equals(course.description)) return false;
        if (!id.equals(course.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
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
