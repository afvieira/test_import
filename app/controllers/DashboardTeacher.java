package controllers;

import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by NRAM on 28/04/14.
 */
public class DashboardTeacher extends Controller {

    /**
     * Display the Dashboard
     *
     * @return
     */
    @Security.Authenticated(Secured.class)
    public static Result index() {
        return ok(
                views.html.Dashboard.dashboardteacher.render(
                        // TODO: Página do professor
                        User.findByEmail(request().username()),
                        Discipline.findByUser(request().username()), // Disciplinas Lecionadas
                        Milestone.findByStudent(request().username()),
                        Project.getByCreatedDate(request().username()),
                        StudentMilestone.getLastAvaliations(request().username())
                )
        );
    }
}
