package controllers;

import models.Milestone;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by NRAM on 07/04/14.
 */
@Security.Authenticated(Secured.class)
public class Milestones extends Controller {

    final static Form<Milestone> MilestoneForm = Form.form(Milestone.class);

    public static Result all() {
        return ok(
                views.html.Milestones.index.render(
                        User.findByEmail(request().username()),
                        Milestone.all()
                )
        );
    }

    public static Result show(Long id) {
        return ok(
                views.html.Milestones.show.render(
                        User.findByEmail(request().username()),
                        Milestone.getById(id)
                )
        );
    }

    public static Result delete(Long id) {
        try {
            Milestone.delete(id);
            return redirect(routes.Milestones.all());
        } catch (Exception e) {
            return badRequest(views.html.error.render(null, e.getMessage()));
        }
    }

    public static Result update(Long id) {
        Form<Milestone> filledForm = MilestoneForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest("BAD");
        } else {
            filledForm.get().update(id);
            return redirect(routes.Milestones.all());
        }
    }

    public static Result create() {
        Form<Milestone> filledForm = MilestoneForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest("BAD");
        } else {
            Milestone.create(filledForm.get());
            return redirect(routes.Milestones.all());
        }
    }
}
