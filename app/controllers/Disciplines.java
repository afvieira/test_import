package controllers;

import models.Discipline;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

/**
 * Created by NRAM on 07/04/14.
 */
public class Disciplines extends Controller {

    final static Form<Discipline> DisciplineForm = Form.form(Discipline.class);

    public static Result index(){
        return ok("Hello Discipline!!!");
    }

    public static Result all(){
        return ok(
                views.html.Disciplines.index.render(
                        User.findByEmail(request().username()),
                        Discipline.all()
                )
        );
        //return ok(views.html.Disciplines.index.render(User.findByEmail(request().username()), Discipline.all(), DisciplineForm));
    }

    public static Result show(Long id){
        return ok(
                views.html.Disciplines.show.render(
                        User.findByEmail(request().username()),
                        Discipline.getById(id)
                )
        );
    }

    public static Result delete(Long id){
        Discipline.delete(id);
        return redirect(routes.Disciplines.all());
    }

    public static Result create(){
        Form<Discipline> filledForm = DisciplineForm.bindFromRequest();
        if(filledForm.hasErrors()){
            return badRequest("BAD");
        }else{
            Discipline.create(filledForm.get());
            return redirect(routes.Disciplines.all());
        }
    }

    public static Result allByCourse(Long id_course){
        return ok(
                views.html.Disciplines.index.render(
                    User.findByEmail(request().username()),
                    Discipline.allByCourse(id_course)
                )
        );
    }

    public static Result showByCourse(Long id_course, Long id_discipline){
        return ok(
                views.html.Disciplines.show.render(
                        User.findByEmail(request().username()),
                        Discipline.getById(id_discipline)
                )
        );
    }

    // Não sei como enviar pelo curso... acho que isso já vem desde o momento que se tenta criar a disciplina
    public static Result createByCourse(Long id_course){
        Form<Discipline> filledForm = DisciplineForm.bindFromRequest();
        if(filledForm.hasErrors()){
            return badRequest("BAD");
        }else{
            Discipline.create(filledForm.get());
            return redirect(routes.Disciplines.all());
        }
    }


}
