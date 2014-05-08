package controllers;

import models.Course;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by NRAM on 07/04/14.
 */
@Security.Authenticated(Secured.class)
public class Courses extends Controller {

    final static Form<Course> courseForm = Form.form(Course.class);

    public static Result all(){
        return ok(
            views.html.Courses.index.render(
                    User.findByEmail(request().username()),
                    Course.all()
            )
        );
    }

    public static Result show(Long id){
        return ok(
                views.html.Courses.show.render(
                        User.findByEmail(request().username()),
                        Course.getById(id)
                )
        );
    }

    public static Result delete(Long id){
        try{
            Course.delete(id);
            return redirect(routes.Courses.all());
        }catch(Exception e){
            return badRequest(views.html.error.render(null, e.getMessage()));
        }
    }

    public static Result update(Long id){
        Form<Course> filledForm = courseForm.bindFromRequest();
        if(filledForm.hasErrors()){
            return badRequest("BAD");
        }else{
            filledForm.get().update(id);
            return redirect(routes.Courses.all());
        }
    }

    public static Result create(){
        Form<Course> filledForm = courseForm.bindFromRequest();
        if(filledForm.hasErrors()){
            return badRequest("BAD");
        }else{
            Course.create(filledForm.get());
            return redirect(routes.Courses.all());
        }
    }
}
