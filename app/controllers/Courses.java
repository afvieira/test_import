package controllers;

import models.Course;
import play.data.Form;
import play.mvc.Controller;

import play.mvc.Result;
import views.html.Courses.*;

/**
 * Created by NRAM on 07/04/14.
 */
public class Courses extends Controller {

    final static Form<Course> courseForm = Form.form(Course.class);

    public static Result index(){
        return ok("Hello Course!!!");
    }

    public static Result all(){
        return ok(index.render(Course.all(), courseForm));
    }

    public static Result show(Long id){
        return TODO;
    }

    public static Result delete(Long id){
        Course.delete(id);
        return redirect(routes.Courses.all());
    }

    public static Result create(){
        Form<Course> filledForm = courseForm.bindFromRequest();
        if(filledForm.hasErrors()){
            return badRequest(
                    index.render(Course.all(), filledForm)
            );
        }else{
            Course.create(filledForm.get());
            return redirect(routes.Courses.all());
        }
    }
}
