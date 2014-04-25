package controllers;

import models.Project;
import play.mvc.Controller;
import play.mvc.Security;
import play.mvc.Result;
import play.data.Form;

/**
 * Created by afv on 25/04/14.
 */
@Security.Authenticated(Secured.class)
public class Projects extends Controller{

    final static Form<Project> projectForm = Form.form(Project.class);

    public static Result index(){
        return ok("Hello Course!!!");
    }

    public static Result all(){
        return ok(views.html.Projects.projects.render(Project.all(), projectForm));
    }

    public static Result show(Long id){
        return TODO;
    }

    public static Result delete(Long id){
        Project.delete(id);
        return redirect(routes.Projects.all());
    }

    public static Result create(){
        Form<Project> filledForm = projectForm.bindFromRequest();
        if(filledForm.hasErrors()){
            return badRequest("BAD");
        }else{
            Project.create(filledForm.get());
            return redirect(routes.Projects.all());
        }
    }
}
