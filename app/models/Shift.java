package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.util.*;

/**
 * Created by NRAM on 07/04/14.
 */
public class Shift extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String code;

    @Constraints.Required
    public String description;

    @ManyToMany
    public List<Student> students = new ArrayList<Student>();

    public Shift(String code, String description, List<Student> students) {
        this.code = code;
        this.description = description;
        this.students = students;
    }

    public Shift() {
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        Shift shift = (Shift) object;

        if (!code.equals(shift.code)) return false;
        if (!description.equals(shift.description)) return false;
        if (!id.equals(shift.id)) return false;
        if (!students.equals(shift.students)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + students.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", students=" + students +
                '}';
    }

    public static Finder<Long, Shift> find = new Finder(
            Long.class, Shift.class
    );

    public static List<Shift> all() {
        return find.all();
    }

    public static void create(Shift shift){
        shift.save();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }
}
