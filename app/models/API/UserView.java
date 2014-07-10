package models.API;

import models.User;

import java.util.Date;

public class UserView {
    public Long id;
    public String code;
    public String name;
    public Date birthdate;
    public String contact;
    public String email;
    public String encrypted_password;
    public Date date_sign_up;
    public String userType;

    public UserView(User u) {
        this.id = u.id;
        this.code = u.code;
        this.name = u.name;
        this.birthdate = u.birthdate;
        this.contact = u.contact;
        this.email = u.email;
        this.encrypted_password = u.encrypted_password;
        this.date_sign_up = u.date_sign_up;
        this.userType = u.userType;
    }
}
