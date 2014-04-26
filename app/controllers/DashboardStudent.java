package controllers;

import models.Discipline;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by NRAM on 26/04/14.
 */
@Security.Authenticated(Secured.class)
public class DashboardStudent extends Controller {

    /**
     * Display the Dashboard
     * @return
     */
    public static Result index() {
        return ok(
                views.html.Dashboard.dashboard.render(
                    Discipline.findByStudent(request().username())
                )
        );
    }
}
