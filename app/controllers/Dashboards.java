package controllers;

import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by NRAM on 28/04/14.
 */
@Security.Authenticated(Secured.class)
public class Dashboards extends Controller {
    public static Result index() {
        Result result = null;
        User u = User.findByEmail(request().username());
        if(u != null){
            switch(u.userType.toString()){
                case "Teacher":
                    break;
                case "Student":
                    result =  DashboardStudent.index();
                    break;
                case "Administrator":
                    break;
                default:
                    // TODO: Aqui certamente temos que mandar para uma página de erro!!! (nunca deverá acontecer)
                    result = ok("BUUMMM");
            }
        }
        return result;
    }
}
