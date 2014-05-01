package controllers;

import models.*;
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
                views.html.Dashboard.Student.dashboard.render(
                        User.findByEmail(request().username()),
                        Discipline.findByUser(request().username()),                // Disciplinas Que frequenta
                        Milestone.findByStudent(request().username()),              // Procurar próximas etapas para entrega
                        Project.getByCreatedDate(request().username()),             // Procurar últimos projetos submetidos
                        StudentMilestone.getLastAvaliations(request().username())   // Últimas Avaliações adicionadas
                )
        );
    }

    @Security.Authenticated(Secured.class)
    public static Result disciplines() {
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result projects() {
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result milestones() {
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result avaliation() {
        return TODO;
    }
}
