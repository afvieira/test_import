package models;

import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by NRAM on 07/04/14.
 */
@Entity
public class Student extends User{

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

    @ManyToMany
    public List<Shift> shifts = new ArrayList<Shift>();

    public Student() {
    }

    public Student(String code, String name, Date birthdate, String contact, List<Shift> shifts) {
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

        Student student = (Student) object;

        if (!birthdate.equals(student.birthdate)) return false;
        if (!code.equals(student.code)) return false;
        if (!contact.equals(student.contact)) return false;
        if (!id.equals(student.id)) return false;
        if (!name.equals(student.name)) return false;
        if (!shifts.equals(student.shifts)) return false;

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
        return "Student{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", contact='" + contact + '\'' +
                ", shifts=" + shifts +
                '}';
    }

    public static Finder<Long, Student> find = new Finder(Long.class, Student.class);

    public static List<Student> all() {
        return find.all();
    }

    public static void create(Student student){
        student.save();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }
}
