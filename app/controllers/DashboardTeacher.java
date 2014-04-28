package controllers;

import models.Discipline;
import models.Milestone;
import models.Project;
import models.StudentMilestone;
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
                )
        );
    }
}
