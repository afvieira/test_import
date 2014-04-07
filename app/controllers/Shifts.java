package controllers;

import models.Shift;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

/**
 * Created by NRAM on 07/04/14.
 */
public class Shifts extends Controller {

    static Form<Shift> courseForm = Form.form(Shift.class);

    public static Result index(){
        return ok(index.render("Your new application Courses is ready."));
    }

    public static Result all(Long id_course, Long id_discipline){
        return TODO;
    }

    public static Result show(Long id_course, Long id_discipline, Long id_shift){
        return TODO;
    }

    public static Result delete(Long id_course, Long id_discipline, Long id_shift){
        return TODO;
    }

    public static Result create(Long id_course, Long id_discipline){
        return TODO;
    }

}
