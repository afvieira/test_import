package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Id;
import java.util.*;

/**
 * Created by NRAM on 07/04/14.
 */
public class Discipline extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public Course course;

    @Constraints.Required
    public String code;

    @Constraints.Required
    public Integer course_year;

    @Constraints.Required
    public Integer year;

    public Discipline(Course course, String code, Integer course_year, Integer year) {
        this.course = course;
        this.code = code;
        this.course_year = course_year;
        this.year = year;
    }

    public Discipline() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCourse_year() {
        return course_year;
    }

    public void setCourse_year(Integer course_year) {
        this.course_year = course_year;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        Discipline that = (Discipline) object;

        if (!code.equals(that.code)) return false;
        if (!course.equals(that.course)) return false;
        if (!course_year.equals(that.course_year)) return false;
        if (!id.equals(that.id)) return false;
        if (!year.equals(that.year)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + course.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + course_year.hashCode();
        result = 31 * result + year.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "id=" + id +
                ", course=" + course +
                ", code='" + code + '\'' +
                ", course_year=" + course_year +
                ", year=" + year +
                '}';
    }

    public static Finder<Long, Discipline> find = new Finder(
            Long.class, Discipline.class
    );

    public static List<Discipline> all() {
        return find.all();
    }

    public static void create(Discipline discipline){
        discipline.save();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }
}
