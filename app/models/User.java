package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import java.util.*;
import javax.persistence.Id;

/**
 * Created by NRAM on 07/04/14.
 */
public class User extends Model {

    @Id
    @Constraints.Min(10)
    private Long id;

    @Constraints.Required
    private String email;

    @Constraints.Required
    private String encrypted_password;

    @Constraints.Required
    private Date date_sign_up;

    public User() {
    }

    public User(String email, String encrypted_password, Date date_sign_up) {
        this.email = email;
        this.encrypted_password = encrypted_password;
        this.date_sign_up = date_sign_up;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        User user = (User) object;

        if (!date_sign_up.equals(user.date_sign_up)) return false;
        if (!email.equals(user.email)) return false;
        if (!encrypted_password.equals(user.encrypted_password)) return false;
        if (!id.equals(user.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + encrypted_password.hashCode();
        result = 31 * result + date_sign_up.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final java.lang.StringBuffer sb = new java.lang.StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", email='").append(email).append('\'');
        sb.append(", encrypted_password='").append(encrypted_password).append('\'');
        sb.append(", date_sign_up=").append(date_sign_up);
        sb.append('}');
        return sb.toString();
    }


    public static Finder<Long, User> find = new Finder( Long.class, User.class );

    public static void create(User user){
        user.save();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }
}
