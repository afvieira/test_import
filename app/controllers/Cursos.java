package controllers;

import models.Curso;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by afv on 19/04/14.
 */
public class Cursos extends Controller {

    final static Form<Curso> cursoForm = Form.form(Curso.class);

    public static Result index(){
        return ok("Hello Course!!!");
    }

    public static Result all(){
        return ok(views.html.Cursos.cursos.render(Curso.all(), cursoForm));
    }

    public static Result show(Long id){
        return TODO;
    }

    public static Result delete(Long id){
        Curso.delete(id);
        return redirect(routes.Cursos.all());
    }

    public static Result create(){
        Form<Curso> filledForm = cursoForm.bindFromRequest();
        if(filledForm.hasErrors()){
            return badRequest(views.html.Cursos.cursos.render(Curso.all(), filledForm));
        }else{
            Curso.create(filledForm.get());
            return redirect(routes.Cursos.all());
        }
    }

}
