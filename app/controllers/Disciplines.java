package controllers;

import models.Discipline;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

/**
 * Created by NRAM on 07/04/14.
 */
public class Disciplines extends Controller {
    static Form<Discipline> disciplineForm = Form.form(Discipline.class);

    public static Result index(){
        return ok(index.render("Your new application Courses is ready."));
    }

    public static Result all(){
        return ok(
                //views.html.Disciplines.index.render(Discipline.all(), disciplineForm)
        );
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

    public static Result allByCourse(Long id_course){
        return ok(views.html.Courses.index.render(
                Discipline.allByCourse(id_course), disciplineForm
        ));
    }

    public static Result showByCourse(Long id_course, Long id_discipline){
        return TODO;
    }

    public static Result createByCourse(Long id_course){
        return TODO;
    }


}
