package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by NRAM on 01/05/14.
 */
public class Dashboards extends Controller {


    /**
     * Display the Dashboard
     *
     * @return
     */
    @Security.Authenticated(Secured.class)
    public static Result index() {
        Result result = null;
        User u = User.findByEmail(request().username());
        if(u != null){
            switch(u.userType){
                case "Teacher":
                    result = DashboardTeacher.index();
                    break;
                case "Student":
                    result =  DashboardStudent.index();
                    break;
                case "Administrator":
                    break;
                default:
                    // TODO: Aqui certamente temos que mandar para uma página de erro!!! (nunca deverá acontecer)
                    result = TODO;
            }
        }
        return result;
    }


    @Security.Authenticated(Secured.class)
    public static Result disciplines() {
        Result result = null;
        User u = User.findByEmail(request().username());
        if(u != null){
            switch(u.userType){
                case "Teacher":
                    result =  DashboardTeacher.disciplines();
                    break;
                case "Student":
                    result =  DashboardStudent.disciplines();
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

    @Security.Authenticated(Secured.class)
    public static Result showDiscipline(Long id) {
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result createDiscipline() {
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result deleteDiscipline(Long id) {
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result projects() {
        Result result = null;
        User u = User.findByEmail(request().username());
        if(u != null){
            switch(u.userType){
                case "Teacher":
                    result =  DashboardTeacher.projects();
                    break;
                case "Student":
                    result =  DashboardStudent.projects();
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


    @Security.Authenticated(Secured.class)
    public static Result showProject(Long id) {
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result createProject() {
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result deleteProject(Long id) {
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result milestones() {
        Result result = null;
        User u = User.findByEmail(request().username());
        if(u != null){
            switch(u.userType){
                case "Teacher":
                    result =  DashboardTeacher.milestones();
                    break;
                case "Student":
                    result =  DashboardStudent.milestones();
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

    @Security.Authenticated(Secured.class)
    public static Result showMilestone(Long id) {
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result createMilestone() {
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result deleteMilestone(Long id) {
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result avaliation() {
        Result result = null;
        User u = User.findByEmail(request().username());
        if(u != null){
            switch(u.userType){
                case "Teacher":
                    result =  DashboardTeacher.avaliation();
                    break;
                case "Student":
                    result =  DashboardStudent.avaliation();
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

    @Security.Authenticated(Secured.class)
    public static Result showAvaliation(Long id) {
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result createAvaliation() {
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result deleteAvaliation(Long id) {
        return TODO;
    }

}
