package models;


import play.data.validation.Constraints;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
/**
 * Created by NRAM on 07/04/14.
 */
@Entity
public class Curso extends Model {

    @Id
    @Constraints.Min(10)
    private Long id;

    @Required
    private String code;

    @Required
    private String description;

    @OneToMany
    private List<Discipline> disciplinas;

    public Curso(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public Curso() {}

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

    public List<Discipline> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Discipline> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        Curso c = (Curso) object;

        if (!code.equals(c.code)) return false;
        if (!description.equals(c.description)) return false;
        if (!id.equals(c.id)) return false;

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

    public static Finder<Long, Curso> find = new Finder(
            Long.class, Curso.class
    );

    public static List<Curso> all() {
        return find.all();


//        find.where()
//                .ilike("name", "%" + filter + "%")
//                .orderBy(sortBy + " " + order)
//                .fetch("company")
//                .findPagingList(pageSize)
//                .setFetchAhead(false)
//                .getPage(page);
    }

    public static void create(Curso c){
        c.save();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }
}
