package controllers;

import models.Course;
import models.Discipline;
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
                views.html.Courses.item.render(
                        User.findByEmail(request().username()),
                        Course.getById(id)
                )
        );
    }

    public static Result create(){
        return ok(
                views.html.Courses.item.render(
                        User.findByEmail(request().username()),
                        null
                )
        );
    }

    public static Result delete(Long id){
        try{
            Course.delete(id);
            flash("sucesso","Curso removido com sucesso");
        }catch(Exception e){
            flash("erro", e.getMessage());
        }
        return all();
    }

    public static Result update(Long id){
        Form<Course> filledForm = courseForm.bindFromRequest();
        if(filledForm.hasErrors()){
            flash("erro","Ocorreram erros na gravação");
            return badRequest(views.html.Courses.item.render(
                    User.findByEmail(request().username()),
                    Course.getById(id)
            ));
        }else{
            filledForm.get().update(id);
            flash("sucesso", "Curso gravado com sucesso.");
            return redirect(routes.Courses.all());
        }
    }

    public static Result save(){
        Form<Course> filledForm = courseForm.bindFromRequest();
        if(filledForm.hasErrors()){
            flash("erro","Ocorreram erros na gravação");
            return badRequest(views.html.Courses.item.render(User.findByEmail(request().username()),null));
        }else{
            Course.create(filledForm.get());
            flash("sucesso","Curso gravado com sucesso.");
            return redirect(routes.Courses.all());
        }
    }
}
