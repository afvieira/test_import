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
        return routing(
                DashboardStudent.index(),
                DashboardTeacher.index(),
                TODO);
    }

    /**
     * Método para não duplicar código em cada um dos Métodos
     * @param callMethodStudent
     * @param callMethodTeacher
     * @param callMethodAdmininstrator
     * @return Result
     */
    private static Result routing(Result callMethodStudent, Result callMethodTeacher, Result callMethodAdmininstrator){
        Result result = null;
        User u = User.findByEmail(request().username());
        if(u != null){
            switch(u.userType){
                case "Teacher":
                    result = callMethodTeacher;
                    break;
                case "Student":
                    result =  callMethodStudent;
                    break;
                case "Administrator":
                    result =  callMethodAdmininstrator;
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
        return routing(
                DashboardStudent.disciplines(),
                DashboardTeacher.disciplines(),
                TODO);
    }

    @Security.Authenticated(Secured.class)
    public static Result showDiscipline(Long id) {
        return routing(
                DashboardStudent.showDiscipline(id),
                DashboardTeacher.showDiscipline(id),
                TODO);
    }

    @Security.Authenticated(Secured.class)
    public static Result createDiscipline() {
        return routing(
                TODO,
                TODO,
                TODO);
    }

    @Security.Authenticated(Secured.class)
    public static Result deleteDiscipline(Long id) {
        return routing(
                TODO,
                TODO,
                TODO);
    }

    @Security.Authenticated(Secured.class)
    public static Result projects() {
        return routing(
                DashboardStudent.projects(),
                DashboardTeacher.projects(),
                TODO);
    }


    @Security.Authenticated(Secured.class)
    public static Result showProject(Long id) {
        return routing(
                DashboardStudent.showProject(id),
                DashboardTeacher.showProject(id),
                TODO);
    }

    @Security.Authenticated(Secured.class)
    public static Result createProject() {
        return routing(
                DashboardStudent.createProject(),
                DashboardTeacher.createProject(),
                TODO);
    }

    @Security.Authenticated(Secured.class)
    public static Result deleteProject(Long id) {
        return routing(
                DashboardStudent.deleteProject(id),
                DashboardTeacher.deleteProject(id),
                TODO);
    }

    @Security.Authenticated(Secured.class)
    public static Result milestones(Long id_project) {
        return routing(
                DashboardStudent.milestones(id_project),
                DashboardTeacher.milestones(id_project),
                TODO);
    }

    @Security.Authenticated(Secured.class)
    public static Result showMilestone(Long id_project, Long id_milestone) {
        return routing(
                DashboardStudent.showMilestone(id_project,id_milestone),
                DashboardTeacher.showMilestone(id_project,id_milestone),
                TODO);
    }

    @Security.Authenticated(Secured.class)
    public static Result createMilestone(Long id_project) {
        return routing(
                DashboardStudent.createMilestone(id_project),
                DashboardTeacher.createMilestone(id_project),
                TODO);
    }

    @Security.Authenticated(Secured.class)
    public static Result deleteMilestone(Long id_project, Long id_milestone) {
        return routing(
                DashboardStudent.deleteMilestone(id_project,id_milestone),
                DashboardTeacher.deleteMilestone(id_project,id_milestone),
                TODO);
    }

    @Security.Authenticated(Secured.class)
    public static Result avaliations(Long id_project,Long id_milestone) {
        return routing(
                DashboardStudent.avaliations(id_project,id_milestone),
                DashboardTeacher.avaliations(id_project,id_milestone),
                TODO);
    }

    @Security.Authenticated(Secured.class)
    public static Result showAvaliation(Long id_project, Long id_milestone, Long id_avaliation) {
        return routing(
                DashboardStudent.showAvaliation(id_project,id_milestone,id_avaliation),
                DashboardTeacher.showAvaliation(id_project,id_milestone,id_avaliation),
                TODO);
    }

    @Security.Authenticated(Secured.class)
    public static Result createAvaliation(Long id_project,Long id_milestone) {
        return routing(
                DashboardStudent.createAvaliation(id_project,id_milestone),
                DashboardTeacher.createAvaliation(id_project,id_milestone),
                TODO);
    }

    @Security.Authenticated(Secured.class)
    public static Result deleteAvaliation(Long id_project,Long id_milestone, Long id_avaliation) {
        return routing(
                DashboardStudent.deleteAvaliation(id_project,id_milestone,id_avaliation),
                DashboardTeacher.deleteAvaliation(id_project,id_milestone,id_avaliation),
                TODO);
    }

}
