package controllers;

import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by NRAM on 28/04/14.
 */
public class DashboardTeacher extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result index() {
        return ok(
                views.html.Dashboard.Teacher.dashboard.render(
                        // TODO: Página do professor
                        User.findByEmail(request().username()),
                        Discipline.findByUser(request().username()), // Disciplinas Lecionadas
                        Milestone.findByStudent(request().username()),
                        Project.getByCreatedDate(request().username()),
                        StudentMilestone.getLastAvaliations(request().username())
                )
        );
    }

    @Security.Authenticated(Secured.class)
    public static Result disciplines() {
        return TODO;
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
        return TODO;
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
        return TODO;
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
        return TODO;
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
