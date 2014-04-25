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

    //Variaveis
    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String code;

    @Constraints.Required
    public String description;

    @OneToMany
    public List<Discipline> disciplines;

    //construtor
    public Course() {}

    //ToString
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

    //Data Base
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
