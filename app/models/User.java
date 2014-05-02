package models;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by NRAM on 07/04/14.
 */
@Entity
@Table(name="general_user") // Palavra reservada "User"
public class User extends Model {

    public enum UserType{
        Student,        //Users do tipo estudante
        Teacher,        //Users do tipo Professor
        Administrator   //Users do tipo Administrador
    }

    //Variáveis de instância
    @Id
    @Constraints.Min(10)
    public Long id;

    public String code;

    @Constraints.Required(message = "O nome é obrigatório.")
    public String name;

    @Constraints.Required(message = "A data de nascimento é obrigatória.")
    public Date birthdate;

    public String contact;

    @Constraints.Required(message = "O email é obrigatório.")
    @Constraints.Email(message = "O formato do email está incorreto.")
    @Formats.NonEmpty
    @Column(unique = true, nullable = false)
    public String email;

    @Constraints.Required(message = "A password é obrigatória.")
    public String encrypted_password;

    @ManyToMany
    public List<Discipline> disciplines;

    @OneToMany
    public List<StudentProject> projects;

    @ManyToMany
    public List<Group> group;

    @ManyToMany
    public List<Shift> shifts;

    @OneToMany
    public List<StudentMilestone> milestones;

    public Date date_sign_up;

    @Constraints.Required
    public String userType;

    //Construtores
    public User() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncrypted_password() {
        return encrypted_password;
    }

    public void setEncrypted_password(String encrypted_password) {
        this.encrypted_password = encrypted_password;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public List<StudentProject> getProjects() {
        return projects;
    }

    public void setProjects(List<StudentProject> projects) {
        this.projects = projects;
    }

    public List<Group> getGroup() {
        return group;
    }

    public void setGroup(List<Group> group) {
        this.group = group;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }

    public List<StudentMilestone> getMilestones() {
        return milestones;
    }

    public void setMilestones(List<StudentMilestone> milestones) {
        this.milestones = milestones;
    }

    public Date getDate_sign_up() {
        return date_sign_up;
    }

    public void setDate_sign_up(Date date_sign_up) {
        this.date_sign_up = date_sign_up;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public static Finder<Long, User> getFind() {
        return find;
    }

    public static void setFind(Finder<Long, User> find) {
        User.find = find;
    }

    //ToString
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", encrypted_password='" + encrypted_password + '\'' +
                ", disciplines=" + disciplines +
                ", group=" + group +
                ", shifts=" + shifts +
                ", milestones=" + milestones +
                ", projects=" + projects +
                ", userType=" + userType +
                ", date_sign_up=" + date_sign_up +
                "} " + super.toString();
    }

    //DataBase
    public static Finder<Long, User> find = new Finder( Long.class, User.class );

    public static void create(User user){
        user.date_sign_up = new Date();
        user.save();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }

    /**
     * Retrieve all users.
     */
    public static List<User> findAll() {
        return find.all();
    }

    /**
     * Retrieve a User from email.
     */
    public static User findByEmail(String email) {
        return find.where().eq("email", email).findUnique();
    }

    /**
     * Authenticate a User.
     */
    public static User authenticate(String email, String password) {
        return find.where()
                .eq("email", email)
                .eq("encrypted_password", password)
                .findUnique();
    }
}
