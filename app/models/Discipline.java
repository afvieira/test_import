package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.*;

/**
 * Created by NRAM on 07/04/14.
 */
@Entity
public class Discipline extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @ManyToOne
    @Constraints.Required
    public Course course;

    @Constraints.Required
    public String code;

    @Constraints.Required
    public Integer course_year;

    @Constraints.Required
    public Integer year;

    @OneToMany
    public List<Shift> shifts;

    public Discipline(Course course, String code, Integer course_year, Integer year, List<Shift> shifts) {
        this.course = course;
        this.code = code;
        this.course_year = course_year;
        this.year = year;
        this.shifts = shifts;
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

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        Discipline that = (Discipline) object;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (course != null ? !course.equals(that.course) : that.course != null) return false;
        if (course_year != null ? !course_year.equals(that.course_year) : that.course_year != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (shifts != null ? !shifts.equals(that.shifts) : that.shifts != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (course_year != null ? course_year.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (shifts != null ? shifts.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final java.lang.StringBuffer sb = new java.lang.StringBuffer("Discipline{");
        sb.append("id=").append(id);
        sb.append(", course=").append(course);
        sb.append(", code='").append(code).append('\'');
        sb.append(", course_year=").append(course_year);
        sb.append(", year=").append(year);
        sb.append(", shifts=").append(shifts);
        sb.append('}');
        return sb.toString();
    }

    public static Finder<Long, Discipline> find = new Finder(
            Long.class, Discipline.class
    );

    public static List<Discipline> all() {
        return find.all();
    }

    public static List<Discipline> allByCourse(Long id_course){

    }

    public static void create(Discipline discipline){
        discipline.save();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }
}
