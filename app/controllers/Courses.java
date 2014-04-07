package controllers;

import models.Course;
import play.data.Form;
import play.mvc.Controller;

import play.mvc.Result;
import views.html.*;
/**
 * Created by NRAM on 07/04/14.
 */
public class Courses extends Controller {

    static Form<Course> courseForm = Form.form(Course.class);

    public static Result index(){
        return ok(index.render("Your new application Courses is ready."));
    }

    public static Result all(){
        return TODO;
    }

    public static Result show(Long id){
        return TODO;
    }

    public static Result delete(Long id){
        return TODO;
    }

    public static Result create(){
        return TODO;
    }
}
