package controllers;

import models.Discipline;
import models.Milestone;
import models.Project;
import models.StudentMilestone;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by NRAM on 26/04/14.
 */
public class DashboardStudent extends Controller {

    /**
     * Display the Dashboard
     *
     * @return
     */
    @Security.Authenticated(Secured.class)
    public static Result index() {
        return ok(
                views.html.Dashboard.dashboard.render(
                        Discipline.findByStudent(request().username()),
                        Milestone.findByStudent(request().username()),
                        Project.getByCreatedDate(request().username()),
                        StudentMilestone.getLastAvaliations(request().username())
                )
        );
    }
}
