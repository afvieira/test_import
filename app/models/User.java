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
public class User extends Model {

    //Variáveis de instância
    @Id
    @Constraints.Min(10)
    private Long id;

    @Constraints.Required
    @Formats.NonEmpty
    @Column(unique = true, nullable = false)
    private String email;

    @Constraints.Required
    private String encrypted_password;

    @Constraints.Required
    private Date date_sign_up;

    //Construtores
    public User() {
    }

    //Propriedades

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public Date getDate_sign_up() {
        return date_sign_up;
    }
    public void setDate_sign_up(Date date_sign_up) {
        this.date_sign_up = date_sign_up;
    }

    //Equals, HashCode e toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (date_sign_up != null ? !date_sign_up.equals(user.date_sign_up) : user.date_sign_up != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (encrypted_password != null ? !encrypted_password.equals(user.encrypted_password) : user.encrypted_password != null)
            return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (encrypted_password != null ? encrypted_password.hashCode() : 0);
        result = 31 * result + (date_sign_up != null ? date_sign_up.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", encrypted_password='" + encrypted_password + '\'' +
                ", date_sign_up=" + date_sign_up +
                "} " + super.toString();
    }

    //Funcoes

    public static Finder<Long, User> find = new Finder( Long.class, User.class );

    public static void create(User user){
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
                .eq("password", password)
                .findUnique();
    }
}
