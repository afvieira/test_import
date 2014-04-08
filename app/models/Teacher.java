package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import java.util.*;
import javax.persistence.Id;

/**
 * Created by NRAM on 07/04/14.
 */
public class Teacher extends User {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String code;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public Date birthdate;

    @Constraints.Required
    public String contact;

    @Constraints.Required
    public List<Discipline> shifts = new ArrayList<Discipline>();

    public Teacher() {
    }

    public Teacher(String code, String name, Date birthdate, String contact, List<Discipline> shifts) {
        this.code = code;
        this.name = name;
        this.birthdate = birthdate;
        this.contact = contact;
        this.shifts = shifts;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<Discipline> getShifts() {
        return shifts;
    }

    public void setShifts(List<Discipline> shifts) {
        this.shifts = shifts;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        Teacher teacher = (Teacher) object;

        if (!birthdate.equals(teacher.birthdate)) return false;
        if (!code.equals(teacher.code)) return false;
        if (!contact.equals(teacher.contact)) return false;
        if (!id.equals(teacher.id)) return false;
        if (!name.equals(teacher.name)) return false;
        if (!shifts.equals(teacher.shifts)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + birthdate.hashCode();
        result = 31 * result + contact.hashCode();
        result = 31 * result + shifts.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", contact='" + contact + '\'' +
                ", shifts=" + shifts +
                '}';
    }

    public static Finder<Long, Teacher> find = new Finder(
            Long.class, Teacher.class
    );

    public static List<Teacher> all() {
        return find.all();
    }

    public static void create(Teacher teacher){
        teacher.save();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }
}
